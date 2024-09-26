package com.indus.exam.persist.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil{
	private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
	    try {
	        return Persistence.createEntityManagerFactory("customerss");
	    } catch (Throwable ex) {
	        // Log the exception to understand the root cause
	        ex.printStackTrace(); // Optionally replace with a logger
	        throw new ExceptionInInitializerError(ex);
	    }
	}

	
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
		
	}
	
	
}