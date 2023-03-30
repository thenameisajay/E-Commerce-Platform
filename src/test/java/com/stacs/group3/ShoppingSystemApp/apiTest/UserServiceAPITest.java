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
 * Tests for the api related to the UserService.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceAPITest {
    @Value(value = "http://localhost:${local.server.port}/userService")
    private String userServiceURI;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Initialises the server with necessary users for test before each test.
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

        Map<String, String> user3 = new HashMap<>();
        user3.put("firstName", "James");
        user3.put("lastName", "Smith");
        user3.put("username", "js123");
        user3.put("email", "js123@example.com");
        user3.put("password", "123456789");
        user3.put("accountType", "admin");
        HttpEntity<Map> request3 = new HttpEntity<>(user3);
        restTemplate.postForEntity(userServiceURI + "/add", request3, Void.class);
    }

    /**
     * Clears up the users in the server after execute each test.
     * This avoids data influenced other test
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
     * Tests for the API of addUser.
     * Ensures the HTTP response status code is 200
     * Ensure the added users can be found in the returned Map by calling viewUsers API
     */
    @Test
    public void testAddUser() {
        Map<String, String> user1 = new HashMap<>();
        user1.put("firstName", "John");
        user1.put("lastName", "Doe");
        user1.put("username", "johndoe");
        user1.put("email", "johndoe@example.com");
        user1.put("password", "123456789");
        user1.put("accountType", "seller");
        HttpEntity<Map> request1 = new HttpEntity<>(user1);

        ResponseEntity<Void> response1 = restTemplate.postForEntity(userServiceURI + "/add", request1, Void.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);

        Map<String, String> user2 = new HashMap<>();
        user2.put("firstName", "Mary");
        user2.put("lastName", "Jones");
        user2.put("username", "mj456");
        user2.put("email", "mj456@example.com");
        user2.put("password", "123456789");
        user2.put("accountType", "customer");
        HttpEntity<Map> request2 = new HttpEntity<>(user2);

        ResponseEntity<Void> response2 = restTemplate.postForEntity(userServiceURI + "/add", request2, Void.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        Map<String, String> user3 = new HashMap<>();
        user3.put("firstName", "Bruce");
        user3.put("lastName", "Dan");
        user3.put("username", "bd123");
        user3.put("email", "bd123@example.com");
        user3.put("password", "123456789");
        user3.put("accountType", "admin");
        HttpEntity<Map> request3 = new HttpEntity<>(user3);

        ResponseEntity<Void> response3 = restTemplate.postForEntity(userServiceURI + "/add", request3, Void.class);
        assertEquals(response3.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> responseAllUsers =
                restTemplate.getForEntity(userServiceURI + "/viewAll", Map.class);

        assertEquals(responseAllUsers.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseAllUsers.getBody());
        Map<String, Map<String, String>> users = responseAllUsers.getBody();
        assertTrue(users.containsKey("johndoe"));
        assertTrue(users.containsKey("mj456"));
        assertTrue(users.containsKey("bd123"));
    }

    /**
     * Tests for the API of adminLogin.
     * Ensures the HTTP response status code is 200
     * Ensures the returned information is the input admin
     */
    @Test
    public void testAdminLogin() {
        ResponseEntity<Map> response =
                restTemplate.getForEntity(userServiceURI + "/adminLogin?user=js123&password=123456789", Map.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());

        Map<String, Map<String, String>> admin = response.getBody();
        assertEquals(admin.size(), 1);
        assertTrue(admin.containsKey("js123"));
    }

    /**
     * Tests for the API of viewAllUsers.
     * Ensures the HTTP response status code is 200
     * Ensures the returned Map contains the inserted user
     */
    @Test
    public void testViewAllUsers() {
        ResponseEntity<Map> response =
                restTemplate.getForEntity(userServiceURI + "/viewAll", Map.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertNotNull(response.getBody());
        Map<String, Map<String, String>> users = response.getBody();
        assertTrue(users.containsKey("jj789"));
        assertTrue(users.containsKey("ml123"));
        assertTrue(users.containsKey("js123"));
    }

    /**
     * Tests for the API of userLogin.
     * Ensures the HTTP response status code is 200
     * Ensures the returned information is the input user
     */
    @Test
    public void testLogin() {
        ResponseEntity<Map> response1 =
                restTemplate.getForEntity(userServiceURI + "/Login?user=jj789&password=123456789", Map.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);

        assertNotNull(response1.getBody());

        Map<String, Map<String, String>> user1 = response1.getBody();
        assertEquals(user1.size(), 1);
        assertTrue(user1.containsKey("jj789"));

        ResponseEntity<Map> response2 =
                restTemplate.getForEntity(userServiceURI + "/Login?user=ml123&password=123456789", Map.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        assertNotNull(response2.getBody());

        Map<String, Map<String, String>> user2 = response2.getBody();
        assertEquals(user2.size(), 1);
        assertTrue(user2.containsKey("ml123"));
    }

    /**
     * Tests for the API of adminGenerate.
     * Ensures the HTTP response status code is 200
     * Ensures the admin is generated successfully in the server
     */
    @Test
    public void testAdminGenerate() {
        HttpEntity<Void> request = new HttpEntity<>(null);

        ResponseEntity<Void> response = restTemplate.postForEntity(userServiceURI + "/adminGenerate", request, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> responseAllUsers =
                restTemplate.getForEntity(userServiceURI + "/viewAll", Map.class);

        assertEquals(responseAllUsers.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseAllUsers.getBody());
        Map<String, Map<String, String>> users = responseAllUsers.getBody();
        assertTrue(users.containsKey("admin"));
    }

    /**
     * Tests for the API of deleteUserViaAdmin.
     * Ensures the HTTP response status code is 200
     * Ensures the user is deleted by the admin
     */
    @Test
    public void testDeleteUserViaAdmin() {
        ResponseEntity<Void> response = restTemplate.postForEntity(userServiceURI + "/deleteViaAdmin/jj789", null, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> responseAllUsers =
                restTemplate.getForEntity(userServiceURI + "/viewAll", Map.class);
        assertEquals(responseAllUsers.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseAllUsers.getBody());
        Map<String, Map<String, String>> users = responseAllUsers.getBody();
        assertFalse(users.containsKey("jj789"));
    }

    /**
     * Tests for the API of deleteSelfAccount.
     * Ensures the HTTP response status code is 200
     * Ensures the user is deleted successfully
     */
    @Test
    public void testDeleteSelfAccount() {
        ResponseEntity<Void> response1 = restTemplate.postForEntity(userServiceURI + "/delete?user=ml123&password=123456789", null, Void.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Void> response2 = restTemplate.postForEntity(userServiceURI + "/delete?user=js123&password=123456789", null, Void.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Map> responseAllUsers =
                restTemplate.getForEntity(userServiceURI + "/viewAll", Map.class);
        assertEquals(responseAllUsers.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseAllUsers.getBody());
        Map<String, Map<String, String>> users = responseAllUsers.getBody();
        assertFalse(users.containsKey("ml123"));
        assertFalse(users.containsKey("js123"));
    }

}
