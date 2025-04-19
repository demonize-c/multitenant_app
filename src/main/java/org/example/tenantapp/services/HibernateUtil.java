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
public class HibernateUtil {

    public static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("default",getDefaultSource());
    }

    public static Properties getDefaultDbProps(){
        Properties props = new Properties();

        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.current_session_context_class","thread");
        props.put("jakarta.persistence.schema-generation.database.action", "update");
        return props;
    }

    public static EntityManager getDefaultEntityManagerFactory(){
//        if(emf != null){
//            return emf.createEntityManager();
//        }
//        emf =
        return emf.createEntityManager();
    }

    public static Properties getDefaultSource(){
        Properties props = getDefaultDbProps();
        props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/kg_db?useSSL=true&serverTimezone=UTC");
        props.put("jakarta.persistence.jdbc.user", "kg_user");
        props.put("jakarta.persistence.jdbc.password", "kg_pass");
        return props;
    }
    public static EntityManagerFactory getCustomEntityManagerFactory(String db_host, String db_port, String db_name, String db_user, String db_pass){
        Properties props = getDefaultDbProps();
        props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://" + db_host + ":" + db_port+ "/" + db_name + "?useSSL=true&serverTimezone=UTC");
        props.put("jakarta.persistence.jdbc.user", db_user);
        props.put("jakarta.persistence.jdbc.password", db_pass);
        return Persistence.createEntityManagerFactory("default",props);
    }
}
