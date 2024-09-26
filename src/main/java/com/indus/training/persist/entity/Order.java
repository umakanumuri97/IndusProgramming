package com.indus.training.persist.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a Order entity with attributes such as orderId,orderDate
 * ,totalAmount and customerId
 */
@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "order_date", nullable = false)
	private Date orderDate;

	@Column(name = "total_amount", nullable = false)
	private Double totalAmount;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
