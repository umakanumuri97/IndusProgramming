package com.indus.training.persist.svc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.indus.training.dao.IProduct;
import com.indus.training.persist.dao.impl.ProductManager;
import com.indus.training.persist.entity.Product;

import java.util.List;

public class TestProduct {

    private IProduct productManager;

    @Before
    public void setUp() throws Exception {
        productManager = new ProductManager();
    }

    @After
    public void tearDown() throws Exception {
       
    }

    @Test
    public void testCreateAndReadProduct() throws Exception {
        Product product = new Product("Laptop", 1200.00);
        boolean created = productManager.create(product);
        assertTrue("Product should be created", created);

        Product retrievedProduct = productManager.read(product.getId());
        assertNotNull("Retrieved product should not be null", retrievedProduct);
        assertEquals("Laptop", retrievedProduct.getName());
        assertEquals(1200.00, retrievedProduct.getPrice(), 0);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product("Tablet", 300.00);
        productManager.create(product);

        // Update the price
        product.setPrice(350.00);
        boolean updated = productManager.update(product);
        assertTrue("Product should be updated", updated);

        Product updatedProduct = productManager.read(product.getId());
        assertEquals(350.00, updatedProduct.getPrice(), 0);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product("Phone", 800.00);
        productManager.create(product);

        boolean deleted = productManager.delete(product.getId());
        assertTrue("Product should be deleted", deleted);

        Product retrievedProduct = productManager.read(product.getId());
        assertNull("Retrieved product should be null after deletion", retrievedProduct);
    }

    @Test
    public void testListAllProducts() throws Exception {
        productManager.create(new Product("TV", 500.00));
        productManager.create(new Product("Speaker", 150.00));

        List<Product> products = productManager.listAll();
        assertFalse("List should not be empty", products.isEmpty());
        assertEquals(2, products.size());
    }
}
