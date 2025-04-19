package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManagerFactory;
import org.example.tenantapp.services.ConnectionManager;

import java.util.concurrent.Semaphore;

public class DataSourceUtil {

    private DataSource  dataSource;
    private Semaphore   semaphore;
    private EntityManagerFactory emf;
    private String connKey;

    public DataSourceUtil(DataSource dataSource, String connKey, EntityManagerFactory emf, Semaphore semaphore){
        this.dataSource = dataSource;
        this.semaphore  = semaphore;
        this.emf  =  emf;
        this.connKey = connKey;
    }

    public void waitForTransaction() throws InterruptedException{
            this.semaphore.acquire();
    }

    public void informTransactionEnd(){
            this.semaphore.release();
    }

    public boolean isSameSource(DataSource dataSource){
            return this.dataSource == dataSource;
    }

    public EntityManagerFactory getEmf() {
           return this.emf;
    }

    public DataSource getDataSource() {
           return this.dataSource;
    }

    public String getConnKey(){
        return this.connKey;
    }

    public int currentConnCount(){
        return ConnectionManager.max_conn - semaphore.availablePermits();
    }
}
