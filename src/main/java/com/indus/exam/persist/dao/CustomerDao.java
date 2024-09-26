package com.indus.exam.persist.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.indus.exam.persist.entity.Orders;
import com.indus.exam.persist.exception.CustomerException;
import com.indus.exam.persist.svc.ICustomerDao;
import com.indus.exam.persist.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;

public class CustomerDao implements ICustomerDao {

    // Update the total amount of an order and handle rollback in case of an error
    @Override
    public void updateOrderAmount(Long orderId, BigDecimal newAmount) throws CustomerException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Orders order = entityManager.find(Orders.class, orderId);
            if (order == null) {
                throw new CustomerException("Order not found", null); // Throw exception if order is not found
            }
            order.setTotalAmount(newAmount);

            // Persist changes and commit
            entityManager.persist(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback in case of any exception
            }
            throw new CustomerException("Order Update Failed", e);
        } finally {
            entityManager.close(); 
        }
    }

    // Retrieve all orders for a given customer using JPQL 
    @Override
    public List<Orders> findOrdersByCustomerId(Long customerId) throws CustomerException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Orders> orders = null;
        try {
            String jpql = "SELECT o FROM Orders o WHERE o.customer.customerId = :customerId";
            TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
            query.setParameter("customerId", customerId);
            orders = query.getResultList();
        } catch (Exception e) {
            throw new CustomerException("Failed to retrieve orders for customer", e);
        } finally {
            entityManager.close(); 
        }
        return orders;
    }

    // Retrieve all customer names, total number of orders, and sum of total_amount for each customer
    @Override
    public List<Object[]> getCustomerOrderStatistics() throws CustomerException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Object[]> stats = null;
        try {
            String jpql = "SELECT c.name, COUNT(o), SUM(o.totalAmount) FROM Customer c LEFT JOIN c.orders o GROUP BY c.name";
            TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
            stats = query.getResultList();
        } catch (Exception e) {
            throw new CustomerException("Failed to retrieve customer order statistics", e);
        } finally {
            entityManager.close(); 
        }
        return stats;
    }
}
