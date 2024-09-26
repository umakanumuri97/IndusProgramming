package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.dao.impl.OrderManager;
import com.indus.training.persist.entity.CustomerEntity;
import com.indus.training.persist.entity.OrderEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;

public class TestOrderManager {

    OrderManager orderMngObj = null;

    @Before
    public void setUp() throws Exception {
        // Initialize OrderManager object before each test
        orderMngObj = new OrderManager();
    }

    @After
    public void tearDown() throws Exception {
        // Clean up the OrderManager object after each test
        orderMngObj = null;
    }

    @Test
    /**
     * Test Case Scenario to create an order with Id, Date, Amount
     *
     * @throws IndusProgrammingTestOneException
     */
    public void testCreateOrder() throws IndusProgrammingTestOneException {
        OrderEntity ordObj = new OrderEntity();
        CustomerEntity custObj = new CustomerEntity();
        // Step 1: Input
        ordObj.setOrderId(115);
        ordObj.setOrderDate(new Date()); 
        ordObj.setOrderTotalAmount(1500.75);
        // Step 2: Input Customer
        custObj.setCustomerId(1);
        ordObj.setCustomer(custObj);
        // Step 3: Expected Result
        Boolean expResult = true;
        // Step 4: Actual Result
        Boolean actResult = orderMngObj.createOrder(ordObj);
        // Step 5: Assertion
        assertEquals(expResult, actResult);
    }

    @Test
    /**
     * Test Case Scenario to update an existing order with new Date and Amount
     *
     * @throws IndusProgrammingTestOneException
     */
    public void testUpdateOrder() throws IndusProgrammingTestOneException {
        OrderEntity ordObj = new OrderEntity();
        // Step 1: Input
        ordObj.setOrderId(113);
        ordObj.setOrderDate(new Date());  // Using current date for the test
        ordObj.setOrderTotalAmount(3001.50);
        // Step 2: Expected Result
        Boolean expResult = true;
        // Step 3: Actual Result
        Boolean actResult = orderMngObj.updateOrder(ordObj);
        // Step 4: Assertion
        assertEquals(expResult, actResult);
    }

    @Test
    /**
     * Test Case Scenario to delete an order by order Id
     *
     * @throws IndusProgrammingTestOneException
     */
    public void testDeleteOrder() throws IndusProgrammingTestOneException {
        OrderEntity ordObj = new OrderEntity();
        // Step 1: Input
        ordObj.setOrderId(107);
        // Step 2: Expected Result
        Boolean expResult = true;
        // Step 3: Actual Result
        Boolean actResult = orderMngObj.deleteOrder(ordObj);
        // Step 4: Assertion
        assertEquals(expResult, actResult);
    }

    @Test
    /**
     * Test Case Scenario to retrieve all orders
     *
     * @throws IndusProgrammingTestOneException
     */
    public void testGetAllOrders() throws IndusProgrammingTestOneException {
        // Step 1: Get Orders List
        List<OrderEntity> ordersList = orderMngObj.getAllOrders();
        // Step 2: Assertion to check if the list is not null and not empty
        assertNotNull("Orders list should not be null", ordersList);
        assertFalse("Orders list should not be empty", ordersList.isEmpty());
    }

    @Test
    /**
     * Test Case Scenario to execute a custom JPQL query and retrieve the result as strings
     *
     * @throws IndusProgrammingTestOneException
     */
    public void testExecuteSpecificJPQLQuery() throws IndusProgrammingTestOneException {
        // Step 1: Define a JPQL query string
        String jpqlQuery = "SELECT o.orderTotalAmount FROM OrderEntity o WHERE o.orderTotalAmount > :orderTotalAmount";
        // Step 2: Execute the JPQL query
        double orderTotalAmount = 1000.00;
        List<Double> orderAmounts = orderMngObj.executeSpecificJPQLQuery(jpqlQuery, orderTotalAmount);
        // Step 3: Assertion to check if the result is not null and contains data
        assertNotNull("Order amounts list should not be null", orderAmounts);
        assertFalse("Order amounts list should not be empty", orderAmounts.isEmpty());
    }

}
