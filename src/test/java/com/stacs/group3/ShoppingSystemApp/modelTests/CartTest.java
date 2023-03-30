package com.stacs.group3.ShoppingSystemApp.modelTests;

import com.stacs.group3.ShoppingSystemApp.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test for the Cart class.
 */
public class CartTest {
    private Cart cart1;
    private Cart cart2;
    private Cart cart3;
    private Cart cart4;

    /**
     * Initialise the carts before each test case.
     */
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

    /**
     * Test for the Get method.
     * Verifies that the data returned by the get method is the same as the data that was inserted
     */
    @Test
    public void testGet() {
        assertEquals(cart1.getCartID(), 1);
        assertEquals(cart1.getProductID(), "1");
        assertEquals(cart1.getCustomerName(), "js123");
        assertEquals(cart1.getProductName(), "Harry Potter: Philosopher's Stone");
        assertEquals(cart1.getProductPrice(), 12.99f);
        assertEquals(cart1.getProductQuantity(), 1);
        assertEquals(cart1.getProductTotal(), 12.99f);
        assertEquals(cart1.getSellerName(), "mj456");

        assertEquals(cart2.getCartID(), 2);
        assertEquals(cart2.getProductID(), "2");
        assertEquals(cart2.getCustomerName(), "js123");
        assertEquals(cart2.getProductName(), "A Man Called Ove");
        assertEquals(cart2.getProductPrice(), 6.99f);
        assertEquals(cart2.getProductQuantity(), 2);
        assertEquals(cart2.getProductTotal(), 13.98f);
        assertEquals(cart2.getSellerName(), "mj456");

        assertEquals(cart3.getCartID(), 3);
        assertEquals(cart3.getProductID(), "3");
        assertEquals(cart3.getCustomerName(), "js123");
        assertEquals(cart3.getProductName(), "Educated");
        assertEquals(cart3.getProductPrice(), 9.99f);
        assertEquals(cart3.getProductQuantity(), 10);
        assertEquals(cart3.getProductTotal(), 99.9f);
        assertEquals(cart3.getSellerName(), "jj789");

        assertEquals(cart4.getCartID(), 1);
        assertEquals(cart4.getProductID(), "1");
        assertEquals(cart4.getCustomerName(), "jh111");
        assertEquals(cart4.getProductName(), "Harry Potter: Philosopher's Stone");
        assertEquals(cart4.getProductPrice(), 12.99f);
        assertEquals(cart4.getProductQuantity(), 10);
        assertEquals(cart4.getProductTotal(), 129.9f);
        assertEquals(cart4.getSellerName(), "mj456");
    }

    /**
     * Test for the Set method.
     * Verifies that the data in the object is changed to the expected values after calling the set method
     */
    @Test
    public void testSet() {
        cart4.setCartID(2);
        cart4.setProductID("2");
        cart4.setCustomerName("js123");
        cart4.setProductName("A Man Called Ove");
        cart4.setProductPrice(6.99f);
        cart4.setProductQuantity(5);
        cart4.setProductTotal(34.95f);
        cart4.setSellerName("jj789");

        assertEquals(cart4.getCartID(), 2);
        assertEquals(cart4.getProductID(), "2");
        assertEquals(cart4.getCustomerName(), "js123");
        assertEquals(cart4.getProductName(), "A Man Called Ove");
        assertEquals(cart4.getProductPrice(), 6.99f);
        assertEquals(cart4.getProductQuantity(), 5);
        assertEquals(cart4.getProductTotal(), 34.95f);
        assertEquals(cart4.getSellerName(), "jj789");
    }
}
