package com.indus.training.persist.impl;

import com.indus.training.persist.dao.IOrders;
import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.OrderException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OrderManager implements IOrders {

	private final EntityManagerFactory entityManagerFactory;

	public OrderManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public Boolean create(Orders order) throws OrderException {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(order);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new OrderException("Failed to create order", e);
		} finally {
			em.close();
		}
	}

	@Override
	public int read(int id) throws OrderException {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Orders order = em.find(Orders.class, id);
			if (order != null) {
				return order.getOrderid();
			} else {
				return 0; // Order not found
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderException("Failed to read order", e);
		} finally {
			em.close();
		}
	}

	@Override
	public Boolean update(int id, Orders updatedOrder) throws OrderException {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();

			Orders order = em.find(Orders.class, id);
			if (order != null) {
				order.setOrderdate(updatedOrder.getOrderdate());
				order.setTotalamount(updatedOrder.getTotalamount());
				em.merge(order);
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
			throw new OrderException("Failed to update order", e);
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

			Orders order = em.find(Orders.class, id);
			if (order != null) {
				em.remove(order);
				transaction.commit();
				return true;
			} else {
				return false; // Order not found
			}
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new OrderException("Failed to delete order", e);
		} finally {
			em.close();
		}
	}
}
