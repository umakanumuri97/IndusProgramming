package com.indus.exam.persist.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

//3)To enforce referential integrity using JPA, we need to check @JoinColumn in mapping relations, 
//cascadetype set to all and placing orphanRemoval true all the orders will be deleted


@Entity
@Table(name = "Orders") 
public class Orders {
	
    @Id
    @Column(name = "order_id") 
    private Long orderId;

    @Column(name = "order_date", nullable = false) 
    private LocalDate orderDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
    
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false) 
    private Customer customer;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
 
}
