package com.indus.training.persist.dao;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.CustomerException;
import com.indus.training.persist.impl.CustomerManager;

public class TestCustomerManager {

	private CustomerManager custObj = null;

	@Before
	public void setUp() throws Exception {
		custObj = new CustomerManager();
	}

	@After
	public void tearDown() throws Exception {
		custObj = null;
	}

	@Test
	public void testInsert() {
		try {
			Customer[] customer = new Customer[3];
			for (int i = 0; i < 3; i++) {
				customer[i] = new Customer();
			}
			customer[0].setCustomerId(1);
			customer[0].setCustomerName("Alice");
			customer[0].setEmail("alice@example.com");

			customer[1].setCustomerId(2);
			customer[1].setCustomerName("Bob");
			customer[1].setEmail("bob@example.com");

			customer[2].setCustomerId(3);
			customer[2].setCustomerName("Charlie");
			customer[2].setEmail("charlie@example.com");
			Boolean exp = true;
			// Act Result
			Boolean[] act = new Boolean[3];
			for (int i = 0; i < 3; i++) {
				act[i] = custObj.insert(customer[i]);
				assertEquals(exp, act[i]);
			}
		} catch (CustomerException e) {
			System.out.println("Customer Exception: " + e.getMessage());
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
