package com.indus.training.persist.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistUtil {

    private static final String PERSISTENCE_UNIT_NAME = "staffPU";
    private static EntityManagerFactory entityManagerFactory;

    // Static block to initialize the EntityManagerFactory
    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Method to get the EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // Method to close the EntityManagerFactory
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
