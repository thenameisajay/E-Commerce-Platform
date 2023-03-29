package com.stacs.group3.ShoppingSystemApp.serviceTests;

import com.stacs.group3.ShoppingSystemApp.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the OrderService class.
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderService();

    /**
     * Initialise the orderService before each test.
     */
    @BeforeEach
    public void setup() {

        orderService.addOrder(1, "1", "Harry Potter: Philosopher's Stone",
                12.99f, 1, 12.99f, "mj456", "js123");
        orderService.addOrder(2, "3", "Educated",
                9.99f, 10, 99.9f, "jj789", "js123");
        orderService.addOrder(3, "2", "A Man Called Ove",
                6.99f, 100, 699f, "mj456", "hs123");
    }

    /**
     * Tests the addToCart method of OrderService class.
     * Ensures that the addOrder method adds the order successfully
     */
    @Test
    public void testAddOrder() {
        assertEquals(orderService.viewAllOrders(), 3);

        orderService.addOrder(4, "2", "A Man Called Ove",
                6.99f, 2, 13.98f, "mj456", "js123");

        assertEquals(orderService.viewAllOrders(), 4);

        orderService.addOrder(5, "2", "A Man Called Ove",
                6.99f, 2, 13.98f, "mj456", "js123");
        orderService.addOrder(6, "2", "A Man Called Ove",
                6.99f, 2, 13.98f, "mj456", "js123");

        assertEquals(orderService.viewAllOrders(), 6);
    }

    /**
     * Tests the getOrderByCustomer method of OrderService class.
     * Ensures that getOrderByCustomer method return the correct number of orders of given customer
     */
    @Test
    public void testGetOrderByCustomer() {
        assertEquals(orderService.getOrderByCustomer("js123").size(), 2);
        assertEquals(orderService.getOrderByCustomer("hs123").size(), 1);
    }

    /**
     * Tests the getOrderBySeller method of OrderService class.
     * Ensures that getOrderBySeller method return the correct number of orders of given seller
     */
    @Test
    public void testGetOrderBySeller() {
        assertEquals(orderService.getOrderBySeller("mj456").size(), 2);
        assertEquals(orderService.getOrderBySeller("jj789").size(), 1);
    }

    /**
     * Tests the viewAllOrders method of OrderService class.
     * Ensures that viewAllOrders method return the correct number of orders in the system
     */
    @Test
    public void testViewAllOrders() {
        assertEquals(orderService.viewAllOrders(), 3);
        orderService.wipeAll();
        assertEquals(orderService.viewAllOrders(), 0);


    }
}
