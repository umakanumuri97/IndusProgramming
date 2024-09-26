package com.indus.training.persist.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents an Order entity in the database.
 */
@Entity
@Table(name = "Orders") // Maps this class to the orders table in the database
public class Orders {
	@Id
	@Column(name = "order_id") // Maps the orderId field to the order_id column
	private int orderId;

	@Column(name = "order_date") // Maps the orderDate field to the order_date column
	private Date orderDate;

	@Column(name = "total_amount") // Maps the totalAmount field to the total_amount column
	private double totalAmount;

	@ManyToOne(cascade = CascadeType.PERSIST) // many-to-one relationship with Customer
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Orders() {
		super();
	}

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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
