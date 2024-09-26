package com.indus.exam.persist.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer") 
public class Customer {

    @Id
    @Column(name = "customer_id") 
    private Long customerId;

    @Column(name = "name", nullable = false) 
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    // 1,2 ) One-to-many relationship with Order, cascade all operations to Orders
    // This means when we perform an operation on a Customer same operation will be applied to the related Orders.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;

    public Customer() { }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void addOrder(Orders order) {
        orders.add(order);
        order.setCustomer(this); 
    }

    public void removeOrder(Orders order) {
        orders.remove(order);
        order.setCustomer(null); 
    }
}
