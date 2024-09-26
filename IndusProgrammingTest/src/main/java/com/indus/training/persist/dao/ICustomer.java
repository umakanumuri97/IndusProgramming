package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.JPAException;

public interface ICustomer {
//method to create Customer
	public Boolean create(Customer customer) throws JPAException;

//method to read
	public Customer read(int customerId) throws JPAException;

//method to update
	public Boolean update(Customer customer) throws JPAException;

//method to delete
	public Boolean delete(int customerId) throws JPAException;

}
