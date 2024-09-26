package com.indus.training.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.impl.OrderManager;

public class TestOrder {

    private OrderManager omObj = null;

    @Before
    public void setUp() throws Exception {
        omObj = new OrderManager();
    }

    @After
    public void tearDown() throws Exception {
        omObj = null;
    }

    @Test
    public void testCreate() {
        try {
            // 1. Input
            Orders order = new Orders();
            order.setOrderId(106); // Ensure this ID doesn't conflict with existing records
            order.setOrderDate(Date.valueOf("2024-11-11"));
            order.setTotalAmount(100.0);

            Boolean result = omObj.create(order);
            assertTrue("Successfully created", result);

        } catch (Exception e) {
            fail("Exception occurred during order creation: " + e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        try {
            // Use an existing order ID for the update test
            Orders order = new Orders();
            order.setOrderId(101); // Ensure this ID exists in the database
            order.setOrderDate(Date.valueOf("2024-11-12")); // Updated date
            order.setTotalAmount(150.0); // Updated amount

            Boolean actResult = omObj.update(101, order);
            assertTrue("Successfully updated", actResult);
        } catch (Exception e) {
            fail("Exception occurred during order update: " + e.getMessage());
        }
    }

    @Test
    public void testRead() {
        try {
            // Read all orders instead of filtering by customerId
            List<Orders> orders = omObj.read(1); // Ensure this method reads all orders
            assertNotNull("Order list should not be null", orders);
            assertFalse("Order list should not be empty", orders.isEmpty());
        } catch (Exception e) {
            fail("Exception occurred during order read: " + e.getMessage());
        }
    }
}
