package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManagerFactory;

import java.util.concurrent.Semaphore;

public class DataSourceUtil {

    private DataSource  dataSource;
    private Semaphore   semaphore;
    private EntityManagerFactory emf;
    public DataSourceUtil(DataSource dataSource, Semaphore semaphore, EntityManagerFactory emf){
        this.dataSource = dataSource;
        this.semaphore  = semaphore;
        this.emf  =  emf;
    }

    public void occupy() throws InterruptedException{
        try{
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            throw new InterruptedException("There is a problem to accuire new connection");
        }
    }

    public void free(){
        this.semaphore.release();
    }

    public boolean isSameSource(DataSource dataSource){
        return this.dataSource == dataSource;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
