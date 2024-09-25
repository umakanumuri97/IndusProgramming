package com.indus.training.persist.dao.impl;

import com.indus.training.dao.IPerson;
import com.indus.training.persist.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PersonManager implements IPerson {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersonPU");

    @Override
    public Boolean create(Person person) throws Exception {
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
            throw new Exception("Failed to create Person: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Person read(Long id) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean update(Person person) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Failed to update Person: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
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
            throw new Exception("Failed to delete Person: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> listAllPersons() throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        entityManagerFactory.close();
    }
}
