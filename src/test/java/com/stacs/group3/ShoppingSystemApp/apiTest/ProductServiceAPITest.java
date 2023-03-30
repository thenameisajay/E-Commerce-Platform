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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the API related to the ProductService.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceAPITest {
    @Value(value = "http://localhost:${local.server.port}/productService")
    private String productServiceURI;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Initialises the server with necessary product before each test.
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
     * Clear up the server after each test.
     * Avoid the influence on other test
     */
    @AfterEach
    public void clear() {
        ResponseEntity<Map> response =
                restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = response.getBody();

        Iterator<Map.Entry<String, Map<String, String>>> iterator = products.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = iterator.next();
            restTemplate.postForEntity(productServiceURI + "/delete?id=" +
                    entry.getKey() + "&seller=" + entry.getValue().get("sellerUsername"), null, Void.class);
        }
    }

    /**
     * Tests the API of addProduct.
     * Ensures the HTTP response status code is 200
     * Ensures the product has been added successfully in the server
     */
    @Test
    public void testAddProduct() {
        Map<String, String> product = new HashMap<>();
        product.put("productID", "3");
        product.put("productName", "Educated");
        product.put("productDescription", "Educated is a memoir by the American author Tara Westover. " +
                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                "and emphasizes the importance of education in enlarging her world. " +
                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.");
        product.put("author", "Tara Westover");
        product.put("productPrice", "9.99");
        product.put("productQuantity", "50");
        product.put("productCategory", "Nonfiction");
        product.put("sellerUsername", "jj789");
        HttpEntity<Map> request = new HttpEntity<>(product);
        ResponseEntity<Void> response = restTemplate.postForEntity(productServiceURI + "/add", request, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> responseProducts =
                restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = responseProducts.getBody();
        assertEquals(products.size(), 3);
    }

    /**
     * Tests the API of searchProductName.
     * Ensures the HTTP response status code is 200
     * Ensures the returned Map contains all the product named the given name
     */
    @Test
    public void testSearchName() {
        ResponseEntity<Map> response = restTemplate.getForEntity(productServiceURI + "/searchName/A Man Called Ove", Map.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        Map<String, Map<String, String>> products = response.getBody();
        assertEquals(products.size(), 1);
        assertTrue(products.containsKey("2"));
    }

    /**
     * Tests the API of searchProductCategory.
     * Ensures the HTTP response status code is 200
     * Ensures the returned Map contains all the product with the given category
     */
    @Test
    public void testSearchCategory() {
        ResponseEntity<Map> response = restTemplate.getForEntity(productServiceURI + "/searchCategory/Fantasy", Map.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        Map<String, Map<String, String>> products = response.getBody();
        assertEquals(products.size(), 1);
        assertTrue(products.containsKey("1"));
    }

    /**
     * Tests the API of searchProductAuthor.
     * Ensures the HTTP response status code is 200
     * Ensures the returned Map contains all the product with the given author
     */
    @Test
    public void testSearchAuthor() {
        ResponseEntity<Map> response = restTemplate.getForEntity(productServiceURI + "/searchAuthor/Joanne Rowling", Map.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        Map<String, Map<String, String>> products = response.getBody();
        assertEquals(products.size(), 1);
        assertTrue(products.containsKey("1"));
    }

    /**
     * Tests the API of viewALLProduct.
     * Ensures the HTTP response status code is 200
     * Ensures the returned Map contains all product
     */
    @Test
    public void testViewAll() {
        ResponseEntity<Map> response = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        Map<String, Map<String, String>> products = response.getBody();
        assertEquals(products.size(), 2);
        assertTrue(products.containsKey("1"));
        assertTrue(products.containsKey("2"));
    }

    /**
     * Tests the API of deleteProduct.
     * Ensures the HTTP response status code is 200
     * Ensures the server delete the product given product id and seller
     */
    @Test
    public void testDelete() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(productServiceURI + "/delete?id=1&seller=ml123", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response1 = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);
        Map<String, Map<String, String>> products = response1.getBody();
        assertFalse(products.containsKey("1"));
    }

    /**
     * Tests the API of viewSellerProduct.
     * Ensures the HTTP response status code is 200
     * Ensures the server return all the product which the seller is given seller id
     */
    @Test
    public void testViewSellerProducts() {
        ResponseEntity<Map> response = restTemplate.getForEntity(productServiceURI + "/view/ml123", Map.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        Map<String, Map<String, String>> products = response.getBody();
        assertEquals(products.size(), 1);
        assertTrue(products.containsKey("1"));
    }

    /**
     * Tests the API of checkValidationToUpdate.
     * Ensures the HTTP response status code is 200
     * Ensures server return true if the user has the permission
     *          return false if not
     */
    @Test
    public void testCheck() {
        ResponseEntity<Boolean> response1 = restTemplate.getForEntity(productServiceURI + "/check?seller=ml123&id=1", Boolean.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);
        assertNotNull(response1.getBody());
        Boolean res1 = response1.getBody();
        assertTrue(res1);

        ResponseEntity<Boolean> response2 = restTemplate.getForEntity(productServiceURI + "/check?seller=jj789&id=1", Boolean.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);
        assertNotNull(response2.getBody());
        Boolean res2 = response2.getBody();
        assertFalse(res2);
    }

    /**
     * Tests the API of updateProductName.
     * Ensures the HTTP response status code is 200
     * Ensures the product name is changed to the expected value
     */
    @Test
    public void testUpdateName() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(productServiceURI + "/updateName?id=1&name=Educated", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response1 = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = response1.getBody();
        assertEquals(products.get("1").get("productName"), "Educated");
    }

    /**
     * Tests the API of updateProductDescription.
     * Ensures the HTTP response status code is 200
     * Ensures the product description is changed to the expected value
     */
    @Test
    public void testUpdateDescription() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(productServiceURI + "/updateDescription?id=1&description=Amazing version", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response1 = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = response1.getBody();
        assertEquals(products.get("1").get("productDescription"), "Amazing version");
    }

    /**
     * Tests the API of updateProductPrice.
     * Ensures the HTTP response status code is 200
     * Ensures the product price is changed to the expected value
     */
    @Test
    public void testUpdatePrice() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(productServiceURI + "/updatePrice?id=1&price=12.99", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response1 = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = response1.getBody();
        assertEquals(products.get("1").get("productPrice"), "12.99");
    }

    /**
     * Tests the API of updateProductQuantity.
     * Ensures the HTTP response status code is 200
     * Ensures the product quantity is changed to the expected value
     */
    @Test
    public void testUpdateQuantity() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(productServiceURI + "/updateQuantity?id=1&quantity=500", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response1 = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = response1.getBody();
        assertEquals(products.get("1").get("productQuantity"), "500");
    }

    /**
     * Tests the API of updateProductCategory.
     * Ensures the HTTP response status code is 200
     * Ensures the product category is changed to the expected value
     */
    @Test
    public void testUpdateCategory() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(productServiceURI + "/updateCategory?id=1&category=novel", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response1 = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = response1.getBody();
        assertEquals(products.get("1").get("productCategory"), "novel");
    }

    /**
     * Tests the API of updateProductAuthor.
     * Ensures the HTTP response status code is 200
     * Ensures the product author is changed to the expected value
     */
    @Test
    public void testUpdateProductAuthor() {
        ResponseEntity<Void> response =
                restTemplate.postForEntity(productServiceURI + "/updateAuthor?id=1&author=J.K. Rowling", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response1 = restTemplate.getForEntity(productServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> products = response1.getBody();
        assertEquals(products.get("1").get("author"), "J.K. Rowling");
    }
}
