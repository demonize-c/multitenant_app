package org.example.tenantapp.models;

import jakarta.persistence.*;
import org.example.tenantapp.services.TransactionManager;
import org.example.tenantapp.utils.Helper;


@Entity
@Table(name = "customers")
public class Customer extends BaseEntity<Customer,Long>{

    static {
        init(Customer.class);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_name",nullable = false)
    public String name;

    @Column(name = "customer_email",nullable = false)
    public String email;

    @Override
    public String toString() {
        return    "id"    + this.id   + "\n"
                + "email" + this.name + "\n"
                + "name"  + this.email;
    }
}
