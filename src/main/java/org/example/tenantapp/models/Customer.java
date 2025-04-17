package org.example.tenantapp.models;

import jakarta.persistence.*;
import org.example.tenantapp.services.TransactionManager;


@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_name",nullable = false)
    public String name;

    @Column(name = "customer_email",nullable = false)
    public String email;

}
