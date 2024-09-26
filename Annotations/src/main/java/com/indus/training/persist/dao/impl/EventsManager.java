package com.indus.training.persist.dao.impl;

import com.indus.training.core.persist.dao.IEvents;
import com.indus.training.persist.entity.Events;
import com.indus.training.persist.exception.EventsException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class EventsManager implements IEvents {
	private static EntityManagerFactory entityManagerFactory = PersistUtil.getEntityManagerFactory();

	@Override
	public Boolean createEvent(Events event) throws EventsException {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(event); // Save the event to the database
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
		// TODO Auto-generated method stub

	}

	@Override
	public Events readEventById(int eventid) throws EventsException {
		EntityManager entityManager = null;
		Events event = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			event = entityManager.find(Events.class, eventid); // Retrieve event by ID
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close(); // Ensure the EntityManager is closed
			}
		}
		return event; // Return the found event or null if not found
	}
	// TODO Auto-generated method stub

	@Override
	public Boolean updateEvent(Events event) throws EventsException {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(event); // Update the event record
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

	// TODO Auto-generated method stub

	@Override
	public Boolean deleteEvent(int eventid) throws EventsException {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Events event = entityManager.find(Events.class, eventid); // Find the event by ID
			if (event != null) {
				entityManager.remove(event); // Delete the event from the database
				transaction.commit();
				return true; // Success
			} else {
				return false; // Event not found
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
		
		// TODO Auto-generated method stub
		
	}
}
