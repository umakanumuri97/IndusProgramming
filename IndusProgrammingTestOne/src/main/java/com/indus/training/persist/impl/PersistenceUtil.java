package com.indus.training.persist.impl;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceUtil {
		private static final String PERSISTENCE_UNIT_NAME = "OrdersPU";
	    private static EntityManagerFactory entityManagerFactory;

	    static {
	        try {
	            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        } catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static EntityManagerFactory getEntityManagerFactory() {
	        return entityManagerFactory;
	    }

	    public static void close() {
	        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
	            entityManagerFactory.close();
	        }
	    }
}
