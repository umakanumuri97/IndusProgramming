package com.indus.exam.persist.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.indus.exam.persist.dao.CustomerOrders;
import com.indus.exam.persist.entity.Orders;
import com.indus.exam.persist.excp.CustomerOrdersException;

class TestCustomerOrders {
	
	private CustomerOrders coObj = null;

	@BeforeEach
	void setUp() throws Exception {
		coObj = new CustomerOrders();
	}

	@AfterEach
	void tearDown() throws Exception {
		coObj = null;
	}

	@Test
	void testdeleteCustomer() {
		try {
			Boolean result = coObj.deleteCustomer(3);
			assertTrue(result);
		} catch (CustomerOrdersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testupdateOrder() {
		try {
			Boolean result = coObj.updateOrder(101, 150.50);
			assertTrue(result);
		} catch (CustomerOrdersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testretrieveOrdersforCustomer() {
		
		List<Orders> allOrders = coObj.retrieveOrdersforCustomer();
		System.out.println("Retrieving the Order IDs for all the orders for the given Customer ID");
		for (Orders ord : allOrders) {
            System.out.println("Order_ID: \n" + ord.getOrderId());
        }
	}
	
	
	@Test
	void testcalculateTotalOrdersAndTotalAmountForCustomer() {
		
		List<Object[]> custOrdersData = coObj.calculateTotalOrdersAndTotalAmountForCustomer();
		System.out.println("Retrieving the customer Orders' Data for all the Customers");
		for (Object[] d : custOrdersData) {
            String customerName = (String) d[0];
            Integer totalOrders = (Integer) d[1];
            Double sumOfTotalAmount = (Double) d[2];

            System.out.println("customerName: " + customerName +
                               ", totalOrders: " + totalOrders +
                               ", sumOfTotalAmount: " + sumOfTotalAmount);
        }
	}
	
	
	
	
	

}
