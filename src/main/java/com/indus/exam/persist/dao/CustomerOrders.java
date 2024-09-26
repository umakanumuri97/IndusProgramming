package com.indus.exam.persist.dao;

import java.util.List;

import com.indus.exam.persist.entity.Orders;
import com.indus.exam.persist.entity.Customer;
import com.indus.exam.persist.excp.CustomerOrdersException;
import com.indus.exam.persist.model.ICustomerOrders;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CustomerOrders implements ICustomerOrders {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomerOrdersTransaction");

	
	public Boolean deleteCustomer(int cId) throws CustomerOrdersException {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		
		try {
			
			tx = em.getTransaction();
			tx.begin();
			
			Customer existingCustomer = em.find(Customer.class, cId);
			if (existingCustomer == null) {
				throw new CustomerOrdersException("No record found for the given customerID");
            }
			
			em.remove(existingCustomer);
			
			tx.commit();
			return true;
		} catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }   
		
	}

	
	public Boolean updateOrder(int oId, double totAmount) throws CustomerOrdersException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		 try {
	            tx = em.getTransaction();
	            tx.begin();

	            // Find the existing customer
	            Orders existingOrder = em.find(Orders.class, oId);
	            if (existingOrder == null) {
	            	throw new CustomerOrdersException("No record found for the given orderID");
	            }

	            // Update employee details
	            existingOrder.setTotalAmount(totAmount);
	            em.merge(existingOrder);
	            tx.commit();
	            return true;

	        } catch (Exception e) {
	            if (tx != null && tx.isActive()) {
	                tx.rollback();
	            }
	            e.printStackTrace();
	            return false;
	        } finally {
	            em.close();
	        }
	}

	
	public List<Orders> retrieveOrdersforCustomer(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
            tx.begin();
            
            // JPQL query to fetch all the orders for a given customer ID
            String jpql = "SELECT o FROM Orders o WHERE o.customer.customerId = 1";
            Query query = em.createQuery(jpql);
            List<Orders> existingOrders = query.getResultList();
            
            
            tx.commit();
            return existingOrders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
		
	}

	
	public List<Object[]> calculateTotalOrdersAndTotalAmountForCustomer() {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
            tx.begin();
            
            // JPQL query to retrieve all Customer names along with the total number of Orders and the sum of total_amount for each Customer.
            String jpql = "SELECT c.name, COUNT(o.orderId), SUM(o.totalAmount) " +
                    "FROM Customer c " +
                    "LEFT JOIN c.orders o " +
                    "GROUP BY c.name";

            Query query = em.createQuery(jpql);
            List<Object[]> custOrdersData = query.getResultList();
            
            
            tx.commit();
            return custOrdersData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
		
	}
	

	

}
