package com.indus.training.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.impl.CustomerManager;

public class TestCustomer {
	private CustomerManager cmObj = null;

	@Before
	public void setUp() throws Exception {
		cmObj = new CustomerManager();
	}

	@After
	public void tearDown() throws Exception {
		cmObj = null;
	}

	@Test
	public void testCreate() {
		try {
			//1.Input
			Customer customer = new Customer();
			customer.setCustomerId(4);
			customer.setCustomerName("Lahari");
			customer.setEmail("lahari@gmail.com");
			
			//ActualResult
			Boolean actResult = cmObj.create(customer);
			//Verify that the customer was successfully created
			assertTrue("Successfully inserted customer" , actResult);
		}catch(Exception e) {
			e.getStackTrace();
		}

	}

	@Test
	public void testUpdate() {
		try {
			//Input
			Customer customer = new Customer();
			customer.setCustomerName("Sarat");
			customer.setEmail("sarat@gmail.com");
			//ActualResult
			Boolean actResult = cmObj.update(1, customer);
			//Verify that the customer was successfully created
			assertTrue("Successfully deleted", actResult);
			
			}catch(Exception e) {
				e.getStackTrace();
			}
    }

	@Test
	public void testDelete() {
		try {
			 // Call the delete method with a specific customer ID
			Boolean actResult = cmObj.delete(2);
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	@Test
	public void testGetCustomerOrders() {
	   try {
		   List<Object[]> statistics = cmObj.getCustomerOrders();

		    assertNotNull(statistics);
		    assertFalse(statistics.isEmpty());

		    for (Object[] stat : statistics) {
		        assertEquals(3, stat.length);
		    }
	   }catch(Exception e) {
		   e.getStackTrace();
	   }
	}


}
