

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService = new UserService();

    @BeforeEach
    public void setup() {

        userService.addUser("James", "Smith", "js123", "js123@gmail.com", "123456789", "admin");
        userService.addUser("Mary", "Jones", "mj456", "mj456@gmail.com", "abcdefgh", "seller");
        userService.addUser("Jack", "Johnson", "jj789", "jj789@gmail.com", "abc123456", "customer");
    }

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

    // ToDo: Check if the user is a customer or seller or admin or even in the system.
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

        assertEquals(userService.adminLogin("js123", "123456789").size(), 1);

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.adminLogin("js123", "123456789"),
                "No users in the system");

    }

    @Test
    public void testViewAllUsers() {
        assertEquals(userService.viewAllUsers().size(), 3);

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.viewAllUsers(),
                "No users in the system");
    }

    // ToDo: Check if the user is a customer or seller or admin or even in the system.
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

    @Test
    public void testAdminGenerate() {
        userService.adminGenerate();
        assertEquals(userService.viewAllUsers().size(), 4);

        assertEquals(userService.adminLogin("admin", "admin").size(), 1);
    }

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
        assertThrows(IllegalArgumentException.class,
                () -> userService.userLogin("mj456", "abcdefgh"),
                "Invalid username or password");

        userService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUserViaAdmin("mj457"),
                "No users in the system");

    }

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
                "Invalid username or password");
    }

    // ToDo: Check if the user is a customer or seller or admin or even in the system.
    @Test
    public void testUpdateUserPermission() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.updateUserPermission("", "admin"),
                "Username cannot be empty");
        assertThrows(IllegalArgumentException.class,
                () -> userService.updateUserPermission("mj456", ""),
                "Customer cannot be empty");
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
