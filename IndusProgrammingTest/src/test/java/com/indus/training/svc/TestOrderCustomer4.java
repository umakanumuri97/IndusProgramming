package com.indus.training.svc;

import static org.junit.Assert.*;
import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.JPAException;
import com.indus.training.persist.impl.CustomerManager;
import com.indus.training.persist.impl.OrderManager;

public class TestOrderCustomer4 {
	
	private OrderManager ordObj;
	private CustomerManager custObj;

	@Before
	public void setUp() throws Exception {
		ordObj = new OrderManager();
		custObj = new CustomerManager();
		
	}

	@After
	public void tearDown() throws Exception {
		ordObj = null;
		custObj = null;
		
	}

	@Test
	public void testCreateOrder() throws JPAException {
		Customer customer = new Customer();
		customer.setCustomerId(92);
		customer.setName("Sumana");
		customer.setEmail("sumana@3123.com");
		custObj.create(customer);

		Order order = new Order();
		order.setOrderId(82);
		order.setTotalAmount(100.0);
		order.setOrderDate(Date.valueOf("2024-09-25"));
		order.setCustomer(custObj.read(92));
		assertTrue("Order should be created successfully", ordObj.create(order));
		
		System.out.println("testCreateOrder executed successfully.");
	}

	@Test
	public void testRead() throws JPAException {
		Order order = new Order();
		order.setOrderId(2);
		order.setTotalAmount(200.0);
		order.setOrderDate(Date.valueOf("2024-09-26"));
		order.setCustomer(custObj.read(1));
		ordObj.create(order);

		Order result1 = ordObj.read(2);

		assertEquals("Order total amount should match", 200.0, result1.getTotalAmount(), 0);
		System.out.println("testRead executed successfully.");
	}

	@Test
	public void testUpdate() throws JPAException {
		Order order = new Order();
		order.setOrderId(55);
		order.setTotalAmount(300.0);
		order.setOrderDate(Date.valueOf("2024-09-27"));
		order.setCustomer(custObj.read(55));
		ordObj.create(order);

		order.setTotalAmount(350.0);
		assertTrue("Order updated", ordObj.update(order));

		Order result2 = ordObj.read(55);
		assertEquals("Updated order total amount should match", 350.0, result2.getTotalAmount(), 0);
		System.out.println("testUpdate executed successfully.");
	}

	@Test
	public void testDelete() throws JPAException {
		Order order = new Order();
		order.setOrderId(4);
		order.setTotalAmount(400.0);
		order.setOrderDate(Date.valueOf("2024-09-28"));
		order.setCustomer(custObj.read(1));
		ordObj.create(order);

		assertTrue("Order deleted successfully", ordObj.delete(4));
		assertNull("Deleted not found", ordObj.read(4));
		System.out.println("testDelete executed successfully.");
	}
    @Test
    public void testDeleteCustomerWithOrders() throws JPAException {
       
        Customer customerWithOrder = new Customer();
        customerWithOrder.setCustomerId(101);
        customerWithOrder.setName("Customer has Order");
        customerWithOrder.setEmail("cust@123.com");
        custObj.create(customerWithOrder);


        Order order = new Order();
        order.setOrderId(200);
        order.setTotalAmount(150.0);
        order.setOrderDate(Date.valueOf("2024-09-26"));
        order.setCustomer(customerWithOrder);
        ordObj.create(order);

        
        custObj.deleteCustomer(101L);

        System.out.println("executed successfully");
    }

	
	}

