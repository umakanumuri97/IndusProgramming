package com.indus.training.persist.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

import com.indus.training.persist.entity.Spaceship;

public class SpaceshipManager {
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("PersonPU").createEntityManager();
	}

	public boolean create(Spaceship spaceShip) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(spaceShip);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public Spaceship read(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Spaceship.class, id);
		} finally {
			em.close();
		}
	}

	public boolean update(Spaceship spaceShip) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(spaceShip);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public boolean delete(Long id) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Spaceship spaceShip = em.find(Spaceship.class, id);
			if (spaceShip != null) {
				em.remove(spaceShip);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public List<Spaceship> getAllSpaceShips() {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT s FROM Spaceship s", Spaceship.class).getResultList();
		} finally {
			em.close();
		}
	}
}
