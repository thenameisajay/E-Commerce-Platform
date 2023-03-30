package com.stacs.group3.ShoppingSystemApp.apiTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for API related to CartService.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartServiceTest {
    @Value(value = "http://localhost:${local.server.port}/cartService")
    private String cartServiceURI;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Initialises the server with the necessary carts before each test.
     */
    @BeforeEach
    public void setup() {
        Map<String, String> cart1 = new HashMap<>();
        cart1.put("cartID", "100");
        cart1.put("productID", "1");
        cart1.put("customerName", "jj789");
        cart1.put("productName", "Harry Potter: Chamber of Secrets");
        cart1.put("productPrice", "11.99");
        cart1.put("productQuantity", "1");
        cart1.put("productTotal", "11.99");
        cart1.put("sellerName", "ml123");
        HttpEntity<Map> request1 = new HttpEntity<>(cart1);
        restTemplate.postForEntity(cartServiceURI + "/add", request1, Void.class);

        Map<String, String> cart2 = new HashMap<>();
        cart2.put("cartID", "101");
        cart2.put("productID", "2");
        cart2.put("customerName", "jj789");
        cart2.put("productName", "A Man Called Ove");
        cart2.put("productPrice", "6.99");
        cart2.put("productQuantity", "2");
        cart2.put("productTotal", "13.98");
        cart2.put("sellerName", "mj456");
        HttpEntity<Map> request2 = new HttpEntity<>(cart2);
        restTemplate.postForEntity(cartServiceURI + "/add", request2, Void.class);
    }

    /**
     * Clear up the server after each test.
     * Avoid the influence on other tests
     */
    @AfterEach
    public void clear() {
        ResponseEntity<Map> response =
                restTemplate.getForEntity(cartServiceURI + "/view/jj789", Map.class);
        Map<String, Map<String, String>> carts = response.getBody();

        Iterator<String> iterator = carts.keySet().iterator();

        while (iterator.hasNext()) {
            restTemplate.postForEntity(cartServiceURI + "/delete/" + iterator.next(), null, Void.class);
        }
    }

    /**
     * Tests the API of viewCart.
     * Ensures the HTTP response status code is 200
     * Ensures the return Map contains the carts which belonged to given customer
     */
    @Test
    public void testView() {
        ResponseEntity<Map> response =
                restTemplate.getForEntity(cartServiceURI + "/view/jj789", Map.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Map<String, Map<String, String>> carts = response.getBody();
        assertEquals(carts.size(), 2);
        assertTrue(carts.containsKey("100"));
        assertTrue(carts.containsKey("101"));
    }

    /**
     * Tests the API of addCart.
     * Ensures the HTTP response status code is 200
     * Ensures the cart is successfully added in the server
     */
    @Test
    public void testAdd() {
        Map<String, String> cart = new HashMap<>();
        cart.put("cartID", "102");
        cart.put("productID", "3");
        cart.put("customerName", "js123");
        cart.put("productName", "Educated");
        cart.put("productPrice", "9.99");
        cart.put("productQuantity", "10");
        cart.put("productTotal", "99.9");
        cart.put("sellerName", "mj456");
        HttpEntity<Map> request = new HttpEntity<>(cart);
        ResponseEntity<Void> response =
                restTemplate.postForEntity(cartServiceURI + "/add", request, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> responseView =
                restTemplate.getForEntity(cartServiceURI + "/view/js123", Map.class);
        Map<String, Map<String, String>> carts = responseView.getBody();
        assertEquals(carts.size(), 1);
    }

    /**
     * Test the API of deleteCart.
     * Ensures the HTTP response status code is 200
     * Ensures the server delete the cart successfully
     */
    @Test
    public void testDelete() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(cartServiceURI + "/delete/100", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        ResponseEntity<Map> responseView =
                restTemplate.getForEntity(cartServiceURI + "/view/jj789", Map.class);
        Map<String, Map<String, String>> carts = responseView.getBody();
        assertEquals(carts.size(), 1);
    }

}
