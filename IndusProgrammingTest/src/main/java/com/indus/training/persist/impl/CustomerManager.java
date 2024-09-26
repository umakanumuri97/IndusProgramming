package com.indus.training.persist.impl;

import java.util.List;
import com.indus.training.persist.dao.ICustomer;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.JPAException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CustomerManager implements ICustomer {
/**
 * Method to create customer
 */
    @Override
    public Boolean create(Customer customer) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JPAException("Failed to create: " + e.getMessage());
        } finally {
            em.close();
        }
    }
/**
 * Method to read
 */
    @Override
    public Customer read(int customerId) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            return em.find(Customer.class, customerId);
        } finally {
            em.close();
        }
    }
/**
 * Method to update
 */
    @Override
    public Boolean update(Customer customer) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JPAException("Failed to update: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    /**
     * Method to delete
     */

    @Override
    public Boolean delete(int customerId) throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, customerId);
            if (customer != null) {
                em.remove(customer);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JPAException("Failed to delete: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    /**
     * Method to not delete orders linked to a Customer 
     * @param customerId
     * @throws JPAException
     */
    public void deleteCustomer(Long customerId) throws JPAException {
    	EntityManager em=PersistenceUtil.getConnection().createEntityManager();
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) {
            throw new JPAException("Customer not found");
        }
        
        
        if (!customer.getOrders().isEmpty()) {
            throw new JPAException("Cannot delete customer with existing orders");
        }

        em.remove(customer);
    }
    /**
     * Method to retrieve customer by summary
     * @return
     * @throws JPAException
     */

    public List<Object[]> getCustomerOrderSummary() throws JPAException {
        EntityManager em = PersistenceUtil.getConnection().createEntityManager();
        try {
            String jpql = "SELECT c.name, COUNT(o.orderId), SUM(o.totalAmount) " +
                           "FROM Customer c " +
                           "LEFT JOIN c.orders o " +
                           "GROUP BY c.name";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            throw new JPAException("Failed to retrieve customer orders: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
