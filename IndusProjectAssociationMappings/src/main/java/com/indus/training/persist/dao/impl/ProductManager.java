package com.indus.training.persist.dao.impl;

import com.indus.training.dao.IProduct;
import com.indus.training.persist.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductManager implements IProduct {
    
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersonPU");

    @Override
    public Boolean create(Product product) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.flush(); // Force the changes to be written to the database
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Failed to create Product: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Product read(Long id) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean update(Product product) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.flush(); // Ensure the changes are written to the database immediately
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Failed to update Product: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
                em.flush(); // Ensure deletion is reflected in the database immediately
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Failed to delete Product: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> listAll() throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        entityManagerFactory.close();
    }
}
