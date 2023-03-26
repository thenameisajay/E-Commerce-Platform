package com.stacs.group3.ShoppingSystemApp.serviceTests;


import com.stacs.group3.ShoppingSystemApp.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CartServiceTest {

    CartService cartService = new CartService();

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

    @Test
    public void testViewCart() {
        assertEquals(cartService.viewCart("js123").size(), 3);
        assertEquals(cartService.viewCart("jh111").size(), 1);
        assertEquals(cartService.viewCart("jj111").size(), 0);
    }

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
                "Product already exists in cart. Please delete the old entry and add the new changes.");

        cartService.addToCart(104, "2", "jh111",
                "A Man Called Ove", 6.99f,
                10, 69.9f, "mj456");
        assertEquals(cartService.viewAllCarts().size(), 5);

    }

    @Test
    public void testDeleteItermFromCart() {
        assertThrows(IllegalArgumentException.class,
                () -> cartService.deleteItemFromCart(""),
                "Cart ID cannot be empty.");

        assertThrows(IllegalArgumentException.class,
                () -> cartService.deleteItemFromCart("500"),
                "Product does not exist.");

        cartService.deleteItemFromCart("101");
        assertEquals(cartService.viewAllCarts().size(), 3);
    }

    @Test
    public void testViewAllCarts() {
        assertEquals(cartService.viewAllCarts().size(), 4);

        cartService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> cartService.viewAllCarts(),
                "No cart");
    }

    @Test
    public void testClearCart() {
        assertThrows(IllegalArgumentException.class,
                () -> cartService.clearCart(""),
                "Username cannot be empty.");

        cartService.clearCart("jh111");
        assertEquals(cartService.viewCart("jh111").size(), 0);


    }
}
