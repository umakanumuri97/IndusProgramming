package com.indus.training.persist.dao;

import com.indus.training.persist.dao.impl.CustomerDaoImpl;
import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.CustomerDaoException;

import junit.framework.TestCase;

/**
 * Provides Test Cases for CustomerDaoImpl class
 */
public class TestCustomerDaoImpl extends TestCase {

	private CustomerDaoImpl customerDaoImplObj = null;

	/**
	 * Sets Up the test environment
	 */
	protected void setUp() throws Exception {
		customerDaoImplObj = new CustomerDaoImpl();
	}

	/**
	 * Tears down the test environment
	 */
	protected void tearDown() throws Exception {
		customerDaoImplObj = null;
	}

	/**
	 * Test case for insertCustomer method
	 */
	public void testInsertCustomer() {
		try {

			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			Boolean isInserted = customerDaoImplObj.insertCustomer(customer);
			assertTrue("Customer should be inserted successfully", isInserted);
			customerDaoImplObj.deleteCustomer(customer.getCustomerId());
		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	/**
	 * Test Case for fetchCustomer method
	 */
	public void testFetchCustomer() {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			customerDaoImplObj.insertCustomer(customer);
			Customer retrievedCustomer = customerDaoImplObj.fetchCustomer(customer.getCustomerId());
			assertNotNull("Customer should not be null", retrievedCustomer);
			assertEquals(1, retrievedCustomer.getCustomerId().intValue());
			assertEquals("Navya", retrievedCustomer.getName());
			assertEquals("navyabade7@gmail.com", retrievedCustomer.getEmail());
			customerDaoImplObj.deleteCustomer(customer.getCustomerId());
		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}
	}

	/**
	 * Test Case for deleteCustomer method
	 */
	public void testDeleteCustomer() {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			customerDaoImplObj.insertCustomer(customer);
			Boolean isDeleted = customerDaoImplObj.deleteCustomer(customer.getCustomerId());
			assertTrue("Customer should be deleted successfully", isDeleted);
			Customer deletedCustomer = customerDaoImplObj.fetchCustomer(customer.getCustomerId());
			assertNull("Customer should be null after deletion", deletedCustomer);
		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}

	}

	/**
	 * Test Case for Update Customer By Id method
	 */
	public void testUpdateCustomerById() {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(1);
			customer.setEmail("navyabade7@gmail.com");
			customer.setName("Navya");
			customerDaoImplObj.insertCustomer(customer);
			Customer updatedCustomer = new Customer();
			updatedCustomer.setCustomerId(1);
			updatedCustomer.setName("Navya");
			updatedCustomer.setEmail("nbade@gmail.com");
			Boolean isUpdated = customerDaoImplObj.updateCustomerById(customer.getCustomerId(), updatedCustomer);
			assertTrue("Customer should be updated successfully", isUpdated);
			Customer retrievedCustomer = customerDaoImplObj.fetchCustomer(updatedCustomer.getCustomerId());
			assertEquals(updatedCustomer.getCustomerId().intValue(), retrievedCustomer.getCustomerId().intValue());
			assertEquals(updatedCustomer.getEmail(), retrievedCustomer.getEmail());
			assertEquals(updatedCustomer.getName(), retrievedCustomer.getName());
			customerDaoImplObj.deleteCustomer(customer.getCustomerId());

		} catch (CustomerDaoException e) {
			fail("Exception should not be thrown: " + e.getMessage());
		}

	}

	
	
	
	
	
	
}
