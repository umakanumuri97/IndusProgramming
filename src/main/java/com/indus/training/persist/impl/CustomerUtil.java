package com.indus.training.persist.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Description: Utility class for maintaining JPA entity manager.
 */
public class CustomerUtil {

	// EntityManagerFactory is a Singleton for the application.
	private static EntityManagerFactory registry = buildEntityManagerFactory();

	/**
	 * Description: Builds the EntityManagerFactory by creating it from the
	 * persistence unit.
	 * 
	 * @return returns EntityMangerFactory instance.
	 */
	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			return Persistence.createEntityManagerFactory("test");
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Description: Obtains a new Entity Manager for the EntityManagerFactory
	 * 
	 * @return returns a Entity Manager Instance for the database operations.
	 */
	public static EntityManager getEntityManager() {
		return registry.createEntityManager();
	}

	/**
	 * Description: Closes the EntityManagerFactory, releases all the resources.
	 */
	public static void close() {
		if (registry != null) {
			registry.close();
		}
	}
}
