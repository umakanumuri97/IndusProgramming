package com.indus.training.persist.impl;

import com.indus.training.persist.dao.ICustomerManager;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.CustomerException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public class CustomerManager implements ICustomerManager {

	@Override
	public Boolean insert(Customer obj) throws CustomerException {
		EntityManager manage = null;
		EntityTransaction trans = null;
		try {

			manage = CustomerUtil.getEntityManager();
			trans = manage.getTransaction();
			trans.begin();
			manage.persist(obj);
			trans.commit();
		} catch (PersistenceException e) {
			if (trans != null) {
				trans.rollback();
			}
			throw new CustomerException("Persist Operation Exception Caught in customer insertion: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			if (trans != null) {
				trans.rollback();
			}
			throw new CustomerException("Invalid entity provided: " + e.getMessage());

		} finally {
			if (manage != null) {
				manage.close();
			}
		}
		return true;
	}

	@Override
	public Customer read(int id) throws CustomerException {
		Customer result = null;
		EntityManager manage = null;
		try {

			manage = CustomerUtil.getEntityManager();

			result = manage.find(Customer.class, id);

		} catch (PersistenceException e) {

			throw new CustomerException("Persist Operation: " + e.getMessage());
		} catch (IllegalArgumentException e) {

			throw new CustomerException("Entity values: " + e.getMessage());
		} finally {
			if (manage != null) {
				manage.close();
			}
		}
		return result;
	}

	@Override
	public Boolean delete(int id) throws CustomerException {
		EntityManager manage = null;
		EntityTransaction trans = null;
		try {

			manage = CustomerUtil.getEntityManager();
			trans = manage.getTransaction();
			trans.begin();
			Customer customer = manage.find(Customer.class, id);
			if (customer == null) {
				throw new CustomerException("Customer with entered id is not found. ");
			}
			manage.remove(customer);
			trans.commit();
		} catch (PersistenceException e) {
			if (trans != null) {
				trans.rollback();
			}
			throw new CustomerException("Persist Operation: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			if (trans != null) {
				trans.rollback();
			}
			throw new CustomerException("Entity values: " + e.getMessage());
		} catch (CustomerException e) {
			if (trans != null) {
				trans.rollback();
			}
			throw (e);
		} finally {
			if (manage != null) {
				manage.close();
			}
		}
		return true;
	}

}
