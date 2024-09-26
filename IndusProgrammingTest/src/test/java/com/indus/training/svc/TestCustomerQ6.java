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

public class TestCustomerQ6 {

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
    public void testCreateCustomerOrder() throws JPAException {
        // Create a new customer
        Customer customer = new Customer();
        customer.setCustomerId(998);
        customer.setName("Bharat");
        customer.setEmail("bharat@yahoo.com");

        
        assertTrue("Customer should be created successfully", custObj.create(customer));

        
        Customer retrievedCustomer = custObj.read(998);
        assertNotNull("Customer should not be null after creation", retrievedCustomer);
        assertEquals( "Bharat", retrievedCustomer.getName());

        
        Order order1 = new Order();
        order1.setOrderId(1001); 
        order1.setTotalAmount(300.0);
        order1.setOrderDate(Date.valueOf("2024-09-25"));
        order1.setCustomer(retrievedCustomer);
        
        assertTrue("First order should be created successfully", ordObj.create(order1)); // Create first order
        
        Order order2 = new Order();
        order2.setOrderId(1002); 
        order2.setTotalAmount(200.0);
        order2.setOrderDate(Date.valueOf("2024-09-26"));
        order2.setCustomer(retrievedCustomer);
        
        assertTrue("Second order should be created successfully", ordObj.create(order2)); // Create second order

       

    }
}