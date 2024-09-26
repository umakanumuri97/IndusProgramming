package com.indus.training.persist.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.OrderException;
import com.indus.training.persist.impl.CustomerManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestCustomerManager {

    private CustomerManager cmobj = null;
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws OrderException {
        // Initialize EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("OrdersPU");
        cmobj = new CustomerManager(entityManagerFactory);
    }
    @Test
    public void testCreate() throws OrderException {
        // 1. Input
        Customer customer1 = new Customer();
        customer1.setCustomername("sai ram");
        customer1.setCustomeremail("sairam@gmail.com");

        Customer customer2 = new Customer();
        customer2.setCustomername("bhuvan");
        customer2.setCustomeremail("bhuvan@gmail.com");

        // 2. Test
        Boolean result1 = cmobj.create(customer1);
        Boolean result2 = cmobj.create(customer2);

        // 3. Verification
        assertTrue(result1);
        assertTrue(result2);
    }

//    @Test
 //   public void testRead() throws OrderException {
   //     // First create a customer so that it can be read later
//        Customer customer1 = new Customer();
//        customer1.setCustomername("sai ram");
//        customer1.setCustomeremail("sairam@gmail.com");
//
//        cmobj.create(customer1);

        // Assume the ID of the customer created is 1
//        Customer result = cmobj; // Expecting a Customer object
//        assertNotNull(result);
//    }

    @Test
    public void testUpdate() throws OrderException {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomername("sai");
        updatedCustomer.setCustomeremail("saibhuvan@gmail.com");

        // Test updating the customer with ID 1
        Boolean result = cmobj.update(1, updatedCustomer);
        assertTrue(result);
    }

    @Test
    public void testDelete() throws OrderException {
        // Test deleting the customer with ID 1
        Boolean result = cmobj.delete(1);
        assertTrue(result);
    }
}
