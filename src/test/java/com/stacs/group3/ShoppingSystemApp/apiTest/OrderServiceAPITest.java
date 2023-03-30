package com.stacs.group3.ShoppingSystemApp.apiTest;

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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the API related to OrderService.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceAPITest {

    @Value(value = "http://localhost:${local.server.port}/orderService")
    private String orderServiceURI;

    @Value(value = "http://localhost:${local.server.port}/productService")
    private String productServiceURI;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Initialise the server with the necessary products before test.
     */
    @BeforeEach
    public void setup() {
        Map<String, String> product1 = new HashMap<>();
        product1.put("productID", "1");
        product1.put("productName", "Harry Potter: Chamber of Secrets");
        product1.put("productDescription", "The second novel in the Harry Potter series");
        product1.put("author", "Joanne Rowling");
        product1.put("productPrice", "11.99");
        product1.put("productQuantity", "150");
        product1.put("productCategory", "Fantasy");
        product1.put("sellerUsername", "ml123");
        HttpEntity<Map> request1 = new HttpEntity<>(product1);
        restTemplate.postForEntity(productServiceURI + "/add", request1, Void.class);

        Map<String, String> product2 = new HashMap<>();
        product2.put("productID", "2");
        product2.put("productName", "A Man Called Ove");
        product2.put("productDescription", "A Man Called Ove is a novel by Swedish writer Fredrik Backman published in Swedish by Forum in 2012. " +
                "The novel was published in English in 2013 and reached the New York Times Best Seller list 18 months " +
                "after its publication and stayed on the list for 42 weeks");
        product2.put("author", "Fredrik Backman");
        product2.put("productPrice", "6.99");
        product2.put("productQuantity", "200");
        product2.put("productCategory", "Novel");
        product2.put("sellerUsername", "mj456");
        HttpEntity<Map> request2 = new HttpEntity<>(product2);
        restTemplate.postForEntity(productServiceURI + "/add", request2, Void.class);
    }

    /**
     * Test the API supplied by the OrderService.
     * Ensures the HTTP response status code is 200
     * Ensures the order is stored by the server
     * Ensures customers and sellers can view their own order
     * Ensures the product quantity after making an order
     */
    @Test
    public void testOrderService() {
        Map<String, String> order1 = new HashMap<>();
        order1.put("orderID", "100");
        order1.put("productID", "1");
        order1.put("customerName", "jj789");
        order1.put("productName", "Harry Potter: Chamber of Secrets");
        order1.put("productPrice", "11.99");
        order1.put("productQuantity", "1");
        order1.put("productTotal", "11.99");
        order1.put("sellerName", "ml123");
        HttpEntity<Map> request1 = new HttpEntity<>(order1);
        ResponseEntity<Void> response1 = restTemplate.postForEntity(orderServiceURI + "/add", request1, Void.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);

        Map<String, String> order2 = new HashMap<>();
        order2.put("orderID", "101");
        order2.put("productID", "2");
        order2.put("customerName", "jj789");
        order2.put("productName", "A Man Called Ove");
        order2.put("productPrice", "6.99");
        order2.put("productQuantity", "2");
        order2.put("productTotal", "13.98");
        order2.put("sellerName", "mj456");
        HttpEntity<Map> request2 = new HttpEntity<>(order2);
        ResponseEntity<Void> response2 = restTemplate.postForEntity(orderServiceURI + "/add", request2, Void.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> responseCustomer =
                restTemplate.getForEntity(orderServiceURI + "/searchName/jj789", Map.class);
        assertEquals(responseCustomer.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseCustomer.getBody());
        Map<String, Map<String, String>> customerOrders = responseCustomer.getBody();
        assertEquals(customerOrders.size(), 2);
        assertTrue(customerOrders.containsKey("100"));
        assertTrue(customerOrders.containsKey("101"));

        ResponseEntity<Map> responseSeller =
                restTemplate.getForEntity(orderServiceURI + "/searchSeller/ml123", Map.class);
        assertEquals(responseSeller.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseSeller.getBody());
        Map<String, Map<String, String>> sellerOrders = responseSeller.getBody();
        assertEquals(sellerOrders.size(), 1);
        assertTrue(sellerOrders.containsKey("100"));

        ResponseEntity<Void> responseUpdate = restTemplate.postForEntity(orderServiceURI + "/update/1/1", null, Void.class);
        assertEquals(responseUpdate.getStatusCode(), HttpStatus.OK);
        ResponseEntity<Map> responseProduct = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = responseProduct.getBody();
        assertEquals(products.get("1").get("productQuantity"), "149");

    }

}
