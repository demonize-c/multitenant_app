package org.example.tenantapp.controllers;


import jakarta.persistence.EntityManager;
import org.example.tenantapp.helperclasses.ConnectionUtil;
import org.example.tenantapp.helperclasses.TestDataSource;
import org.example.tenantapp.models.Customer;
import org.example.tenantapp.services.ConnectionManager;
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
//        lcm.createNewEntityManager(TestDataSource.get(1));
//        lcm.createNewEntityManager(TestDataSource.get(1));
//        try {
//            Thread.sleep(1000 * 30);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 0; i < 5; i++) {
//            TransactionManager.useConnection(this.lcm.createNewEntityManager(TestDataSource.get(i)));
//            Customer customer = new Customer();
//            customer.name  = Helper.name();
//            customer.email = Helper.email();
//            customer.save();
//        }
        try {
            ConnectionUtil connUtil = this.lcm.createNewEntityManager(TestDataSource.get(0));
//          for (int i = 0; i < 5; i++) {
//            Helper.print("count: " + connUtil.getDataSourceCurrentConnCount());
            TransactionManager.useConnection(connUtil);
            Customer customer = new Customer();
            customer.name = Helper.name();
            customer.email = Helper.email();
            customer.save();
//            Thread.sleep(1000 * 10);
        }catch (Exception e){
            Helper.print(e.getMessage());
            return "Error";
        }
//            Helper.print("a s count: " + lcm.getTotalActiveSourceCount());
//        }
//        try {
//            Thread.sleep(1000 * 20);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        return "Hello World";
    }

    @GetMapping("/check")
    public String check() throws InterruptedException{
//        ConnectionUtil connUtil = this.lcm.createNewEntityManager(TestDataSource.get(0));
//        TransactionManager.useConnection(connUtil);
//        Customer customer = new Customer();
//        customer.name  = Helper.name();
//        customer.email = Helper.email();
//        customer.save();
        try {
            ConnectionUtil connUtil = this.lcm.createNewEntityManager(TestDataSource.get(0));
//          for (int i = 0; i < 5; i++) {
//            Helper.print("count: " + connUtil.getDataSourceCurrentConnCount());
            TransactionManager.useConnection(connUtil);
            Customer customer = new Customer();
            customer.name = Helper.name();
            customer.email = Helper.email();
            customer.save();
//            Thread.sleep(1000 * 20);
        }catch (Exception e){
            Helper.print(e.getMessage());
            return "Error";
        }
        return "Hello World";
    }
}
