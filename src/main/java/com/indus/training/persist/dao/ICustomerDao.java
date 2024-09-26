package com.indus.training.persist.dao;

import java.util.List;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.CustomerDaoException;

/**
 * Interface for managing Customer data access operations. Provides methods to
 * insert, fetch, delete, and update Customer records.
 */
public interface ICustomerDao {

	/**
	 * Inserts a customer object into the database.
	 * 
	 * @param customer the customer object to insert
	 * @return true if the customer was inserted successfully, false otherwise
	 * @throws CustomerDaoException if an error occurs during the insertion process
	 */
	public Boolean insertCustomer(Customer customer) throws CustomerDaoException;

	/**
	 * Fetches a customer record from the database based on the customer ID.
	 * 
	 * @param customerId the ID of the customer to fetch
	 * @return the Customer object corresponding to the given customer ID, or null
	 *         if not found
	 * @throws CustomerDaoException if an error occurs during the fetch operation
	 */
	public Customer fetchCustomer(Integer customerId) throws CustomerDaoException;

	/**
	 * Deletes a customer record from the database based on the customer ID.
	 * 
	 * @param customerId the ID of the customer to delete
	 * @return true if the customer was deleted successfully, false otherwise
	 * @throws CustomerDaoException if an error occurs during the deletion process
	 */
	public Boolean deleteCustomer(Integer customerId) throws CustomerDaoException;

	/**
	 * Updates the customer in the database.
	 * 
	 * @param customerId      the ID of the customer to be updated
	 * @param updatedCustomer the updated customer object
	 * @return true if the customer was updated successfully, false otherwise
	 * @throws CustomerDaoException if an error occurs during the update process
	 */
	public Boolean updateCustomerById(Integer customerId, Customer updatedCustomer) throws CustomerDaoException;

	/**
	 * Gets all the Orders of Given Customer Id
	 * 
	 * @param customerId the Id of Customer whose Orders need to be fetched
	 * @return the list of Orders
	 * @throws CustomerDaoException if an error occurs during the fetching process
	 */

	public List<Order> getAllOrdersByCustomerId(Integer customerId) throws CustomerDaoException;

}
