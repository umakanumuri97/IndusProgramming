package com.indus.training.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "total_amount", nullable = false)
    private double orderTotalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "\nOrder Id=" + orderId + "\nOrder Date=" + orderDate + "\nOrder Total Amount=" + orderTotalAmount;
    }
}
