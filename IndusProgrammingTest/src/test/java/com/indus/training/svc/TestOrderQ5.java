package com.indus.training.svc;

import static org.junit.Assert.*;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.JPAException;
import com.indus.training.persist.impl.CustomerManager;
import com.indus.training.persist.impl.OrderManager;

public class TestOrderQ5 {
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
    public void testGetOrderByCustomerId() throws JPAException {
        // Set up test data
        Customer customer = new Customer();
        customer.setCustomerId(12); 
        customer.setName("Sam");
        customer.setEmail("sam@gmail.com");
        custObj.create(customer); 


        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setTotalAmount(100.0);
        order1.setOrderDate(Date.valueOf("2024-09-25"));
        order1.setCustomer(customer);
        ordObj.create(order1); 

        Order order2 = new Order();
        order2.setOrderId(2);
        order2.setTotalAmount(200.0);
        order2.setOrderDate(Date.valueOf("2024-09-26"));
        order2.setCustomer(customer);
        ordObj.create(order2); 


        List<Order> orders = ordObj.getOrderByCustomerId(12); 


        assertNotNull("Order list should not be null", orders);
        assertEquals("Should retrieve 2 orders for the customer", 2, orders.size());


        assertEquals("First order total amount should match", 100.0, orders.get(0).getTotalAmount(), 0);
        assertEquals("Second order total amount should match", 200.0, orders.get(1).getTotalAmount(), 0);
    }
}
