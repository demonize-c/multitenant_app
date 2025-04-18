package org.example.tenantapp.controllers;


import jakarta.persistence.EntityManager;
import org.example.tenantapp.models.Customer;
import org.example.tenantapp.services.HibernateUtil;
import org.example.tenantapp.services.LocalConnectionManager;
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
    TransactionManager
            transactionManager;
    @Autowired
    LocalConnectionManager lcm;

    @GetMapping("/")
    public String index(){
//        util.hello();
//        EntityManager em = util.getSession();
//        em.getTransaction().begin();
//        for (int i = 402; i < 602; i++) {
//
//            Customer customer = Customer.findOne(Long.valueOf(i));
//            customer.remove();
//        }
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

//        Customer customer = Customer.findOne(Long.valueOf(402));
//        System.out.println(customer);
//        Customer customer = new Customer();
//        customer.name  = "Mohan das";
//        customer.email = "sourab@gmail.com";
//        customer.save();
//        customer.email="mohan@gmail.com";
//        customer.update();
        lcm.run();
        return "Hello World";
    }
}
