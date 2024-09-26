package com.indus.training.persist.dao;

import java.util.List;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.JPAException;

/**
 * Interface for Customer
 * 
 */
public interface ICustomer {

	// Creates a new customer record in the database.

	Boolean create(Customer customer) throws JPAException;

	// Updates an existing customer record in the database.

	Boolean update(int customerId, Customer updateCustomer) throws JPAException;

	// Deletes a customer record from the database.

	Boolean delete(int customerId) throws JPAException;

	// Retrieves a list of customers along with their orders.

	List<Object[]> getCustomerOrders() throws JPAException;
}
