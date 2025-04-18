package org.example.tenantapp.services;


import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.tenantapp.helperclasses.DataSource;
import org.example.tenantapp.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

//@Configuration
@Component
@RequestScope
public class LocalConnectionManager {

    @Autowired
    private ConnectionManager connectionManager;

    private EntityManagerFactory emf;
    public LocalConnectionManager(){ };
//    static ThreadLocal<>


    public void createNewEntityManager(DataSource dataSource){
       this.em  =
    }

    @PreDestroy
    public void onDestroy(){
        Helper.print("destroyed");
    }
}
