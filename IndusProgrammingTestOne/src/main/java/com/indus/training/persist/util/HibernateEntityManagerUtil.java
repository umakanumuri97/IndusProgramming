package com.indus.training.persist.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class HibernateEntityManagerUtil {
	
	private static EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
	
	public static EntityManager createEntityManager() {

		EntityManager em = entityManagerFactory.createEntityManager();
		return em;
	}
	
	public static void closeEntityManager(EntityManager em) {
		
		if(em!=null && em.isOpen()) {
			
			em.close();
		}
	}
	
	public static void beginTransaction(EntityManager em) {
		
		em.getTransaction().begin();
	}
	
	public static void rollBackTransaction(EntityManager em) {

		if(em.getTransaction().isActive()) {
			
			em.getTransaction().rollback();
		}
		
	}
	
	public static void commitTransaction(EntityManager em) {
		
		if(em.getTransaction().isActive()) {
			
			em.getTransaction().commit();
		}
		
	}

}
