package com.indus.training.persist.impl;

import com.indus.training.persist.dao.ICustomer;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.OrderException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class CustomerManager implements ICustomer {
	private final EntityManagerFactory entityManagerFactory;

	public CustomerManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public Boolean create(Customer customer) throws OrderException {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(customer);//here for persist is nothing but save ref for ques2 
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new OrderException("Failed to create customer", e);
		} finally {
			em.close();
		}
	}

	@Override
	public int read(int id) throws OrderException {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Customer customer = em.find(Customer.class, id);
			if (customer != null) {
				return customer.getCustomerid();
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderException("Failed to read customer", e);
		} finally {
			em.close();
		}
	}

	@Override
	public Boolean update(int id, Customer updatedCustomer) throws OrderException {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();

			Customer customer = em.find(Customer.class, id);
			if (customer != null) {
				customer.setCustomername(updatedCustomer.getCustomername());
				customer.setCustomeremail(updatedCustomer.getCustomeremail());
				em.merge(customer);
				transaction.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new OrderException("Failed to update customer", e);
		} finally {
			em.close();
		}
	}

	@Override
	public Boolean delete(int id) throws OrderException {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();

			Customer customer = em.find(Customer.class, id);
			if (customer != null) {
				em.remove(customer);
				transaction.commit();
				return true;
			} else {
				return false; 
			}
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new OrderException("Failed to delete customer", e);
		} finally {
			em.close();
		}
	}
}
