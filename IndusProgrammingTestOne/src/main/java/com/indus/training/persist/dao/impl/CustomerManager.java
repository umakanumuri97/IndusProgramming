package com.indus.training.persist.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.indus.training.persist.dao.ICustomer;
import com.indus.training.persist.entity.CustomerEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;
import com.indus.training.persist.util.HibernateEntityManagerUtil;

public class CustomerManager implements ICustomer {

    /**
     * This method is used to create a new customer record in the database.
     *
     * @param customer the customer entity to be created
     * @return true if the customer is successfully created, false otherwise
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public Boolean createCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Persist the customer entity
            em.persist(customer);

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error creating customer", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    /**
     * This method is used to update an existing customer record in the database.
     *
     * @param customer the customer entity with updated values
     * @return true if the customer is successfully updated, false otherwise
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public Boolean updateCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Find the existing customer by ID
            CustomerEntity custObj = em.find(CustomerEntity.class, customer.getCustomerId());

            // Update customer details
            custObj.setCustomerName(customer.getCustomerName());
            custObj.setCustomerEmail(customer.getCustomerEmail());

            // Merge the updated customer entity
            em.merge(custObj);

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error updating customer", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    /**
     * This method is used to delete an existing customer record from the database.
     *
     * @param customer the customer entity to be deleted
     * @return true if the customer is successfully deleted, false otherwise
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public Boolean deleteCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Find the customer by ID
            CustomerEntity foundCustomer = em.find(CustomerEntity.class, customer.getCustomerId());

            // Remove the customer entity
            em.remove(foundCustomer);

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error deleting customer", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    /**
     * This method retrieves all customer records from the database.
     *
     * @return a list of all CustomerEntity objects
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public List<CustomerEntity> getAllCustomers() throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<CustomerEntity> customers = null;
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Create a JPQL query to retrieve all customers
            Query query = em.createQuery("FROM CustomerEntity");

            // Execute the query and retrieve the result list
            customers = query.getResultList();

            // Commit the transaction
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error retrieving customers", e);
        } finally {
            // Ensure the EntityManager is closed
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return customers;
    }

    /**
     * This method executes a JPQL query and returns a list of results as an array of objects.
     *
     * @param jpql the JPQL query to be executed
     * @return a list of query results
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public List<Object[]> executeSpecificJPQLQuery(String jpql) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<Object[]> results = null;
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Create and execute the JPQL query
            Query query = em.createQuery(jpql);
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
     * This method executes a JPQL query and returns a list of customer entities.
     *
     * @param jpql the JPQL query to be executed
     * @return a list of CustomerEntity objects
     * @throws IndusProgrammingTestOneException if there is an error during the transaction
     */
    @Override
    public List<CustomerEntity> executeJPQLQuery(String jpql) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<CustomerEntity> results = null;
        try {
            // Start the transaction
            HibernateEntityManagerUtil.beginTransaction(em);

            // Create and execute the JPQL query
            Query query = em.createQuery(jpql);
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
