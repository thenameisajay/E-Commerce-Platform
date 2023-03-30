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
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for API related to RequestService.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestServiceAPITest {
    @Value(value = "http://localhost:${local.server.port}/userService")
    private String userServiceURI;

    @Value(value = "http://localhost:${local.server.port}/requestService")
    private String requestServiceURI;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Initialises the server with the necessary users before each test.
     */
    @BeforeEach
    public void setup() {
        Map<String, String> user1 = new HashMap<>();
        user1.put("firstName", "Jack");
        user1.put("lastName", "Johnson");
        user1.put("username", "jj789");
        user1.put("email", "jj789@example.com");
        user1.put("password", "123456789");
        user1.put("accountType", "customer");
        HttpEntity<Map> request1 = new HttpEntity<>(user1);
        restTemplate.postForEntity(userServiceURI + "/add", request1, Void.class);

        Map<String, String> user2 = new HashMap<>();
        user2.put("firstName", "Mike");
        user2.put("lastName", "Li");
        user2.put("username", "ml123");
        user2.put("email", "ml123@example.com");
        user2.put("password", "123456789");
        user2.put("accountType", "seller");
        HttpEntity<Map> request2 = new HttpEntity<>(user2);
        restTemplate.postForEntity(userServiceURI + "/add", request2, Void.class);
    }

    /**
     * Clear up the server after each test.
     * Avoid the influence on other tests
     */
    @AfterEach
    public void clear() {
        ResponseEntity<Map> response =
                restTemplate.getForEntity(userServiceURI + "/viewAll", Map.class);
        Map<String, Map<String, String>> users = response.getBody();

        Iterator<String> iterator = users.keySet().iterator();

        while (iterator.hasNext()) {
            restTemplate.postForEntity(userServiceURI + "/deleteViaAdmin/" + iterator.next(), null, Void.class);
        }
    }

    /**
     * Tests the API of updateUserPermission.
     * Ensures the HTTP response status code is 200
     * Ensures the permission is changed to the expected value
     */
    @Test
    public void testUpdateUserPermission() {
        ResponseEntity<Void> response1 =
                restTemplate.postForEntity(requestServiceURI + "/updatePermission/jj789/seller", null, Void.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response2 =
                restTemplate.getForEntity(userServiceURI + "/Login?user=jj789&password=123456789", Map.class);
        Map<String, Map<String, String>> user1 = response2.getBody();
        assertEquals(user1.get("jj789").get("accountType"), "seller");

        ResponseEntity<Void> response3 =
                restTemplate.postForEntity(requestServiceURI + "/updatePermission/ml123/customer", null, Void.class);
        assertEquals(response3.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response4 =
                restTemplate.getForEntity(userServiceURI + "/Login?user=ml123&password=123456789", Map.class);
        Map<String, Map<String, String>> user2 = response4.getBody();
        assertEquals(user2.get("ml123").get("accountType"), "customer");
    }

    /**
     * Tests API of storeRequest and viewRequest.
     * Ensures the HTTP response status code is 200
     * Ensures the Requests are stored by the server
     * Ensures the returned requests are the inserted request
     */
    @Test
    public void testStoreAndViewRequest() {
        ResponseEntity<Void> response1 =
                restTemplate.postForEntity(requestServiceURI + "/storeRequest/jj789/seller", null, Void.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Void> response2 =
                restTemplate.postForEntity(requestServiceURI + "/storeRequest/ml123/customer", null, Void.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> response3 =
                restTemplate.getForEntity(requestServiceURI + "/viewRequests", Map.class);
        assertEquals(response3.getStatusCode(), HttpStatus.OK);
        assertNotNull(response3.getBody());

        Map<String, Map<String, String>> requests = response3.getBody();
        assertEquals(requests.size(), 2);
    }
}
