
package com.indus.training.persist.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceUtil {

	private static final String PERSISTENCE_UNIT_NAME = "PersonPU";
	private static EntityManagerFactory entityManagerFactory;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		} catch (Throwable ex) {
			ex.getMessage();
		}
	}

	public static EntityManagerFactory getConnection() {
		return entityManagerFactory;
	}

	public static void close() {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

}


