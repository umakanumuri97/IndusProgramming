package com.indus.exam.persist.testCutomer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.exam.persist.dao.CustomerDao;
import com.indus.exam.persist.entity.Orders;
import com.indus.exam.persist.exception.CustomerException;
import com.indus.exam.persist.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class testCustomerDao {

    private EntityManager entityManager;
    private CustomerDao dao;

    @Before
    public void setUp() {
        entityManager = HibernateUtil.getEntityManager();
        dao = new CustomerDao();
    }

    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
    }

    //4) Test updating the total amount of an order and handle rollback in case of an error
    @Test
    public void testUpdateOrderAmount() {
        // Expected Output
        BigDecimal expectedAmount = new BigDecimal("150.00");
        
        try {
            // Perform update
            dao.updateOrderAmount(103L, expectedAmount);

            // Actual Output
            Orders updatedOrder = entityManager.find(Orders.class, 103L);
            BigDecimal actualAmount = updatedOrder.getTotalAmount();
            
            // Assertions
            assertEquals(expectedAmount, actualAmount);
        } catch (CustomerException e) {
            fail("Transaction Failed update order amount" + e.getMessage());
        }
    }

    //4) Test rollback functionality by throwing a forced error
    @Test
    public void testUpdateOrderAmountWithRollback() {
        try {
            // the below order is is not there so it throws error
            dao.updateOrderAmount(1L, new BigDecimal("150.00"));
            fail("exception thrown");
        } catch (CustomerException e) {
            // Expected exception
            String expectedMessage = "Order Update Failed";
            String actualMessage = e.getMessage();
            
            // Assertions
            assertEquals(expectedMessage, actualMessage);
        }
    }

    //5) Test retrieving all orders for Customer with customer_id = 1 
    @Test
    public void testFindOrdersByCustomerId() {
        // Expected Output
        int expectedSize = 2;
        BigDecimal expectedFirstOrderAmount = new BigDecimal("150.00");
        BigDecimal expectedSecondOrderAmount = new BigDecimal("150.00");

        try {
            // Actual Output
            List<Orders> orders = dao.findOrdersByCustomerId(1L);
            int actualSize = orders.size();
            BigDecimal actualFirstOrderAmount = orders.get(0).getTotalAmount();
            BigDecimal actualSecondOrderAmount = orders.get(1).getTotalAmount();

            // Assertions
            assertEquals(expectedSize, actualSize);
            assertEquals(expectedFirstOrderAmount, actualFirstOrderAmount);
            assertEquals(expectedSecondOrderAmount, actualSecondOrderAmount);
        } catch (CustomerException e) {
            fail("orders recieve failed" + e.getMessage());
        }
    }

    //6) Test retrieving all Customer names, total number of Orders, and sum of total_amount
    @Test
    public void testGetCustomerOrderStatistics() {
        // Expected Output
        int expectedCustomerCount = 3;

        try {
            // Retrieve statistics for customers and their orders
            List<Object[]> stats = dao.getCustomerOrderStatistics();

            // Actual Output
            int actualCustomerCount = stats.size();

            // Assertions
            assertEquals(expectedCustomerCount, actualCustomerCount);

            for (Object[] stat : stats) {
                String name = (String) stat[0];
                Long orderCount = (Long) stat[1];
                BigDecimal totalAmount = (BigDecimal) stat[2];

                switch (name) {
                    case "Alice":
                        assertEquals(Long.valueOf(2), orderCount);
                        assertEquals(new BigDecimal("300.00"), totalAmount); 
                        break;
                    case "Bob":
                        assertEquals(Long.valueOf(1), orderCount); 
                        assertEquals(new BigDecimal("150.00"), totalAmount); 
                        break;
                    case "Charlie":
                        assertEquals(Long.valueOf(1), orderCount); 
                        assertEquals(new BigDecimal("300.00"), totalAmount); 
                        break;
                }
            }
        } catch (CustomerException e) {
            fail("getting customer details failed"+e.getMessage());
        }
    }
}
