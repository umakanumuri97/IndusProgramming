package com.indus.training.persist.dao.impl;

import com.indus.training.dao.IEmployee;
import com.indus.training.persist.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class EmployeeManager implements IEmployee {

    private EntityManager getEntityManager() {
        return PersistenceUtil.getConnection().createEntityManager();
    }

    @Override
    public Boolean create(Employee employee) throws Exception {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new Exception("Insertion failed: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Employee read(int id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } catch (Exception e) {
            throw new Exception("Read failed: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean delete(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Employee findByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery(
                "SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving employee by name", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean update(Employee employee) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}
