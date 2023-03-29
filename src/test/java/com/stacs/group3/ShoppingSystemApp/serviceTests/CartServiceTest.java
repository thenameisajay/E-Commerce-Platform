package com.stacs.group3.ShoppingSystemApp.serviceTests;


import com.stacs.group3.ShoppingSystemApp.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the CartService class.
 */
public class CartServiceTest {
    private CartService cartService = new CartService();

    /**
     * Initialise the cartService before each test.
     */
    @BeforeEach
    public void setup() {
        cartService.addToCart(100, "1", "js123",
                "Harry Potter: Philosopher's Stone", 12.99f,
                1, 12.99f, "mj456");

        cartService.addToCart(101, "2", "js123",
                "A Man Called Ove", 6.99f,
                2, 13.98f, "mj456");

        cartService.addToCart(102, "3", "js123",
                "Educated", 9.99f,
                10, 99.9f, "jj789");

        cartService.addToCart(103, "1", "jh111",
                "Harry Potter: Philosopher's Stone", 12.99f,
                10, 129.9f, "mj456");
    }

    /**
     * Tests the viewCart method of CartService class.
     * Ensures that viewCart returns the correct number of carts for each user
     */
    @Test
    public void testViewCart() {
        assertEquals(cartService.viewCart("js123").size(), 3);
        assertEquals(cartService.viewCart("jh111").size(), 1);
        assertEquals(cartService.viewCart("jj111").size(), 0);
    }

    /**
     * Tests the addToCart method of CartService class.
     * Ensures that the addToCart method throws an exception if the product already exists in the cart
     * or if the productID is null of if the product quantity is 0
     * Ensures that the addToCart method adds the product to the cart successfully if it does not already exist.
     */
    @Test
    public void testAddCart() {
        assertThrows(IllegalArgumentException.class,
                () -> cartService.addToCart(103, "1", "js123",
                        "Harry Potter: Philosopher's Stone", 12.99f,
                        1, 12.99f, "mj456"),
                "Product already exists in cart. Please delete the old entry and add the new changes.");

        assertThrows(IllegalArgumentException.class,
                () -> cartService.addToCart(104, "", "js123",
                        "Harry Potter: Philosopher's Stone", 12.99f,
                        1, 12.99f, "mj456"),
                "Product ID cannot be empty.");

        assertThrows(IllegalArgumentException.class,
                () -> cartService.addToCart(104, "1", "js123",
                        "Harry Potter: Philosopher's Stone", 12.99f,
                        0, 12.99f, "mj456"),
                "Product quantity cannot be less than or equal to 0.");

        cartService.addToCart(104, "2", "jh111",
                "A Man Called Ove", 6.99f,
                10, 69.9f, "mj456");
        assertEquals(cartService.viewAllCarts().size(), 5);

    }

    /**
     * Tests the deleteItemFromCart method of the CartService class.
     * Ensures that the deleteItemFromCart method throws an exception if the cart ID is empty
     * or if the product does not exist in the cart.
     * Ensures that the deleteItemFromCart method removes the product from the cart successfully if it exists.
     */
    @Test
    public void testDeleteItemFromCart() {
        assertThrows(IllegalArgumentException.class,
                () -> cartService.deleteItemFromCart(""),
                "Cart ID cannot be empty.");

        assertThrows(IllegalArgumentException.class,
                () -> cartService.deleteItemFromCart("500"),
                "Product does not exist.");

        cartService.deleteItemFromCart("101");
        assertEquals(cartService.viewAllCarts().size(), 3);
    }

    /**
     * Tests the viewAllCarts method of the CartService class.
     * Ensures that viewCart returns the correct number of carts
     * Ensures that viewCart throws an exception if there is no cart in the system
     */
    @Test
    public void testViewAllCarts() {
        assertEquals(cartService.viewAllCarts().size(), 4);

        cartService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> cartService.viewAllCarts(),
                "No cart");
    }

    /**
     * Tests the clearCart method of the CartService class.
     * Ensures that clearCart throw an exception if the input username is empty
     * Ensures that clearCart remove all carts of the user
     */
    @Test
    public void testClearCart() {
        assertThrows(IllegalArgumentException.class,
                () -> cartService.clearCart(""),
                "Username cannot be empty.");

        cartService.clearCart("jh111");
        assertEquals(cartService.viewCart("jh111").size(), 0);


    }
}
