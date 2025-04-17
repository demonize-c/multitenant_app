package org.example.tenantapp.models;

import org.example.tenantapp.services.TransactionManager;

public class BaseEntity {

    public void save(){
        TransactionManager.execute((em) -> {
            em.persist(this);
        });
    }

    public <T> T find(){

    }
}
