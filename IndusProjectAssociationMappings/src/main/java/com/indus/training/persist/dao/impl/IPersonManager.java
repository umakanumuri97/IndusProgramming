package com.indus.training.persist.dao.impl;

import com.indus.training.dao.IIPerson;
import com.indus.training.persist.entity.ImmutablePerson;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class IPersonManager implements IIPerson {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersonPU");

    @Override
    public Boolean create(ImmutablePerson person) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Failed to create ImmutablePerson: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public ImmutablePerson read(Long id) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(ImmutablePerson.class, id, LockModeType.NONE);
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean update(ImmutablePerson person) throws Exception {
        // Immutable entities typically do not support updates,
        // so we can throw an exception or handle it differently.
        throw new UnsupportedOperationException("ImmutablePerson cannot be updated.");
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            ImmutablePerson person = em.find(ImmutablePerson.class, id);
            if (person != null) {
                em.remove(person);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Failed to delete ImmutablePerson: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<ImmutablePerson> listAll() throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ImmutablePerson> query = em.createQuery("FROM ImmutablePerson", ImmutablePerson.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        entityManagerFactory.close();
    }
}
