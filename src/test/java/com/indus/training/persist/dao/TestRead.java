package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.CustomerException;
import com.indus.training.persist.impl.QueryManager;

public class TestRead {

	private QueryManager queryObj = null;

	@Before
	public void setUp() throws Exception {
		queryObj = new QueryManager();
	}

	@After
	public void tearDown() throws Exception {

		queryObj = null;
	}

	@Test
	public void testReadInt() {
		try {
			// Expected Result
			Orders[] expResult = new Orders[2];
			for (int i = 0; i < 2; i++) {
				expResult[i] = new Orders();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Customer customer = new Customer();
			customer.setCustomerId(1);
			expResult[0].setOrderId(101);
			expResult[0].setOrderDate(sdf.parse("2024-09-01"));
			expResult[0].setAmount(100.50);
			expResult[0].setCustomerId(customer);

			expResult[1].setOrderId(102);
			expResult[1].setOrderDate(sdf.parse("2024-09-02"));
			expResult[1].setAmount(200.75);
			expResult[1].setCustomerId(customer);

			// Actual Result
			List<Orders> actResult = queryObj.read(1);
			Orders[] actualOrderArray = actResult.toArray(new Orders[0]);

			// Assertions
			assertNotNull(actResult);
			for (int i = 0; i < actResult.size(); i++) {
				assertEquals(expResult[0].getOrderId(), actualOrderArray[0].getOrderId());
				assertEquals(expResult[0].getOrderDate(), actualOrderArray[0].getOrderDate());
				// assertEquals(expResult[0].getAmount(), actualOrderArray[0].getAmount(), 0.0);
				assertEquals(expResult[0].getCustomerId(), actualOrderArray[0].getCustomerId());
			}

		} catch (CustomerException e) {
			System.out.println("Error in Read by id: " + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRead() {
		try {
			// ExpectedResult
			List<Object[]> expResult = new ArrayList<>();

			Object[] customer1 = new Object[] { "Alice", 2L, 301.25 };
			Object[] customer2 = new Object[] { "Bob", 1L, 50.00 };
			Object[] customer3 = new Object[] { "Charlie", 1L, 300.00 };

			expResult.add(customer1);
			expResult.add(customer2);
			expResult.add(customer3);

			// Actual Result
			List<Object[]> actResult = queryObj.read();

			// Assertion
			assertNotNull(actResult);
			for (int i = 0; i < expResult.size(); i++) {
				assertArrayEquals(expResult.get(i), actResult.get(i));
			}

		} catch (CustomerException e) {
			e.printStackTrace();
			fail("Should not throw CustomerException: " + e.getMessage());
		}
	}

}
