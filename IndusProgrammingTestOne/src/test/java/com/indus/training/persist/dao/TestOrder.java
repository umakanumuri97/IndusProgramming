package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.impl.OrderManager;
import com.indus.training.persist.exception.OrderException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class TestOrder {

    private OrderManager omobj;
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        // Initialize EntityManagerFactory and OrderManager
        entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");  // Replace with your persistence unit name
        omobj = new OrderManager(entityManagerFactory);
    }

    @After
    public void tearDown() throws Exception {
        if (omobj != null) {
            omobj = null;
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testCreate() {
        // Create a new Order
        Orders order = new Orders();
        order.setOrderdate(02-25-2024);
        order.setTotalamount(150);  // Set the order amount

        try {
            Boolean isCreated = omobj.create(order);
            assertTrue("Order should be created successfully", isCreated);
        } catch (OrderException e) {
            e.printStackTrace();
            fail("Order creation failed: " + e.getMessage());
        }
    }

    @Test
    public void testRead() {
        try {
            int orderId = 1; 
            Orders orderupdatedOrder = omobj.update(orderId);
            assertNotNull("Order should be found", orderId);
            assertEquals("Order ID should match", orderId, orderupdatedOrder.getOrderid());
        } catch (OrderException e) {
            e.printStackTrace();
            fail("Order read failed: " + e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        try {
            int orderId = 1;  // Assuming Order with ID 1 exists
            Orders updatedOrder = new Orders();
            updatedOrder.setTotalamount(200);  // Update the order amount

            Boolean isUpdated = omobj.update(orderId, updatedOrder);
            assertTrue("Order should be updated successfully", isUpdated);
        } catch (OrderException e) {
            e.printStackTrace();
            fail("Order update failed: " + e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        try {
            int orderId = 1;  // Assuming Order with ID 1 exists
            Boolean isDeleted = omobj.delete(orderId);
            assertTrue("Order should be deleted successfully", isDeleted);
        } catch (OrderException e) {
            e.printStackTrace();
            fail("Order delete failed: " + e.getMessage());
        }
    }
}
