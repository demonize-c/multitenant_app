package org.example.tenantapp.services;

import jakarta.persistence.EntityManager;
import org.example.tenantapp.helperclasses.*;
import org.example.tenantapp.utils.Helper;
import org.springframework.stereotype.Service;
import java.util.concurrent.Semaphore;

@Service
public class TransactionManager {
    static Semaphore semaphore = new Semaphore(250);

//    @Autowired
//    static HibernateUtil hibernateUtil;
//    static ThreadLocal<EntityManager> em = new ThreadLocal<EntityManager>(){
//        @Override
//        protected EntityManager initialValue() {
//            return HibernateUtil.getDefaultEntityManagerFactory();
//        }
//    };
//    static int poolSize = 0;
//
//    public static void incrementPoolSize(){
//        poolSize++;
//    }
//    public static void decrementPoolSize(){
//        poolSize--;
//    }

    public static ThreadLocal<ConnectionUtil> connUtil = new ThreadLocal<ConnectionUtil>();

    public static void useConnection(ConnectionUtil connectionUtil){
        connUtil.set(connectionUtil);
    }
    public static void execute(CreateFunctionInterface function) throws Exception {

        EntityManagerUtil emUtil = connUtil.get().createEntityManagerUtil();
        emUtil.open();
        emUtil.beginTransaction();
        Thread.sleep(1000 * 20);
        function.apply(emUtil);
        emUtil.commit();
        emUtil.close();
    }

    public static <T> T execute(FindOneFunctionInterface<T> function){

        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        EntityManager useEm = HibernateUtil.getDefaultEntityManagerFactory();
        useEm.getTransaction().begin();
        T result;
        try{
            result = function.apply(useEm);
            useEm.getTransaction().commit();
            useEm.close();
            semaphore.release();
            return result;
        } catch (Exception e) {
            if(useEm.getTransaction().isActive()){
                useEm.getTransaction().rollback();
            }
            if(useEm.isOpen()){
                useEm.close();
            }
            semaphore.release();
            throw new RuntimeException(e);
        }
    }

    public static boolean execute(RemoveFunctionInterface function){

        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        EntityManager useEm = HibernateUtil.getDefaultEntityManagerFactory();
        useEm.getTransaction().begin();
        try{
            function.apply(useEm);
            useEm.getTransaction().commit();
            useEm.close();
            semaphore.release();
            return true;
        } catch (Exception e) {
            if(useEm.getTransaction().isActive()){
                useEm.getTransaction().rollback();
            }
            if(useEm.isOpen()){
                useEm.close();
            }
            semaphore.release();
            throw new RuntimeException(e);
        }
    }

    public static <T> T execute(UpdateFunctionInterface<T> function){

        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        EntityManager useEm = HibernateUtil.getDefaultEntityManagerFactory();
        useEm.getTransaction().begin();
        T result = null;
        try{
            result = function.apply(useEm);
            useEm.getTransaction().commit();
            useEm.close();
            semaphore.release();
            return result;
        } catch (Exception e) {
            if(useEm.getTransaction().isActive()){
                useEm.getTransaction().rollback();
            }
            if(useEm.isOpen()){
                useEm.close();
            }
            semaphore.release();
            throw new RuntimeException(e);
        }
    }

    public static void useSource(EntityManager s){
//        em.remove();
//        em.set(s);
    }
    public  static void  swicthDefaultSource(){
//        em.remove();
//        em.set(HibernateUtil.getDefaultEntityManagerFactory());
    }

//    public static <T extends BaseEntity> T execute(Object o) {
//    }
}
