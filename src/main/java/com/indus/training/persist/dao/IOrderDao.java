package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.OrderDaoException;

/**
 * Interface for managing Order data access operations. Provides methods to
 * insert, fetch, delete, and update Order records.
 */
public interface IOrderDao {

	/**
	 * Inserts a Order object into the database.
	 * 
	 * @param order the Order object to insert
	 * @return true if the order was inserted successfully, false otherwise
	 * @throws OrderDaoException if an error occurs during the insertion process
	 */
	public Boolean insertOrder(Order order) throws OrderDaoException;

	/**
	 * Fetches a order record from the database based on the order ID.
	 * 
	 * @param orderId the ID of the order to fetch
	 * @return the Order object corresponding to the given order ID, or null if not
	 *         found
	 * @throws OrderDaoException if an error occurs during the fetch operation
	 */
	public Order fetchOrder(Integer orderId) throws OrderDaoException;

	/**
	 * Deletes a order record from the database based on the order ID.
	 * 
	 * @param orderId the ID of the order to delete
	 * @return true if the order was deleted successfully, false otherwise
	 * @throws OrderDaoException if an error occurs during the deletion process
	 */
	public Boolean deleteOrder(Integer orderId) throws OrderDaoException;

	/**
	 * Updates the order in the database.
	 * 
	 * @param orderId      the ID of the order to be updated
	 * @param updatedOrder the updated order object
	 * @return true if the order was updated successfully, false otherwise
	 * @throws OrderDaoException if an error occurs during the update process
	 */
	public Boolean updateOrderById(Integer orderId, Order updatedOrder) throws OrderDaoException;

	/**
	 * Updates the total amount order in the database.
	 * 
	 * @param order              the order for which total amount need to be updated
	 * @param updatedTotalAmount the amount with which it need to be updated
	 * @return true if updated successfully
	 * @throws OrderDaoException if an error occurs during the update process
	 */
	public Boolean updateTotalAmountOfOrder(Order order, Double updatedTotalAmount) throws OrderDaoException;

}
