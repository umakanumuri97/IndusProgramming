package com.indus.training.persist.impl;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * PersistenceUtil class
 */
public class PersistenceUtil {
	
	private static final String PERSISTENCE_UNIT_NAME = "OrderPU";
    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // To get connection
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // to close connection
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}
