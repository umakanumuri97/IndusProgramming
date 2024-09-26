package com.indus.training.persist.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.indus.training.persist.dao.IOrders;
import com.indus.training.persist.entity.OrderEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;
import com.indus.training.persist.util.HibernateEntityManagerUtil;

public class OrderManager implements IOrders {

    /**
     * This method is used to create a new order record in the database.
     *
     * @param order the order entity to be created
     * @return true if the order is successfully created, false otherwise
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public Boolean createOrder(OrderEntity order) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Persist the order entity
            em.persist(order);

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error creating order", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    /**
     * This method is used to update an existing order record in the database.
     *
     * @param order the order entity with updated values
     * @return true if the order is successfully updated, false otherwise
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public Boolean updateOrder(OrderEntity order) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Find the existing order by ID
            OrderEntity ordObj = em.find(OrderEntity.class, order.getOrderId());

            // Update order details
            ordObj.setOrderDate(order.getOrderDate());
            ordObj.setOrderTotalAmount(order.getOrderTotalAmount());

            // Merge the updated order entity
            em.merge(ordObj);

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error updating order", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    /**
     * This method is used to delete an existing order record from the database.
     *
     * @param order the order entity to be deleted
     * @return true if the order is successfully deleted, false otherwise
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public Boolean deleteOrder(OrderEntity order) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Find the order by ID
            OrderEntity foundOrder = em.find(OrderEntity.class, order.getOrderId());

            // Remove the order entity
            em.remove(foundOrder);

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error deleting order", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    /**
     * This method retrieves all order records from the database.
     *
     * @return a list of all OrderEntity objects
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public List<OrderEntity> getAllOrders() throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<OrderEntity> orders = null;
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Create a JPQL query to retrieve all orders
            Query query = em.createQuery("FROM OrderEntity");

            // Execute the query and retrieve the result list
            orders = query.getResultList();

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error retrieving orders", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return orders;
    }

    /**
     * This method executes a JPQL query to retrieve a list of order amounts greater than a given total.
     *
     * @param jpql the JPQL query to be executed
     * @param orderTotalAmount the threshold amount for the query
     * @return a list of order total amounts that meet the criteria
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public List<Double> executeSpecificJPQLQuery(String jpql, double orderTotalAmount) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<Double> results = null;
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Create the JPQL query and set the parameter
            Query query = em.createQuery(jpql);
            query.setParameter("orderTotalAmount", orderTotalAmount);
            results = query.getResultList();

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error executing JPQL query", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return results;
    }

    /**
     * This method executes a JPQL query to retrieve a list of orders for a specific customer.
     *
     * @param jpql the JPQL query to be executed
     * @param customerId the customer ID to filter the orders
     * @return a list of OrderEntity objects that belong to the specified customer
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public List<OrderEntity> executeJPQLQuery(String jpql, int customerId) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<OrderEntity> results = null;
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Create the JPQL query and set the parameter
            Query query = em.createQuery(jpql);
            query.setParameter("customerId", customerId);
            results = query.getResultList();

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error executing JPQL query", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return results;
    }
}
