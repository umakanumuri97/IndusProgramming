package com.indus.training.persist.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProgrammingTestPU");
			return emf;
		} catch(Throwable ex) {
			System.out.println("Error Unable to create Entity Manager Factory");
			throw new ExceptionInInitializerError(ex);
			
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	public static void shutDown() {
		
		if(entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
	}
	

}
