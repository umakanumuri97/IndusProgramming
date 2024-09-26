package com.indus.training.persist.dao;

import com.indus.training.persist.dao.impl.CustomerDaoImpl;
import com.indus.training.persist.dao.impl.OrderDaoImpl;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.CustomerDaoException;
import com.indus.training.persist.exception.OrderDaoException;

import junit.framework.TestCase;

/**
 * Test Case for OrderDaoImpl class
 */
public class TestOrderDaoImpl extends TestCase {

	private CustomerDaoImpl customerDaoImplObj = null;
	private OrderDaoImpl orderDaoImplObj = null;

	/**
	 * Sets Up the Test environment
	 */
	protected void setUp() throws Exception {
		orderDaoImplObj = new OrderDaoImpl();
		customerDaoImplObj = new CustomerDaoImpl();
	}

	/**
	 * Tears down the Test Environment
	 */
	protected void tearDown() throws Exception {
		orderDaoImplObj = null;
		customerDaoImplObj = null;
	}

	/**
	 * Test case for insertOrder method
	 */
	public void testInsertOrder() {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			customerDaoImplObj.insertCustomer(customer);
			Order order = new Order();
			order.setOrderId(101);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			order.setOrderDate(date);
			order.setTotalAmount(200.00);
			order.setCustomer(customer);
			Boolean isInserted = orderDaoImplObj.insertOrder(order);
			assertTrue("Order should be inserted successfully", isInserted);
			customerDaoImplObj.deleteCustomer(customer.getCustomerId());
			orderDaoImplObj.deleteOrder(order.getOrderId());
		} catch (OrderDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());

		}
	}

	/**
	 * Test Case for fetch Order method
	 */
	public void testFetchOrder() {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			customerDaoImplObj.insertCustomer(customer);
			Order order = new Order();
			order.setOrderId(101);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			order.setOrderDate(date);
			order.setTotalAmount(200.00);
			order.setCustomer(customer);
			orderDaoImplObj.insertOrder(order);
			Order retrievedOrder = orderDaoImplObj.fetchOrder(order.getOrderId());
			assertNotNull(retrievedOrder);
			customerDaoImplObj.deleteCustomer(customer.getCustomerId());
		} catch (OrderDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());

		}
	}

	/**
	 * Test Case for Delete Order method
	 */
	public void testDeleteOrder() {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			customerDaoImplObj.insertCustomer(customer);
			Order order = new Order();
			order.setOrderId(101);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			order.setOrderDate(date);
			order.setTotalAmount(200.00);
			order.setCustomer(customer);
			orderDaoImplObj.insertOrder(order);
			Boolean isDeleted = orderDaoImplObj.deleteOrder(order.getOrderId());
			assertTrue("Order should be deleted successfully", isDeleted);
			customerDaoImplObj.deleteCustomer(customer.getCustomerId());
		} catch (OrderDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());

		}
	}

	/**
	 * Test Case for UpdateOrderById method
	 */
	public void testUpdateOrderById() {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			customerDaoImplObj.insertCustomer(customer);
			Order order = new Order();
			order.setOrderId(101);
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			order.setOrderDate(date);
			order.setTotalAmount(200.00);
			order.setCustomer(customer);
			orderDaoImplObj.insertOrder(order);
			Order updatedOrder = new Order();
			updatedOrder.setOrderId(101);
			long updatedMillis = System.currentTimeMillis();
			java.sql.Date updatedDate = new java.sql.Date(updatedMillis);
			updatedOrder.setOrderDate(updatedDate);
			updatedOrder.setTotalAmount(250.00);
			updatedOrder.setCustomer(customer);
			Boolean isUpdated = orderDaoImplObj.updateOrderById(order.getOrderId(), updatedOrder);
			assertTrue("Order should be updated successfully", isUpdated);
			customerDaoImplObj.deleteCustomer(customer.getCustomerId());
			orderDaoImplObj.deleteOrder(order.getOrderId());
		} catch (OrderDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());

		}
	}

}
