package org.example.tenantapp.helperclasses;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ConnectionUtil {



    private DataSourceUtil dataSourceUtil;

    public ConnectionUtil(DataSourceUtil dataSourceUtil){
        this.dataSourceUtil = dataSourceUtil;
    }

    public String getConnKey(){
        return this.dataSourceUtil.getConnKey();
    }

    public EntityManagerUtil createEntityManagerUtil(){
        return new EntityManagerUtil(this);
    }

    public EntityManager createNewEntityManager() throws Exception{
        if(!this.dataSourceUtil.getEmf().isOpen()){
            throw new Exception("Current Entity Manager Factory is closed." +
                    "Can't create new entity manager");
        }
        return this.dataSourceUtil.getEmf().createEntityManager();
    }

    public void waitForTransaction() throws InterruptedException{
        this.dataSourceUtil.waitForTransaction();
    }

    public void informTransactionEnd(){
        this.dataSourceUtil.informTransactionEnd();
    }

    public int getDataSourceCurrentConnCount(){
        return this.dataSourceUtil.currentConnCount();
    }
}
