package org.example.tenantapp.services;
import org.example.tenantapp.helperclasses.ConnectionUtil;
import org.example.tenantapp.helperclasses.DataSource;
import org.example.tenantapp.helperclasses.DataSourceUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

@Service
public class ConnectionManager{

    public static HashMap<String,DataSourceUtil> dataSourceUtils = new HashMap<String,DataSourceUtil>();

    public static int max_conn = 200;

    public ConnectionUtil createEntityManagerFactory(DataSource dataSource){

        boolean found = false;
        String existedDataSourceUtilKey = null;
        existedDataSourceUtilKey = getExistedDataSourceUtilKey(dataSource);
        if(existedDataSourceUtilKey != null){
            DataSourceUtil existedDataSourceUtil = dataSourceUtils.get(existedDataSourceUtilKey);
            return new ConnectionUtil(existedDataSourceUtil);
        }
        String uniqueConnkey = "unit_" + dataSourceUtils.size();
        DataSourceUtil newDataSourceUtil = new DataSourceUtil(
                    dataSource,
                    uniqueConnkey,
                    HibernateUtil.getCustomEntityManagerFactory(
                            dataSource.getDb_host(),
                            dataSource.getDb_port(),
                            dataSource.getDb_name(),
                            dataSource.getDb_user(),
                            dataSource.getDb_pass()
                    ),
                    new Semaphore(max_conn)
        );
        dataSourceUtils.put(
                    uniqueConnkey, newDataSourceUtil);

        return new ConnectionUtil(newDataSourceUtil);
    }

    public static String getExistedDataSourceUtilKey(DataSource dataSource){
        String key = null;
        for (Map.Entry<String, DataSourceUtil> entry: dataSourceUtils.entrySet()){
            if(entry.getValue().isSameSource(dataSource)){
                key = entry.getKey();
                break;
            }
        }
        return key;
    }

    public void destoryEntityManagerFactory(ConnectionUtil connUtil){
         DataSourceUtil dataSourceUtil  = dataSourceUtils.get(connUtil.getConnKey());
         if(dataSourceUtil.currentConnCount() != 0){
             System.out.println("clear Entity Manager Factory escaped"+
             " due to remaining " + dataSourceUtil.currentConnCount()+" active transactions");
             return;
         }
         System.out.println("conn count: " + dataSourceUtil.currentConnCount());
         dataSourceUtil.getEmf().close();
         dataSourceUtils.remove(connUtil.getConnKey());
    }

    public int getTotalActiveSourceCount(){
        return dataSourceUtils.size();
    }

}
