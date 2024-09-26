package com.indus.training.persist.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.indus.training.persist.dao.ICustomer;
import com.indus.training.persist.entity.CustomerEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;
import com.indus.training.persist.util.HibernateEntityManagerUtil;

public class CustomerManager implements ICustomer {

    @Override
    public Boolean createCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            em.persist(customer);
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error creating customer", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    @Override
    public Boolean updateCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            em.merge(customer);
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error updating customer", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    @Override
    public Boolean deleteCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException {
        Boolean result = false;
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            CustomerEntity foundCustomer = em.find(CustomerEntity.class, customer.getCustomerId());
            em.remove(foundCustomer);
            HibernateEntityManagerUtil.commitTransaction(em);
            result = true;
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error deleting customer", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return result;
    }

    @Override
    public List<CustomerEntity> getAllCustomers() throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<CustomerEntity> customers = null;
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            Query query = em.createQuery("From CustomerEntity");
            customers = query.getResultList();
            HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            HibernateEntityManagerUtil.rollBackTransaction(em);
            throw new IndusProgrammingTestOneException("Error retrieving customers", e);
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }
        return customers;
    }

    @Override
    public List<Object[]> executeSpecificJPQLQuery(String jpql) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<Object[]> results = null;
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            Query query = em.createQuery(jpql);
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
    public List<CustomerEntity> executeJPQLQuery(String jpql) throws IndusProgrammingTestOneException {
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        List<CustomerEntity> results = null;
        try {
            HibernateEntityManagerUtil.beginTransaction(em);
            Query query = em.createQuery(jpql);
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
