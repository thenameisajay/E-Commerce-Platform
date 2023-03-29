package com.stacs.group3.ShoppingSystemApp.modelTests;

import com.stacs.group3.ShoppingSystemApp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test for the User class.
 */
public class UserTest {
    private User user1;
    private User user2;
    private User user3;

    /**
     * Initialise the users before each test.
     */
    @BeforeEach
    public void setup() {
        user1 = new User("James", "Smith", "js123", "js123@gmail.com", "123456789", "admin");
        user2 = new User("Mary", "Jones", "mj456", "mj456@gmail.com", "abcdefgh", "seller");
        user3 = new User("Jack", "Johnson", "jj789", "jj789@gmail.com", "abc123456", "customer");
    }

    /**
     * Test for the Get method.
     * Verifies that the data returned by the get method is the same as the data that was inserted
     */
    @Test
    public void testGet() {
        assertEquals(user1.getFirstName(), "James");
        assertEquals(user1.getLastName(), "Smith");
        assertEquals(user1.getUsername(), "js123");
        assertEquals(user1.getEmail(), "js123@gmail.com");
        assertEquals(user1.getPassword(), "123456789");
        assertEquals(user1.getAccountType(), "admin");

        assertEquals(user2.getFirstName(), "Mary");
        assertEquals(user2.getLastName(), "Jones");
        assertEquals(user2.getUsername(), "mj456");
        assertEquals(user2.getEmail(), "mj456@gmail.com");
        assertEquals(user2.getPassword(), "abcdefgh");
        assertEquals(user2.getAccountType(), "seller");

        assertEquals(user3.getFirstName(), "Jack");
        assertEquals(user3.getLastName(), "Johnson");
        assertEquals(user3.getUsername(), "jj789");
        assertEquals(user3.getEmail(), "jj789@gmail.com");
        assertEquals(user3.getPassword(), "abc123456");
        assertEquals(user3.getAccountType(), "customer");
    }

    /**
     * Test for the Set method.
     * Verifies that the data in the object is changed to the expected values after calling the set method
     */
    @Test
    public void testSet() {
        user1.setFirstName("Robert");
        user1.setLastName("Williams");
        user1.setUsername("rw111");
        user1.setEmail("rw111@gmail.com");
        user1.setPassword("987654321");
        user1.setAccountType("customer");

        assertEquals(user1.getFirstName(), "Robert");
        assertEquals(user1.getLastName(), "Williams");
        assertEquals(user1.getUsername(), "rw111");
        assertEquals(user1.getEmail(), "rw111@gmail.com");
        assertEquals(user1.getPassword(), "987654321");
        assertEquals(user1.getAccountType(), "customer");

        user3.setFirstName("Thomas");
        user3.setLastName("Lewis");
        user3.setUsername("tl567");
        user3.setEmail("tl567@gmail.com");
        user3.setPassword("147258369");
        user3.setAccountType("admin");

        assertEquals(user3.getFirstName(), "Thomas");
        assertEquals(user3.getLastName(), "Lewis");
        assertEquals(user3.getUsername(), "tl567");
        assertEquals(user3.getEmail(), "tl567@gmail.com");
        assertEquals(user3.getPassword(), "147258369");
        assertEquals(user3.getAccountType(), "admin");
    }
}
