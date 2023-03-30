package com.stacs.group3.ShoppingSystemApp.serviceTests;

import com.stacs.group3.ShoppingSystemApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for the UserService class.
 */
public class UserServiceTest {
    private UserService userService = new UserService();

    /**
     * Initialise the userService before each test.
     */
    @BeforeEach
    public void setup() {
        userService.addUser("James", "Smith", "js123", "js123@gmail.com", "123456789", "admin");
        userService.addUser("Mary", "Jones", "mj456", "mj456@gmail.com", "abcdefgh", "seller");
        userService.addUser("Jack", "Johnson", "jj789", "jj789@gmail.com", "abc123456", "customer");
    }

    /**
     * Tests the addUser method of the UserService.
     * Ensures that addUser method throws an exception if the first name is empty or invalid
     *      or if the last name is empty or invalid
     *      or if the username is empty or invalid
     *      or if the email is empty or invalid
     *      or if password is empty
     *      or if the user has already existed
     * Ensures the addUser method add the user to the system if the input is valid
     */
    @Test
    public void testAddUser() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("", "Williams", "rw111", "rw111@gmail.com", "987654321", "customer"),
                "First name cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("123", "Williams", "rw111", "rw111@gmail.com", "987654321", "customer"),
                "First name can only contain alphabets");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "", "rw111", "rw111@gmail.com", "987654321", "customer"),
                "Last name cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "123", "rw111", "rw111@gmail.com", "987654321", "customer"),
                "Last name can only contain alphabets");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "Williams", "", "rw111@gmail.com", "987654321", "customer"),
                "Username cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "Williams", "rw.111", "rw111@gmail.com", "987654321", "customer"),
                "Username can only contain alphabets and numbers");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "Williams", "rw111", "", "987654321", "customer"),
                "Email cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "Williams", "rw111", "rw111gmail", "987654321", "customer"),
                "Invalid email address");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "Williams", "rw111", "rw111@gmail.com", "", "customer"),
                "Password cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Robert", "Williams", "js123", "rw111@gmail.com", "", "customer"),
                "Username already exists");

        userService.addUser("Robert", "Williams", "rw111", "rw111@gmail.com", "987654321", "customer");
        assertEquals(userService.viewAllUsers().size(), 4);
    }

    /**
     * Tests the adminLogin method of the UserService.
     * Ensures that adminLogin method throws an exception if the username is empty
     *      or if password is empty
     *      or if the user does not exist
     *      or if the username and password are not matched
     *      or if the user is not admin
     *      or if there is no user in the system
     * Ensures that the adminLogin method return the admin information
     */
    @Test
    public void testAdminLogin() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.adminLogin("", "123456"),
                "Username cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.adminLogin("js123", ""),
                "Password cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.adminLogin("js111", "123456789"),
                "Invalid username or password");
        assertThrows(IllegalArgumentException.class,
                () -> userService.adminLogin("js123", "abcdefgh"),
                "Invalid username or password");
        assertThrows(IllegalArgumentException.class,
                () -> userService.adminLogin("mj456", "abcdefgh"),
                "User is not admin");

        assertEquals(userService.adminLogin("js123", "123456789").size(), 1);

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.adminLogin("js123", "123456789"),
                "No users in the system");

    }

    /**
     * Tests the viewAllUsers method of the UserService.
     * Ensures that viewAllUsers method throws an exception if there is no user
     * Ensures that viewAllUsers method return the correct number of users in the system
     */
    @Test
    public void testViewAllUsers() {
        assertEquals(userService.viewAllUsers().size(), 3);

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.viewAllUsers(),
                "No users in the system");
    }

    /**
     * Tests the userLogin method of UserService class.
     * Ensures that userLogin method throws an exception if username is empty
     *      or if password is empty
     *      or if user does not exist
     *      or if username and password are not matched
     *      or if there is no user in the system
     * Ensures that userLogin method returns the user information
     */
    @Test
    public void testUserLogin() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.userLogin("", "123"),
                "Username cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.userLogin("js123", ""),
                "Password cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.userLogin("mj455", "abcdefgh"),
                "Invalid username or password");
        assertThrows(IllegalArgumentException.class,
                () -> userService.userLogin("mj456", "123456789"),
                "Invalid username or password");

        assertEquals(userService.userLogin("mj456", "abcdefgh").size(), 1);

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.userLogin("mj456", "abcdefgh"),
                "No users in the system");
    }

    /**
     * Tests the adminGenerate method of UserService class.
     * Ensures that the adminGenerate method generate a default admin
     */
    @Test
    public void testAdminGenerate() {
        userService.adminGenerate();
        assertEquals(userService.viewAllUsers().size(), 4);

        assertEquals(userService.adminLogin("admin", "admin").size(), 1);
    }

    /**
     * Tests the deleteUserViaAdmin method of UserService class.
     * Ensures that deleteUserViaAdmin method throws an exception if username is empty or does not exist
     *      or if there is no user in the system
     * Ensures that deleteUserViaAdmin method delete the user
     */
    @Test
    public void testDeleteUserViaAdmin() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUserViaAdmin(""),
                "Username cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUserViaAdmin("mj457"),
                "Username does not exist");

        userService.deleteUserViaAdmin("mj456");
        assertEquals(userService.viewAllUsers().size(), 2);

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUserViaAdmin("mj457"),
                "No users in the system");

    }

    /**
     * Tests deleteSelfAccount method of UserService class.
     * Ensures that deleteSelfAccount method throws an exception if username is empty
     *      or if password is empty
     *      or if the username or password is invalid
     * Ensures that deleteSelfAccount method delete the user
     */
    @Test
    public void testDeleteSelfAccount() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteSelfAccount("", "abcdefgh"),
                "Username cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteSelfAccount("mj456", ""),
                "Password cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteSelfAccount("mj457", "abcdefgh"),
                "Invalid username or password");
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteSelfAccount("mj456", "123456789"),
                "Invalid username or password");

        userService.deleteSelfAccount("mj456", "abcdefgh");
        assertEquals(userService.viewAllUsers().size(), 2);
        assertThrows(IllegalArgumentException.class,
                () -> userService.userLogin("mj456", "abcdefgh"),
                "Invalid username or password");

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteSelfAccount("mj456", "123456789"),
                "No users in the system");
    }

    /**
     * Tests the updateUserPermission method of UserService class.
     * Ensures the updateUserPermission method throws an exception if username is empty or does not exist
     *      or if requestType is empty
     *      or if there is no users in the system
     * Ensure the updateUserPermission method changed the accountType of the user
     */
    @Test
    public void testUpdateUserPermission() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.updateUserPermission("", "admin"),
                "Username cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.updateUserPermission("mj456", ""),
                "requestType cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.updateUserPermission("mj457", "admin"),
                "Username does not exist");

        userService.updateUserPermission("mj456", "admin");
        // the user can use the adminLogin

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.updateUserPermission("jj789", "abc123456"),
                "No users in the system");
    }

}
