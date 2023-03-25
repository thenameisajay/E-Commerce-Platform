package com.stacs.group3.ShoppingSystemApp.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest {
    Cart cart1;
    Cart cart2;
    Cart cart3;
    Cart cart4;

    @BeforeEach
    public void setup() {
        cart1 = new Cart(1, "1", "js123",
                "Harry Potter: Philosopher's Stone", 12.99f,
                1, 12.99f, "mj456");

        cart2 = new Cart(2, "2", "js123",
                "A Man Called Ove", 6.99f,
                2, 13.98f, "mj456");

        cart3 = new Cart(3, "3", "js123",
                "Educated", 9.99f,
                10, 99.9f, "jj789");

        cart4 = new Cart(1, "1", "jh111",
                "Harry Potter: Philosopher's Stone", 12.99f,
                10, 129.9f, "mj456");
    }

    @Test
    public void testGet() {
        assertEquals(cart1.getCartId(), 1);
        assertEquals(cart1.getProductId(), "1");
        assertEquals(cart1.getCustomerName(), "js123");
        assertEquals(cart1.getProductName(), "Harry Potter: Philosopher's Stone");
        assertEquals(cart1.getProductPrice(), 12.99f);
        assertEquals(cart1.getProductQuantity(), 1);
        assertEquals(cart1.getProductTotal(), 12.99f);
        assertEquals(cart1.getSellerName(), "mj456");

        assertEquals(cart2.getCartId(), 2);
        assertEquals(cart2.getProductId(), "2");
        assertEquals(cart2.getCustomerName(), "js123");
        assertEquals(cart2.getProductName(), "A Man Called Ove");
        assertEquals(cart2.getProductPrice(), 6.99f);
        assertEquals(cart2.getProductQuantity(), 2);
        assertEquals(cart2.getProductTotal(), 13.98f);
        assertEquals(cart2.getSellerName(), "mj456");

        assertEquals(cart3.getCartId(), 3);
        assertEquals(cart3.getProductId(), "3");
        assertEquals(cart3.getCustomerName(), "js123");
        assertEquals(cart3.getProductName(), "Educated");
        assertEquals(cart3.getProductPrice(), 9.99f);
        assertEquals(cart3.getProductQuantity(), 10);
        assertEquals(cart3.getProductTotal(), 99.9f);
        assertEquals(cart3.getSellerName(), "jj789");

        assertEquals(cart4.getCartId(), 1);
        assertEquals(cart4.getProductId(), "1");
        assertEquals(cart4.getCustomerName(), "jh111");
        assertEquals(cart4.getProductName(), "Harry Potter: Philosopher's Stone");
        assertEquals(cart4.getProductPrice(), 12.99f);
        assertEquals(cart4.getProductQuantity(), 10);
        assertEquals(cart4.getProductTotal(), 129.9f);
        assertEquals(cart4.getSellerName(), "mj456");
    }

    @Test
    public void testSet() {
        cart4.setCartId(2);
        cart4.setProductId("2");
        cart4.setCustomerName("js123");
        cart4.setProductName("A Man Called Ove");
        cart4.setProductPrice(6.99f);
        cart4.setProductQuantity(5);
        cart4.setProductTotal(34.95f);
        cart4.setSellerName("jj789");

        assertEquals(cart4.getCartId(), 2);
        assertEquals(cart4.getProductId(), "2");
        assertEquals(cart4.getCustomerName(), "js123");
        assertEquals(cart4.getProductName(), "A Man Called Ove");
        assertEquals(cart4.getProductPrice(), 6.99f);
        assertEquals(cart4.getProductQuantity(), 5);
        assertEquals(cart4.getProductTotal(), 34.95f);
        assertEquals(cart4.getSellerName(), "jj789");
    }
}
