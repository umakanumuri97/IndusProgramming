package com.indus.training.persist.impl;

import java.util.List;

import com.indus.training.persist.dao.ICustomer;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.JPAException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class CustomerManager implements ICustomer {

	/**
	 * Creates a new customer in the database.
	 */
	@Override
	public Boolean create(Customer customer) throws JPAException {
		EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction tr = null;
		try {
			tr = em.getTransaction();
			tr.begin();// Start the transaction
			em.persist(customer);//// Persist the customer entity to the database
			tr.commit();// Commit the transaction if successful
			return true; 
		} catch (Exception e) {
			if (tr != null) {
				tr.rollback();
			}
			throw new JPAException("Failed to create customer: " + e.getMessage(), e);
		} finally {
			em.close();
		}
	}
/**
 * Updates an existing customer in the database.
 */
	@Override
	public Boolean update(int customerId, Customer updateCustomer) throws JPAException {
		EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction tr = null;
		try {
			tr = em.getTransaction();
			tr.begin();
			Customer customer = em.find(Customer.class, customerId);
			if (customer != null) {
				customer.setCustomerName(updateCustomer.getCustomerName());
				customer.setEmail(updateCustomer.getEmail());
				tr.commit(); // Commit only if update is successful
				return true; // Return true on successful update
			} else {
				throw new JPAException("Customer not found with ID: " + customerId);
			}
		} catch (Exception e) {
			if (tr != null) {
				tr.rollback();
			}
			throw new JPAException("Error updating customer: " + e.getMessage(), e);
		} finally {
			em.close();
		}
	}
/**
 * Deletes a customer from the database.
 */
	@Override
	public Boolean delete(int customerId) throws JPAException {
		EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction tr = null;
		try {
			tr = em.getTransaction();
			tr.begin();
			Customer customer = em.find(Customer.class, customerId);
			if (customer != null) {
				em.remove(customer);
				tr.commit(); // Commit only if deletion is successful
				return true; // Return true on successful deletion
			} else {
				throw new JPAException("Customer not found with ID: " + customerId);
			}
		} catch (Exception e) {
			if (tr != null) {
				tr.rollback();
			}
			throw new JPAException("Error deleting customer: " + e.getMessage(), e);
		} finally {
			em.close();
		}
	}
/**
 * To getCustomerOrders
 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCustomerOrders() throws JPAException {
		EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
		try {
			String jpql = "SELECT c.name, COUNT(o.id) AS totalOrders, SUM(o.totalAmount) AS totalAmount "
					+ "FROM Customer c " + "LEFT JOIN Order o ON c.id = o.customer.id " + "GROUP BY c.id";
			Query query = em.createQuery(jpql);
			return query.getResultList();
		} catch (Exception e) {
			throw new JPAException("Error retrieving customer order statistics", e);
		}
	}
}
