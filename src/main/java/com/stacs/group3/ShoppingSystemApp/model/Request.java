package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

/**
 * This class is used to create a request object.
 * It contains the following attributes:
 * username, requestType
 * It also contains the following methods:
 * getUsername, setUsername, getRequestType, setRequestType
 */
public class Request implements Serializable {
    private String username;
    private String requestType;

    public Request(String username, String requestType) {
        this.username = username;
        this.requestType = requestType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
