package com.indus.training.persist.dao.impl;

import com.indus.training.dao.IDepartment;
import com.indus.training.persist.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DepartmentManager implements IDepartment {

    private EntityManager getEntityManager() {
        return PersistenceUtil.getConnection().createEntityManager();
    }

    @Override
    public Boolean create(Department department) throws Exception {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(department);
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
    public Department read(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Department.class, id);
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
            Department department = em.find(Department.class, id);
            if (department != null) {
                em.remove(department);
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
    public Department findByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Department> query = em.createQuery(
                "SELECT d FROM Department d WHERE d.name = :name", Department.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving department by name", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean update(Department department) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(department);
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
