package com.stacs.group3.ShoppingSystemApp.modelTests;

import com.stacs.group3.ShoppingSystemApp.model.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {
    Request request1;
    Request request2;
    Request request3;

    @BeforeEach
    public void setup() {
        request1 = new Request("js123", "customer");
        request2 = new Request("mj456", "admin");
        request3 = new Request("jj789", "seller");
    }

    @Test
    public void testGet() {
        assertEquals(request1.getUsername(), "js123");
        assertEquals(request2.getUsername(), "mj456");
        assertEquals(request3.getUsername(), "jj789");

        assertEquals(request1.getRequestType(), "customer");
        assertEquals(request2.getRequestType(), "admin");
        assertEquals(request3.getRequestType(), "seller");
    }

    @Test
    public void testSet() {
        request1.setUsername("mj456");
        request2.setUsername("jj789");
        request3.setUsername("js123");
        assertEquals(request1.getUsername(), "mj456");
        assertEquals(request2.getUsername(), "jj789");
        assertEquals(request3.getUsername(), "js123");

        request1.setRequestType("admin");
        request2.setRequestType("seller");
        request3.setRequestType("customer");
        assertEquals(request1.getRequestType(), "admin");
        assertEquals(request2.getRequestType(), "seller");
        assertEquals(request3.getRequestType(), "customer");

    }
}
