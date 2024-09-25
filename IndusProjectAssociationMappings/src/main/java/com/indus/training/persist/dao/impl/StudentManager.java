
package com.indus.training.persist.dao.impl;

import com.indus.training.dao.IStudent;
import com.indus.training.persist.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class StudentManager implements IStudent {

	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("PersonPU").createEntityManager();
	}

	@Override
	public Boolean create(Student student) throws Exception {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(student);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw new Exception("Insert failed: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public Student read(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Student.class, (long) id);
		} finally {
			em.close();
		}
	}

	@Override
	public Boolean delete(int id) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			Student student = em.find(Student.class, (long) id);
			if (student != null) {
				em.remove(student);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public Student findByName(String name) {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class);
			query.setParameter("name", name);
			return query.getSingleResult();
		} catch (Exception e) {
			return null; // Handle no result case as needed
		} finally {
			em.close();
		}
	}

	@Override
	public Boolean update(Student student) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(student);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			return false;
		} finally {
			em.close();
		}
	}

}
