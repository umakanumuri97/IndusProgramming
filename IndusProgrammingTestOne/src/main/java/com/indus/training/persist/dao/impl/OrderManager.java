package com.indus.training.persist.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.indus.training.persist.dao.IOrders;
import com.indus.training.persist.entity.OrderEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;
import com.indus.training.persist.util.HibernateEntityManagerUtil;

public class OrderManager implements IOrders {

    @Override
    public Boolean createOrder(OrderEntity order) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            em.persist(order);
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error creating order", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    @Override
    public Boolean updateOrder(OrderEntity order) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            em.merge(order);
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error updating order", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    @Override
    public Boolean deleteOrder(OrderEntity order) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            OrderEntity foundOrder = em.find(OrderEntity.class, order.getOrderId());
            em.remove(foundOrder);
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error deleting order", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    @Override
    public List<OrderEntity> getAllOrders() throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<OrderEntity> orders = null;
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            Query query = em.createQuery("From OrderEntity");
            orders = query.getResultList();
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error retrieving orders", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return orders;
    }

    @Override
    public List<Double> executeSpecificJPQLQuery(String jpql, double orderTotalAmount) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<Double> results = null;
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            Query query = em.createQuery(jpql);
            query.setParameter("orderTotalAmount", orderTotalAmount);
            results = query.getResultList();
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error executing JPQL query", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return results;
    }

    @Override
    public List<OrderEntity> executeJPQLQuery(String jpql, int customerId) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<OrderEntity> results = null;
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            Query query = em.createQuery(jpql);
            query.setParameter("customerId", customerId);
            results = query.getResultList();
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error executing JPQL query", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return results;
    }
}
