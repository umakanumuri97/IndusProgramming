package com.indus.training.persist.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerUtil {

	private static final EntityManagerFactory emf = createEntityManagerFactory();

	private static final Logger logger = LoggerFactory.getLogger(EntityManagerUtil.class);

	private EntityManagerUtil()
	{
		
	}
	public static EntityManagerFactory createEntityManagerFactory() {
		try {
			return Persistence.createEntityManagerFactory("myPersistenceUnit");
		} catch (Exception e) {
			logger.error("An exception occured while creating Entity Manager Factory");
			throw e;
		}
	}

	public static EntityManager getEntityManager() {
		try {
			return emf.createEntityManager();
		} catch (Exception e) {
			logger.error("An exception occured while creating Entity Manager");
			throw e;

		}
	}

	public static void closeEntityManager(EntityManager entityManager) {
		if (entityManager != null) {
			entityManager.close();
		}
	}

}
