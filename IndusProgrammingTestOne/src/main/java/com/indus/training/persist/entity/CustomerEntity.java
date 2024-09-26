package com.indus.training.persist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "name", nullable = false)
    private String customerName;

    @Column(name = "email", nullable = false)
    private String customerEmail;
    
	// One customer can have many orders
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderEntity> orders;
	
	

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



	public String getCustomerEmail() {
		return customerEmail;
	}



	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}



	public List<OrderEntity> getOrders() {
		return orders;
	}



	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	@Override
    public String toString() {
        return "\nCustomerId: " + customerId + "\nCustomer Name=" + customerName + "\nCustomerEmail=" + customerEmail;
    }
}
