package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.CustomerException;
import com.indus.training.persist.impl.OrderManager;

public class TestUpdation {

	private OrderManager orderObj = null;

	@Before
	public void setUp() throws Exception {
		orderObj = new OrderManager();
	}

	@After
	public void tearDown() throws Exception {
		orderObj = null;
	}
	@Test
	public void testUpdateTotalAmount() {
		try {
			
			// Inputs
			Orders obj1 = new Orders();
			obj1.setOrderId(103);
			obj1.setAmount(70.0);
			// Expected Result
			Boolean exp = true;
			// Actual Result
			Boolean act = orderObj.updateTotalAmount(103, obj1);
			// Assertions
			assertEquals(exp, act);
		} catch (CustomerException e) {
			System.out.println("Exception occurred during update test: " + e.getMessage());
		}
	}

}
