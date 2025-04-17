package org.example.tenantapp.services;

import jakarta.persistence.EntityManager;
import org.example.tenantapp.helperclasses.FunctionWithSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.concurrent.Semaphore;

@Service
public class TransactionManager {
    static Semaphore semaphore = new Semaphore(250);

//    @Autowired
//    static HibernateUtil hibernateUtil;
    static ThreadLocal<EntityManager> em = new ThreadLocal<EntityManager>(){
        @Override
        protected EntityManager initialValue() {
            return HibernateUtil.getDefaultEntityManager();
        }
    };
//    static int poolSize = 0;
//
//    public static void incrementPoolSize(){
//        poolSize++;
//    }
//    public static void decrementPoolSize(){
//        poolSize--;
//    }
    public static <T> void execute(FunctionWithSession function){
         try{
             semaphore.acquire();
             EntityManager useEm = em.get();
             useEm.getTransaction().begin();
             function.apply(useEm);
             useEm.getTransaction().commit();
             useEm.close();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }finally {
             semaphore.release();
         }
    }

    public static void useSource(EntityManager s){
        em.remove();
        em.set(s);
    }
    public  static void  swicthDefaultSource(){
        em.remove();
        em.set(HibernateUtil.getDefaultEntityManager());
    }
}
