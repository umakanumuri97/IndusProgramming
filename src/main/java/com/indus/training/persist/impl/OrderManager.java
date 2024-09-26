package com.indus.training.persist.impl;

import com.indus.training.persist.dao.IOrderManager;
import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.CustomerException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public class OrderManager implements IOrderManager {

	@Override
	public Boolean insert(Orders obj) throws CustomerException {
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
	public Boolean updateTotalAmount(int id, Orders obj) throws CustomerException {
		EntityManager manage = null;
		EntityTransaction trans = null;
		try {

			manage = CustomerUtil.getEntityManager();
			trans = manage.getTransaction();
			trans.begin();
			Orders order = manage.find(Orders.class, id);
			if (order == null) {
				throw new CustomerException("Customer with entered id is not found. ");
			}
			order.setAmount(obj.getAmount());

			manage.merge(order);
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

	@Override
	public Boolean delete(int id) throws CustomerException {
		EntityManager manage = null;
		EntityTransaction trans = null;
		try {

			manage = CustomerUtil.getEntityManager();
			trans = manage.getTransaction();
			trans.begin();
			Orders order = manage.find(Orders.class, id);
			if (order == null) {
				throw new CustomerException("Customer with entered id is not found. ");
			}
			manage.remove(order);
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
