package com.stacs.group3.ShoppingSystemApp.modelTests;

import com.stacs.group3.ShoppingSystemApp.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test for the Order class.
 */
public class OrderTest {
    private Order order1;
    private Order order2;
    private Order order3;

    /**
     * Initialise the orders before each test.
     */
    @BeforeEach
    public void setup() {
        order1 = new Order(1, "1", "Harry Potter: Philosopher's Stone",
                12.99f, 1, 12.99f, "mj456", "js123");
        order2 = new Order(2, "2", "A Man Called Ove",
                6.99f, 2, 13.98f, "mj456", "js123");
        order3 = new Order(1, "3", "Educated",
                9.99f, 10, 99.9f, "jj789", "js123");
    }

    /**
     * Test for the Get method.
     * Verifies that the data returned by the get method is the same as the data that was inserted
     */
    @Test
    public void testGet() {
        assertEquals(order1.getOrderID(), 1);
        assertEquals(order1.getProductID(), "1");
        assertEquals(order1.getProductName(), "Harry Potter: Philosopher's Stone");
        assertEquals(order1.getProductPrice(), 12.99f);
        assertEquals(order1.getProductQuantity(), 1);
        assertEquals(order1.getProductTotal(), 12.99f);
        assertEquals(order1.getSellerName(), "mj456");
        assertEquals(order1.getCustomerName(), "js123");

        assertEquals(order2.getOrderID(), 2);
        assertEquals(order2.getProductID(), "2");
        assertEquals(order2.getProductName(), "A Man Called Ove");
        assertEquals(order2.getProductPrice(), 6.99f);
        assertEquals(order2.getProductQuantity(), 2);
        assertEquals(order2.getProductTotal(), 13.98f);
        assertEquals(order2.getSellerName(), "mj456");
        assertEquals(order2.getCustomerName(), "js123");

        assertEquals(order3.getOrderID(), 1);
        assertEquals(order3.getProductID(), "3");
        assertEquals(order3.getProductName(), "Educated");
        assertEquals(order3.getProductPrice(), 9.99f);
        assertEquals(order3.getProductQuantity(), 10);
        assertEquals(order3.getProductTotal(), 99.9f);
        assertEquals(order3.getSellerName(), "jj789");
        assertEquals(order3.getCustomerName(), "js123");
    }

    /**
     * Test for the Set method.
     * Verifies that the data in the object is changed to the expected values after calling the set method
     */
    @Test
    public void testSet() {
        order1.setOrderID(3);
        order1.setProductID("3");
        order1.setProductName("Educated");
        order1.setProductPrice(9.99f);
        order1.setProductQuantity(5);
        order1.setProductTotal(49.95f);
        order1.setSellerName("xs123");
        order1.setCustomerName("ss111");

        assertEquals(order1.getOrderID(), 3);
        assertEquals(order1.getProductID(), "3");
        assertEquals(order1.getProductName(), "Educated");
        assertEquals(order1.getProductPrice(), 9.99f);
        assertEquals(order1.getProductQuantity(), 5);
        assertEquals(order1.getProductTotal(), 49.95f);
        assertEquals(order1.getSellerName(), "xs123");
        assertEquals(order1.getCustomerName(), "ss111");
    }
}
