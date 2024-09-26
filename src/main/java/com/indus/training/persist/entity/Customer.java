package com.indus.training.persist.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a Customer entity in the database
 */

@Entity
@Table(name = "Customer") // Maps this class to the customer table in database
public class Customer {
	@Id
	@Column(name = "customer_id") // Maps the customerId field to the customer_id column
	private int customerId;

	@Column(name = "name") // Maps the customerName field to the name column
	private String customerName;
	@Column(name = "email") // Maps the email field to the email column
	private String email;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)//OneToMany relation for customers and orders 
	private List<Orders> order;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
