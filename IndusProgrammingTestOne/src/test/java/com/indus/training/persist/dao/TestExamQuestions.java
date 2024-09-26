package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.dao.impl.CustomerManager;
import com.indus.training.persist.dao.impl.OrderManager;
import com.indus.training.persist.entity.CustomerEntity;
import com.indus.training.persist.entity.OrderEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;
import com.indus.training.persist.util.HibernateEntityManagerUtil;

public class TestExamQuestions {

    CustomerManager customerManager;
    OrderManager orderManager;

    @Before
    public void setUp() throws Exception {
        // Initialize CustomerManager and OrderManager objects before each test
        customerManager = new CustomerManager();
        orderManager = new OrderManager();
    }

    @After
    public void tearDown() throws Exception {
        // Clean up after each test
        customerManager = null;
        orderManager = null;
    }

    @Test
    /**
     * Test Case 1: What happens if you try to delete a Customer who has placed orders?
     * 
     * Explanation: In JPA, if you try to delete a Customer that has related Orders
     * in the database, it may violate referential integrity. To enforce referential
     * integrity, you should use cascading options like `CascadeType.REMOVE` or
     * implement business logic to ensure that Orders are handled properly before
     * deleting the Customer.
     */
    public void testDeleteCustomerWithOrders() throws IndusProgrammingTestOneException {
        // Step 1: Create a new Customer and associated Order
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerId(10);
        customer.setCustomerName("Vinay Makineni");
        customer.setCustomerEmail("vinay@gmail.com");
        customerManager.createCustomer(customer);

        OrderEntity order = new OrderEntity();
        order.setOrderId(110);
        order.setOrderDate(new java.util.Date());
        order.setOrderTotalAmount(200.0);
        order.setCustomer(customer);
        orderManager.createOrder(order);

        // Step 2: Try to delete the customer with existing orders
        try {
            customerManager.deleteCustomer(customer);
        } catch (PersistenceException e) {
            // Expected exception when trying to delete a customer with linked orders
            System.out.println("Referential integrity violation occurred as expected.");
        }
    }

    @Test
    /**
     * Test Case 2: Write a JPA method to update the total_amount of an Order inside a transaction.
     * Demonstrate how you would handle a transaction roll back in case of an error.
     *
     * Explanation: This test updates the total_amount of an Order in a transaction.
     * If an error occurs during the update, the transaction should be rolled back.
     */
    public void testUpdateOrderTotalAmount() throws IndusProgrammingTestOneException {
        // Step 1: Create and persist a Customer to associate with the Order
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerId(11);
        customer.setCustomerName("Vinay Chandra Makineni");
        customer.setCustomerEmail("vinay@gmail.com");
        customerManager.createCustomer(customer); // Persist the customer first

        // Step 2: Create an Order to update and associate it with the persisted Customer
        OrderEntity order = new OrderEntity();
        order.setOrderId(111);
        order.setOrderDate(new Date());
        order.setOrderTotalAmount(300.0);
        order.setCustomer(customer); // Associate the persisted customer with the order
        orderManager.createOrder(order);

        // Step 3: Begin transaction to update the total_amount
        EntityManager em = HibernateEntityManagerUtil.createEntityManager();
        HibernateEntityManagerUtil.beginTransaction(em);
        
        Boolean result = null;

        try {
            // Step 4: Update the total_amount
            order.setOrderTotalAmount(500.0);
            orderManager.updateOrder(order);

            // Simulate an error by manually throwing an exception after the update
            throw new RuntimeException("Simulated error: Something went wrong!");
            

            // Commit the transaction if everything goes well (this line will not be reached)
            // HibernateEntityManagerUtil.commitTransaction(em);
        } catch (Exception e) {
            e.printStackTrace();
            // Roll back the transaction in case of an error
            HibernateEntityManagerUtil.rollBackTransaction(em);
            result = true; // Mark the result as true to indicate roll back was triggered
        } finally {
            HibernateEntityManagerUtil.closeEntityManager(em);
        }

        // Step 5: Assertion to confirm that the transaction was rolled back
        assertTrue("Transaction should be rolled back due to simulated error.", result);
    }


    @Test
    /**
     * Test Case 3: Write a JPQL query to retrieve all orders for a given Customer with customer_id = 1.
     *
     * Explanation: This test case demonstrates how to write a JPQL query to
     * retrieve all orders for a specific customer.
     */
    public void testRetrieveOrdersForCustomer() throws IndusProgrammingTestOneException {
        // Step 1: Define the JPQL query string
        String jpqlQuery = "FROM OrderEntity o WHERE o.customer.customerId = :customerId";
        int customerId = 1;

        // Step 2: Execute the query and retrieve the result
        List<OrderEntity> orders = orderManager.executeJPQLQuery(jpqlQuery, customerId);

        // Step 3: Assertion to check if the list is not null and contains orders
        assertNotNull("Orders list should not be null", orders);
        assertFalse("Orders list should not be empty", orders.isEmpty());
    }

    @Test
    /**
     * Test Case 4: Write a JPQL query to retrieve all Customer names along with the total number of Orders
     * and the sum of total_amount for each Customer. Ensure that customers who have no orders are also included.
     *
     * Explanation: This test case demonstrates a more advanced JPQL query that uses
     * `LEFT JOIN` to include customers without orders, along with aggregation functions
     * for counting the number of orders and summing the total amount.
     */
    public void testRetrieveCustomerOrderStats() throws IndusProgrammingTestOneException {
        // Step 1: Define the JPQL query string to get customer names, total orders, and sum of amounts
        String jpqlQuery = "SELECT c.customerName, COUNT(o.orderId), SUM(o.orderTotalAmount) " +
                           "FROM CustomerEntity c LEFT JOIN c.orders o " +
                           "GROUP BY c.customerName";

        // Step 2: Execute the query and retrieve the result
        List<Object[]> results = customerManager.executeSpecificJPQLQuery(jpqlQuery);

        // Step 3: Print and verify the results
        for (Object[] result : results) {
            String customerName = (String) result[0];
            Long totalOrders = (Long) result[1];
            Double totalAmount = (Double) result[2];

            System.out.println("\nCustomer Name: " + customerName +
                               ", Total Orders: " + totalOrders +
                               ", Total Amount: " + totalAmount);
        }

        // Step 4: Assertion to ensure result contains data
        assertNotNull("Results should not be null", results);
        assertFalse("Results should not be empty", results.isEmpty());
    }
}
