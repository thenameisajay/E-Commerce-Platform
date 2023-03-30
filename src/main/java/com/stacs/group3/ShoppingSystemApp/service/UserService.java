package com.stacs.group3.ShoppingSystemApp.service;


import com.stacs.group3.ShoppingSystemApp.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for all the business logic of the application.
 * It is the service layer of the application.
 */
public class UserService implements Serializable {

    // HashMap to store user data
    private Map<String, User> user = new HashMap<>();

    public void addUser(String firstName, String lastName, String username, String email, String password, String accountType) {
        // First Check : For empty fields
        if (firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
        if (lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("Last name cannot be empty");
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");

        // Second Check : For validation of first name, last name,email and username
        if (!firstName.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("First name can only contain alphabets");
        if (!lastName.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Last name can only contain alphabets");
        if (!email.matches("^(.+)@(.+)$"))
            throw new IllegalArgumentException("Invalid email address");
        if (!username.matches("[a-zA-Z0-9]+"))
            throw new IllegalArgumentException("Username can only contain alphabets and numbers");
        // Third Check : If username already exists in the system.
        if (!user.containsKey(username)) {
            User userToAdd = new User(firstName.toLowerCase(), lastName.toLowerCase(), username.toLowerCase(), email.toLowerCase(), password.toLowerCase(), accountType.toLowerCase());
            // Adding new user to the system
            user.put(username.toLowerCase(), userToAdd);
        } else {
            throw new IllegalArgumentException("Username already exists");
        }

    }

    public Map<String, User> adminLogin(String username, String password) {
        Map<String, User> adminLoggedIn = new HashMap<>();
        // First Check : For empty fields
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");

        // Second Check : If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users in the system");
        }

        // Third Check : If username and password matches
        if (user.containsKey(username.toLowerCase())) {
            if (user.get(username).getPassword().equals(password.toLowerCase())) {
                if (user.get(username).getAccountType().equals("admin")) {
                    adminLoggedIn.put(username, user.get(username));
                } else {
                    throw new IllegalArgumentException("User is not admin");
                }

            } else {
                throw new IllegalArgumentException("Invalid username or password");
            }
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return adminLoggedIn;
    }

    public HashMap<String, User> viewAllUsers() {
        HashMap<String, User> users = new HashMap<>();
        // First Check : If there are any users in the system
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users in the system");
        } else {
            users.putAll(user);
        }
        return users;
    }

    public Map<String, User> userLogin(String username, String password) {
        Map<String, User> userLoggedIn = new HashMap<>();
        // First Check : For empty fields
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
        // Second Check : If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users in the system");
        }
        // Third Check : If username and password matches
        if (user.containsKey(username.toLowerCase())) {
            // Why is the Map returning null values after loading from the file.
            for (Map.Entry<String, User> entry : user.entrySet()) {
                if (entry.getKey().equals(username.toLowerCase())) {
                    if (entry.getValue().getPassword().equals(password.toLowerCase())) {
                        userLoggedIn.put(username.toLowerCase(), user.get(username.toLowerCase()));
                    } else {
                        throw new IllegalArgumentException("Invalid username or password");
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return userLoggedIn;
    }

    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/userData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(user);
        o.close();
        f.close();
    }

    public void loadData() {
        try {
            FileInputStream fi = new FileInputStream("src/main/resources/data/userData.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            // read object from file
            user = (Map<String, User>) oi.readObject();
            oi.close();
            fi.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adminGenerate() {
        user.put("admin", new User("admin", "admin", "admin", "admin@admin.com", "admin", "admin"));
    }

    public void deleteUserViaAdmin(String username) {
        // First Check : For empty fields
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        // Second Check : If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users in the system");
        }
        // Third Check : If username exists in the system
        if (user.containsKey(username.toLowerCase())) {
            user.remove(username.toLowerCase());
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }
    }

    public void deleteSelfAccount(String username, String password) {
        // First Check : For empty fields
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
        // Second Check : If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users in the system");
        }
        // Third Check : If username and password matches
        if (user.containsKey(username.toLowerCase())) {
            if (user.get(username).getPassword().equals(password.toLowerCase())) {
                user.remove(username.toLowerCase());
            } else {
                throw new IllegalArgumentException("Invalid username or password");
            }
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

    public void updateUserPermission(String username, String requestType) {
        // First Check : For empty fields
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        if (requestType == null || requestType.isEmpty())
            throw new IllegalArgumentException("requestType cannot be empty");
        // Second Check : If userdata is empty
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No users in the system");
        }
        // Third Check : If username exists in the system
        if (user.containsKey(username.toLowerCase())) {
            user.get(username).setAccountType(requestType.toLowerCase());
        } else {
            throw new IllegalArgumentException("Username does not exist");
        }
    }

    public void wipeAll() {
        user.clear();
    }
}
