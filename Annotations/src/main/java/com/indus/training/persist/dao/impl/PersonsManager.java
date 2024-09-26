package com.indus.training.persist.dao.impl;

import com.indus.training.persist.entity.Persons;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public final class PersonsManager {

    private static EntityManagerFactory entityManagerFactory = PersistUtil.getEntityManagerFactory();

    // Create a new person
    public Boolean create(Persons person) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(person); // Save the person to the database
            transaction.commit();
            return true; // Success
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback on failure
            }
            System.out.println("Exception Caught:" + e.getMessage());
            return false; // Failure
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Ensure the EntityManager is closed
            }
        }
    }

    // Read a person by ID
    public Persons read(int personId) {
        EntityManager entityManager = null;
        Persons person = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            person = entityManager.find(Persons.class, personId); // Retrieve person by ID
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Ensure the EntityManager is closed
            }
        }
        return person; // Return the found person or null if not found
    }

    // Update a person
    public Boolean update(Persons person) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(person); // Update the person record
            transaction.commit();
            return true; // Success
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback on failure
            }
            e.printStackTrace();
            return false; // Failure
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Ensure the EntityManager is closed
            }
        }
    }

    // Delete a person by ID
    public Boolean delete(int personId) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Persons person = entityManager.find(Persons.class, personId); // Find the person by ID
            if (person != null) {
                entityManager.remove(person); // Delete the person from the database
                transaction.commit();
                return true; // Success
            } else {
                return false; // Person not found
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback on failure
            }
            e.printStackTrace();
            return false; // Failure
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Ensure the EntityManager is closed
            }
        }
    }
}
