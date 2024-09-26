package com.indus.training.persist.impl;

import java.util.List;

import com.indus.training.persist.dao.IQueryManager;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.CustomerException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

public class QueryManager implements IQueryManager {

	@Override
	public List<Orders> read(int id) throws CustomerException {
		List<Orders> result = null;
		EntityManager manage = null;
		try {

			manage = CustomerUtil.getEntityManager();

			String jpql = "SELECT o FROM Orders o WHERE o.customer.customerId = :customerId";
			TypedQuery<Orders> query = manage.createQuery(jpql, Orders.class);
			query.setParameter("customerId", id);
			result = query.getResultList();

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
	public List<Object[]> read() throws CustomerException {
		List<Object[]> result = null;
		EntityManager manage = null;
		try {

			manage = CustomerUtil.getEntityManager();

			String jpql = "SELECT c.customerName, COUNT(o), SUM(o.amount) " +
                       "FROM Customer c LEFT JOIN c.orders o " +
                       "GROUP BY c.customerName";
        result = manage.createQuery(jpql, Object[].class).getResultList();

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

}
