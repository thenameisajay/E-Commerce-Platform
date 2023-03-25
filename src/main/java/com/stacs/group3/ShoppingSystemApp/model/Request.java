package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

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
