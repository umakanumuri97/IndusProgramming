package com.indus.training.persist.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
//ques1(conducted many-to-one relationship between Customer and Order using JPA annotations)
@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;

	@Column(name = "order_date")
	private int orderdate;
	@Column(name = "total_amount")
	private int totalamount;
	@Column(name = "customer_id")
	private int customerid;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(int orderdate) {
		this.orderdate = orderdate;
	}

	public int getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

}
