package org.example.tenantapp.controllers;


import jakarta.persistence.EntityManager;
import org.example.tenantapp.models.Customer;
import org.example.tenantapp.services.HibernateUtil;
import org.example.tenantapp.services.TransactionManager;
import org.example.tenantapp.utils.Helper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    HibernateUtil util;

    @Autowired
    TransactionManager transactionManager;

    @GetMapping("/")
    public String index(){
//        util.hello();
//        EntityManager em = util.getSession();
//        em.getTransaction().begin();
        Customer customer = new Customer();
        customer.name  = Helper.name();
        customer.email = Helper.email();
        customer.save();
//        em.persist(customer);
//        em.getTransaction().commit();
//        em.close();
//        session.close();
//        transactionManager.run();
//        String name = Helper.name();
//        Helper.print(name);
//        TransactionManager.useSource(name);
//        try {
//           Thread.sleep(1000 * 10);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Helper.print(TransactionManager.getSource());

        return "Hello World";
    }
}
