package com.indus.training.persist.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indus.training.persist.dao.ICustomerDao;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.CustomerDaoException;
import com.indus.training.persist.util.EntityManagerUtil;

/**
 * This class implements the CustomerDao interface to handle operations for
 * Customer entities, such as inserting, fetching, deleting, and updating using
 * JPA.
 */
public class CustomerDaoImpl implements ICustomerDao {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

	/**
	 * Inserts a customer object into the database.
	 * 
	 * @param customer the customer object to insert
	 * @return true if the customer was inserted successfully, false otherwise
	 * @throws CustomerDaoException if an error occurs during the insertion process
	 */
	@Override
	public Boolean insertCustomer(Customer customer) throws CustomerDaoException {
		if (customer == null) {
			logger.error("Customer Object Cannnot be null");
			throw new CustomerDaoException("Customer Object Cannot be null");
		}
		EntityManager entityManager = null;
		boolean isInserted = false;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			isInserted = true;
			logger.info("Customer inserted successfully with id {}", customer.getCustomerId());
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while saving Customer with id {} to database", customer.getCustomerId());
				entityManager.getTransaction().rollback();
			}
			throw new CustomerDaoException("An error occured while saving Customer to database");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return isInserted;
	}

	/**
	 * Fetches a customer record from the database based on the customer ID.
	 * 
	 * @param customerId the ID of the customer to fetch
	 * @return the Customer object corresponding to the given customer ID, or null
	 *         if not found
	 * @throws CustomerDaoException if an error occurs during the fetch operation
	 */
	@Override
	public Customer fetchCustomer(Integer customerId) throws CustomerDaoException {
		if (customerId == null) {
			logger.error("Customer Id Cannnot be null");
			throw new CustomerDaoException("Customer Id Cannot be null");
		}
		EntityManager entityManager = null;
		Customer customer = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			customer = entityManager.find(Customer.class, customerId);
			if (customer != null) {
				logger.info("Customer with ID {} fetched successfully", customerId);
			} else {
				logger.info("Customer with provided Id {} not found", customerId);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while fetching customer with ID {}", customerId);
				entityManager.getTransaction().rollback();
			}
			throw new CustomerDaoException("An error occured while fetching customer");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return customer;
	}

	/**
	 * Deletes a customer record from the database based on the customer ID.
	 * 
	 * @param customerId the ID of the customer to delete
	 * @return true if the customer was deleted successfully, false otherwise
	 * @throws CustomerDaoException if an error occurs during the deletion process
	 */
	@Override
	public Boolean deleteCustomer(Integer customerId) throws CustomerDaoException {
		if (customerId == null) {
			logger.error("Customer Id Cannnot be null");
			throw new CustomerDaoException("Customer Id Cannot be null");
		}
		EntityManager entityManager = null;
		boolean isDeleted = false;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Customer customer = entityManager.find(Customer.class, customerId);
			if (customer != null) {
				entityManager.remove(customer);
				isDeleted = true;
				entityManager.getTransaction().commit();
				logger.info("Customer with ID {} deleted successfully", customer.getCustomerId());
			} else {
				logger.info("Customer with provided Id {} not found", customerId);
			}

		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while deleting customer with ID {}", customerId);
				entityManager.getTransaction().rollback();
			}
			throw new CustomerDaoException("An error occured while deleting customer");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return isDeleted;
	}

	/**
	 * Updates the customer in the database.
	 * 
	 * @param customerId      the ID of the customer to be updated
	 * @param updatedCustomer the updated customer object
	 * @return true if the customer was updated successfully, false otherwise
	 * @throws CustomerDaoException if an error occurs during the update process
	 */
	@Override
	public Boolean updateCustomerById(Integer customerId, Customer updatedCustomer) throws CustomerDaoException {
		if (customerId == null) {
			logger.error("Customer Id Cannnot be null");
			throw new CustomerDaoException("Customer Id Cannot be null");
		}
		EntityManager entityManager = null;
		Boolean isUpdated = false;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Customer customer = entityManager.find(Customer.class, customerId);
			if (customer != null) {
				customer.setCustomerId(updatedCustomer.getCustomerId());
				customer.setName(updatedCustomer.getName());
				customer.setEmail(updatedCustomer.getEmail());
				entityManager.getTransaction().commit();
				isUpdated = true;
				logger.info("Customer with ID {} updated successfully", customer.getCustomerId());
			} else {
				logger.info("Customer with provided Id {} not found", customerId);
			}
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while updating customer with ID {}", customerId);
				entityManager.getTransaction().rollback();
			}
			throw new CustomerDaoException("An error occured while updating customer");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return isUpdated;
	}

	/**
	 * Gets all the Orders of Given Customer Id
	 * 
	 * @param customerId the Id of Customer whose Orders need to be fetched
	 * @return the list of Orders
	 * @throws CustomerDaoException if an error occurs during the fetching process
	 */
	@Override
	public List<Order> getAllOrdersByCustomerId(Integer customerId) throws CustomerDaoException {
		EntityManager entityManager = null;
		List<Order> orders = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			String jpql = "SELECT c.orders FROM Customer c " + "where c.customerId== :customerId";
			orders = entityManager.createQuery(jpql, Order.class).setParameter("customerId", customerId)
					.getResultList();
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while getting Orders for customer with ID {}", customerId);
				entityManager.getTransaction().rollback();
			}
			throw new CustomerDaoException("An error occured while fetching customer orders");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return orders;
	}

}
