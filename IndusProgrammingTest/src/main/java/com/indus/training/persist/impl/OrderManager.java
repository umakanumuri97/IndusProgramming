package com.indus.training.persist.impl;

import java.util.List;
import com.indus.training.persist.dao.IOrder;
import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.JPAException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class OrderManager implements IOrder {
/**
 * Method to create
 */
    @Override
    public Boolean create(Order order) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(order); 
            em.getTransaction().commit();
            return true; 
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }
            throw new JPAException("Failed to create order: " + e.getMessage());
        } finally {
            em.close(); 
        }
    }

    /**
     * Method to read
     */
    @Override
    public Order read(int orderId) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            return em.find(Order.class, orderId); 
            } finally {
            em.close();         }
    }
/**
 * Method to update
 */
    @Override
    public Boolean update(Order order) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            em.getTransaction().begin();
            Order existingOrder = em.find(Order.class, order.getOrderId());
            if (existingOrder != null) {
                // Update order properties
                existingOrder.setTotalAmount(order.getTotalAmount());
                existingOrder.setOrderDate(order.getOrderDate());
                existingOrder.setCustomer(order.getCustomer());
                em.getTransaction().commit();
                return true; 
            } else {
                return false; 
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }
            throw new JPAException("Failed to update order: " + e.getMessage());
        } finally {
            em.close();
        }
    }
/**
 * MEthod to delete
 */
    @Override
    public Boolean delete(int orderId) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                em.remove(order); 
                em.getTransaction().commit();
                return true; 
            } else {
                return false; 
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }
            throw new JPAException("Failed to delete order: " + e.getMessage());
        } finally {
            em.close(); 
        }
    }
/**
 * Method to fetch customers with jpql
 * @param customerId 
 * @return
 * @throws JPAException
 */
    public List<Order> getOrderByCustomerId(int customerId) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try { 
            String jpql = "SELECT o FROM Order o WHERE o.customer.customerId = :customerId"; 
            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("customerId", customerId);
            return query.getResultList(); 
        } catch (Exception e) {
            throw new JPAException("Failed to retrieve orders: " + e.getMessage());
        } finally {
            em.close(); 
        }
    }
}
