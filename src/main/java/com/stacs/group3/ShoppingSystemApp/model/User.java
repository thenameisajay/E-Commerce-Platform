package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

/**
 * This class is used to create a user object.
 * It contains the following attributes:
 * firstName, lastName, username, email, password, accountType
 * It also contains the following methods:
 * getFirstName, setFirstName, getLastName, setLastName, getUsername, setUsername, getEmail, setEmail, getPassword, setPassword, getAccountType, setAccountType
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;

    private String username;
    private String email;
    private String password;
    private String accountType;

    public User(String firstName, String lastName, String username, String email, String password, String accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


}
