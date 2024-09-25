package com.indus.training.persist.dao.impl;

import com.indus.training.dao.ICourse;
import com.indus.training.persist.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CourseManager implements ICourse {

    private EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("PersonPU").createEntityManager();
    }

    @Override
    public Boolean create(Course course) throws Exception {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(course);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new Exception("Insert failed: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Course read(Long id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            return em.find(Course.class, id);
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
            Course course = em.find(Course.class, (long) id);
            if (course != null) {
                em.remove(course);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Course findByName(String title) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.title = :title", Course.class);
            query.setParameter("title", title);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Handle no result case as needed
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean update(Course course) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(course);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}
