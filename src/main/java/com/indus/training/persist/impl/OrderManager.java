package com.indus.training.persist.impl;



import java.util.List;

import com.indus.training.persist.dao.IOrder;
import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.JPAException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class OrderManager implements IOrder {
	/**
	 * Creates a new customer in the database.
	 */
	public Boolean create(Orders order) throws JPAException {
	    EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction tr = null;
	    try {
	        tr = em.getTransaction();
	        tr.begin();
	        em.merge(order); // Use merge to avoid conflicts
	        tr.commit();
	        return true;
	    } catch (Exception e) {
	        if (tr != null) {
	            tr.rollback();
	        }
	        throw new JPAException("Failed to create order: " + e.getMessage(), e);
	    } finally {
	        em.close();
	    }
	}

	/**
	 * Updates an existing customer in the database.
	 */
    @Override
    public Boolean update(int orderId, Orders updateOrders) throws JPAException {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tr = null;
        try {
            tr = em.getTransaction();
            tr.begin();
            Orders order = em.find(Orders.class, orderId);
            if (order != null) {
                order.setOrderDate(updateOrders.getOrderDate());
                order.setTotalAmount(updateOrders.getTotalAmount());
                // If necessary, you can set other fields from updateOrders
                tr.commit();  // Commit only if update is successful
                return true;  // Return true on successful update
            } else {
                throw new JPAException("Order not found with ID: " + orderId);
            }
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new JPAException("Error updating order: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
/**
 * To read the orders
 */
    @Override
    public List<Orders> read(int customerId) throws JPAException {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.customerId = :customerId", Orders.class);
            query.setParameter("customerId", customerId);
            return query.getResultList();  
        } catch (Exception e) {
            throw new JPAException("Error retrieving orders for customer: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
