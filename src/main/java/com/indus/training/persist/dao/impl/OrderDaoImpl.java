package com.indus.training.persist.dao.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indus.training.persist.dao.IOrderDao;
import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.OrderDaoException;
import com.indus.training.persist.util.EntityManagerUtil;

/**
 * This class implements the OrderDao interface to handle operations for Order
 * entities, such as inserting, fetching, deleting, and updating using JPA.
 */
public class OrderDaoImpl implements IOrderDao {

	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	/**
	 * Inserts a Order object into the database.
	 * 
	 * @param order the Order object to insert
	 * @return true if the order was inserted successfully, false otherwise
	 * @throws OrderDaoException if an error occurs during the insertion process
	 */
	@Override
	public Boolean insertOrder(Order order) throws OrderDaoException {
		if (order == null) {
			logger.error("Order Object Cannnot be null");
			throw new OrderDaoException("Order Object Cannot be null");
		}
		EntityManager entityManager = null;
		boolean isInserted = false;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(order);
			entityManager.getTransaction().commit();
			isInserted = true;
			logger.info("Order inserted successfully with id {}", order.getOrderId());
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while saving Order with id {} to database", order.getOrderId());
				entityManager.getTransaction().rollback();
			}
			throw new OrderDaoException("An error occured while saving Customer to database");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return isInserted;
	}

	/**
	 * Fetches a order record from the database based on the order ID.
	 * 
	 * @param orderId the ID of the order to fetch
	 * @return the Order object corresponding to the given order ID, or null if not
	 *         found
	 * @throws OrderDaoException if an error occurs during the fetch operation
	 */
	@Override
	public Order fetchOrder(Integer orderId) throws OrderDaoException {
		if (orderId == null) {
			logger.error("Order Id Cannnot be null");
			throw new OrderDaoException("Order Id Cannot be null");
		}
		EntityManager entityManager = null;
		Order order = null;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			order = entityManager.find(Order.class, orderId);
			if (order != null) {
				logger.info("Order with ID {} fetched successfully", orderId);
			} else {
				logger.info("Order with provided Id {} not found", orderId);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while fetching order with ID {}", orderId);
				entityManager.getTransaction().rollback();
			}
			throw new OrderDaoException("An error occured while fetching order");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return order;
	}

	/**
	 * Deletes a order record from the database based on the order ID.
	 * 
	 * @param orderId the ID of the order to delete
	 * @return true if the order was deleted successfully, false otherwise
	 * @throws OrderDaoException if an error occurs during the deletion process
	 */
	@Override
	public Boolean deleteOrder(Integer orderId) throws OrderDaoException {
		if (orderId == null) {
			logger.error("order Id Cannnot be null");
			throw new OrderDaoException("Order Id Cannot be null");
		}
		EntityManager entityManager = null;
		boolean isDeleted = false;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Order order = entityManager.find(Order.class, orderId);
			if (order != null) {
				entityManager.remove(order);
				isDeleted = true;
				entityManager.getTransaction().commit();
				logger.info("Order with ID {} deleted successfully", order.getOrderId());
			} else {
				logger.info("Order with provided Id {} not found", orderId);
			}

		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while deleting order with ID {}", orderId);
				entityManager.getTransaction().rollback();
			}
			throw new OrderDaoException("An error occured while deleting order");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return isDeleted;
	}

	/**
	 * Updates the order in the database.
	 * 
	 * @param orderId      the ID of the order to be updated
	 * @param updatedOrder the updated order object
	 * @return true if the order was updated successfully, false otherwise
	 * @throws OrderDaoException if an error occurs during the update process
	 */
	@Override
	public Boolean updateOrderById(Integer orderId, Order updatedOrder) throws OrderDaoException {
		if (orderId == null) {
			logger.error("Order Id Cannnot be null");
			throw new OrderDaoException("Order Id Cannot be null");
		}
		EntityManager entityManager = null;
		Boolean isUpdated = false;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Order order = entityManager.find(Order.class, orderId);
			if (order != null) {
				order.setOrderId(updatedOrder.getOrderId());
				order.setOrderDate(updatedOrder.getOrderDate());
				order.setCustomer(updatedOrder.getCustomer());
				order.setTotalAmount(updatedOrder.getTotalAmount());
				entityManager.getTransaction().commit();
				isUpdated = true;
				logger.info("Order with ID {} updated successfully", order.getOrderId());
			} else {
				logger.info("Order with provided Id {} not found", orderId);
			}
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while updating order with ID {}", orderId);
				entityManager.getTransaction().rollback();
			}
			throw new OrderDaoException("An error occured while updating order");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return isUpdated;
	}

	/**
	 * Updates the total amount order in the database.
	 * 
	 * @param order              the order for which total amount need to be updated
	 * @param updatedTotalAmount the amount with which it need to be updated
	 * @return true if updated successfully
	 * @throws OrderDaoException if an error occurs during the update process
	 */
	@Override
	public Boolean updateTotalAmountOfOrder(Order order, Double updatedTotalAmount) throws OrderDaoException {
		if (order == null) {
			logger.error("Order Object Cannnot be null");
			throw new OrderDaoException("Order Object Cannot be null");
		}

		EntityManager entityManager = null;
		Boolean isUpdated = false;
		try {
			entityManager = EntityManagerUtil.getEntityManager();
			entityManager.getTransaction().begin();
			order = entityManager.find(Order.class, order.getOrderId());
			if (order != null) {
				order.setTotalAmount(updatedTotalAmount);
				entityManager.getTransaction().commit();
				isUpdated = true;
				logger.info("Total Amount for Order with ID {} updated successfully", order.getOrderId());
			} else {
				logger.info("Order with provided Id {} not found");
			}
		} catch (Exception e) {
			if (entityManager != null && entityManager.getTransaction().isActive()) {
				logger.info("An error occured while updating total amount of  order with ID {}", order.getOrderId());
				entityManager.getTransaction().rollback();
			}
			throw new OrderDaoException("An error occured while updating order");
		} finally {
			entityManager.close();
			entityManager = null;

		}
		return isUpdated;

	}

}
