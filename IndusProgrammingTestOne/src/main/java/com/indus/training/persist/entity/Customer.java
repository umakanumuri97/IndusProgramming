package com.indus.training.persist.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//ques1(conducted one-to-many relationship between Customer and Order using JPA annotations)and 
//used cascade = CascadeType.ALL (it allows to perform save,update,delete operations )--ref ques 2
@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerid;
	@Column(name = "name")
	private String customername;
	@Column(name = "email")
	private String customeremail;

	@OneToMany(mappedBy = "customer_id", cascade = CascadeType.ALL)
	private List<Orders> order;// in my case if i delete a Customer,
	// the related Orders will also be deleted automatically----ref ques 3

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomeremail() {
		return customeremail;
	}

	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
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
