package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManager;

import java.util.concurrent.Semaphore;

public class EntityManagerUtil {
    private EntityManager  em;
    private ConnectionUtil connUtil;

    public EntityManagerUtil(ConnectionUtil connUtil){
        this.connUtil = connUtil;
    }

    public void open() throws  Exception{
        this.connUtil.waitForTransaction();
        this.em       = connUtil.createNewEntityManager();// throw Execeptions
    }

    public boolean isOpen(){
        return this.em.isOpen();
    }

    public void beginTransaction() throws Exception{
        if (!isOpen()){
            throw new Exception("Entity Manager already closed. Can't start transaction.");
        }
        this.em.getTransaction().begin();
    }

    public void commit() throws Exception{
        if(!isOpen()){
            throw new Exception("Entity Manager already closed. Can't commit.");
        }
        this.em.getTransaction().commit();
    }

    public void update(Object o){
        this.em.merge(o);
    }

    public void save(Object o) throws Exception{
        if(!isOpen()){
            throw new Exception("Entity Manager already closed. Can't perform insert operation.");
        }
        this.em.persist(o);
    }

    public void delete(Object o){
        this.em.remove(o);
    }

    public void find(Class<?> clazz ,Object o){
        this.em.find(clazz, o);
    }

    public void close() throws Exception{
        this.em.close();
        this.connUtil.informTransactionEnd();
    }
}
