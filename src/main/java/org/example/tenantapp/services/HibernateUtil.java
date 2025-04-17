package org.example.tenantapp.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.tenantapp.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Properties;

@Service
@Scope("singleton")
public class HibernateUtil {

    public static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("default",getDefaultSource());
    }
    public static EntityManager getDefaultEntityManager(){
//        if(emf != null){
//            return emf.createEntityManager();
//        }
//        emf =
        return emf.createEntityManager();
    }

    public static Properties getDefaultSource(){
        Properties props = new Properties();
        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/kg_db?useSSL=false&serverTimezone=UTC");
        props.put("jakarta.persistence.jdbc.user", "kg_user");
        props.put("jakarta.persistence.jdbc.password", "kg_pass");
        props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.current_session_context_class","thread");
        props.put("hibernate.hbm2ddl.auto", "update");
        return props;
    }
    public static Properties getCustomSource(String db_name, String db_user, String db_pass){
        Properties props = new Properties();
        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/"+ db_name + "?useSSL=false&serverTimezone=UTC");
        props.put("jakarta.persistence.jdbc.user", db_user);
        props.put("jakarta.persistence.jdbc.password", db_pass);
        props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.current_session_context_class","thread");
        return props;
    }
}
