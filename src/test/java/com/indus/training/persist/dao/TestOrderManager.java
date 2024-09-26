package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.CustomerException;
import com.indus.training.persist.impl.OrderManager;

public class TestOrderManager {
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
	public void testInsert() {
		try {
			Orders[] orders = new Orders[4];
			for (int i = 0; i < 4; i++) {
				orders[i] = new Orders();
			}

			Customer[] customer = new Customer[3];
			for (int i = 0; i < 3; i++) {
				customer[i] = new Customer();
				customer[i].setCustomerId(i + 1);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			orders[0].setOrderId(101);
			orders[0].setAmount(100.50);
			orders[0].setOrderDate(sdf.parse("2024-09-01"));
			orders[0].setCustomerId(customer[0]);

			orders[1].setOrderId(102);
			orders[1].setAmount(200.75);
			orders[1].setOrderDate(sdf.parse("2024-09-02"));
			orders[1].setCustomerId(customer[0]);

			orders[2].setOrderId(103);
			orders[2].setAmount(50.00);
			orders[2].setOrderDate(sdf.parse("2024-09-03"));
			orders[2].setCustomerId(customer[1]);

			orders[3].setOrderId(104);
			orders[3].setAmount(300.00);
			orders[3].setOrderDate(sdf.parse("2024-09-04"));
			orders[3].setCustomerId(customer[2]);

			Set<Orders> order1 = new HashSet<>();
			order1.add(orders[0]);
			order1.add(orders[1]);
			customer[0].setOrders(order1);

			Set<Orders> order2 = new HashSet<>();
			order2.add(orders[2]);
			customer[1].setOrders(order2);

			Set<Orders> order3 = new HashSet<>();
			order3.add(orders[3]);
			customer[2].setOrders(order3);
			Boolean exp = true;
			// Act Result
			Boolean[] act = new Boolean[4];
			for (int i = 0; i < 4; i++) {
				act[i] = orderObj.insert(orders[i]);
				assertEquals(exp, act[i]);
			}
		} catch (CustomerException e) {
			System.out.println("Customer Exception: " + e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
