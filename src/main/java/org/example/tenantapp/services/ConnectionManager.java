package org.example.tenantapp.services;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManagerFactory;
import org.example.tenantapp.helperclasses.DataSource;
import org.example.tenantapp.helperclasses.DataSourceUtil;
import org.example.tenantapp.utils.Helper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

@Service
public class ConnectionManager{
    public static HashMap<String,DataSourceUtil> dataSourceUtils;
    public static ThreadLocal<String> emf;
    public static int max_conn = 200;
//    public static EntityManagerFactory createEntityManagerFactory(DataSource dataSource){
//           DataSourceUtil dataSourceUtil = new DataSourceUtil(dataSource,new Semaphore(max_conn));
//           dataSourceUtils.add(dataSourceUtil);
//           return
//    }
    public  EntityManagerFactory createEntityManagerFactory(DataSource dataSource){

        boolean found = false;
        DataSourceUtil foundDataSourceUtil = null;
//        for (int i = 0; i < dataSourceUtils.size(); i++) {
//            if(dataSourceUtils.get(i).isSameSource(dataSource)){
//                foundDataSourceUtil = dataSourceUtils.get(i);
//                break;
//            }
//        }
        for (Map.Entry<String, DataSourceUtil> entry: dataSourceUtils.entrySet()){
            if(entry.getValue().isSameSource(dataSource)){
                foundDataSourceUtil = entry.getValue();
                break;
            }
        }
        if(foundDataSourceUtil == null){
            String unit_name = "unit_" + dataSourceUtils.size();
            foundDataSourceUtil = new DataSourceUtil(
                    dataSource,
                    new Semaphore(200),
                    HibernateUtil.getCustomEntityManagerFactory(
                            unit_name,
                            dataSource.getDb_name(),
                            dataSource.getDb_pass(),
                            dataSource.getDb_pass()
                    )
            );
            dataSourceUtils.put(
                    unit_name, foundDataSourceUtil);
        }
        return foundDataSourceUtil.getEmf();
    }

}
