package org.example.tenantapp.models;

import jakarta.persistence.MappedSuperclass;
import org.example.tenantapp.helperclasses.*;
import org.example.tenantapp.services.TransactionManager;

import java.io.Serializable;
import java.lang.reflect.Field;

@MappedSuperclass
public class BaseEntity<T extends BaseEntity<T,I>, I>{

    public static Class<? extends BaseEntity> entityClass;



    public static void init(Class<? extends BaseEntity> paramClass){
       entityClass = paramClass;
    }

//    public static void useConnection(ConnectionUtil connectionUtil){
//        TransactionManager.useConnection(connectionUtil);
//    }
//
//    public void  useConnection(ConnectionUtil connectionUtil){
//        TransactionManager.useConnection(connectionUtil);
//    }

    public void save() throws Exception{
        CreateFunctionInterface callback = (em) -> em.save(this);
        TransactionManager.execute(callback);
    }

    public static <T> T findOne(Long id){
        FindOneFunctionInterface<T> callback = (em) -> (T) em.find(entityClass, id);
        T entity = TransactionManager.execute(callback);
        return entity;
    }

    public boolean remove(){
        RemoveFunctionInterface callback = ( em )->{
            try{
                Field field = entityClass.getDeclaredField("id");
                field.setAccessible(true);
                em.remove(em.find(this.getClass(),(Long) field.get(this)));
            }catch (Exception e){
               throw new RuntimeException(e);
            }
        };
        return TransactionManager.execute(callback);
    }
    public T update(){
        UpdateFunctionInterface<T> callback = (em )->{
            try{
                return (T) em.merge(this);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        };
        return TransactionManager.execute(callback);
    }
}
