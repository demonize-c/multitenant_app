package org.example.tenantapp.services;


import jakarta.annotation.PreDestroy;
import org.example.tenantapp.helperclasses.ConnectionUtil;
import org.example.tenantapp.helperclasses.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;

//@Configuration
@Component
@RequestScope
public class LocalConnectionManager {

    @Autowired
    private ConnectionManager connectionManager;

    private List<ConnectionUtil> connectionUtils = new ArrayList<ConnectionUtil>();

    public LocalConnectionManager(){ }


    public ConnectionUtil createNewEntityManager(DataSource dataSource){
        ConnectionUtil connUtil = this.connectionManager.createEntityManagerFactory(dataSource);
        connectionUtils.add(connUtil);
        return connUtil;
    }

    @PreDestroy
    public void onDestroy(){
        System.out.println("cleanup running.");
        for (int i = 0; i < this.connectionUtils.size(); i++) {
            this.connectionManager.destoryEntityManagerFactory(this.connectionUtils.get(i));
        }
        this.connectionUtils.clear();
    }

    public int getTotalActiveSourceCount(){
        return  this.connectionManager.getTotalActiveSourceCount();
    }
}
