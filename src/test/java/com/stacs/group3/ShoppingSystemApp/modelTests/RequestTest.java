package com.stacs.group3.ShoppingSystemApp.modelTests;

import main.java.alphasystem.model.AccountType;
import main.java.alphasystem.model.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestTest {
    Request request1;
    Request request2;
    Request request3;

    @BeforeEach
    public void setup() {
        request1 = new Request("js123", AccountType.customer);
        request2 = new Request("mj456", AccountType.admin);
        request3 = new Request("jj789", AccountType.seller);
    }

    @Test
    public void testGet() {
        assertEquals(request1.getUsername(), "js123");
        assertEquals(request2.getUsername(), "mj456");
        assertEquals(request3.getUsername(), "jj789");

        assertEquals(request1.getRequestType(), AccountType.customer);
        assertEquals(request2.getRequestType(), AccountType.admin);
        assertEquals(request3.getRequestType(), AccountType.seller);
    }

    @Test
    public void testSet() {
        request1.setUsername("mj456");
        request2.setUsername("jj789");
        request3.setUsername("js123");
        assertEquals(request1.getUsername(), "mj456");
        assertEquals(request2.getUsername(), "jj789");
        assertEquals(request3.getUsername(), "js123");

        request1.setRequestType(AccountType.admin);
        request2.setRequestType(AccountType.seller);
        request3.setRequestType(AccountType.customer);
        assertEquals(request1.getRequestType(), AccountType.admin);
        assertEquals(request2.getRequestType(), AccountType.seller);
        assertEquals(request3.getRequestType(), AccountType.customer);

    }
}
