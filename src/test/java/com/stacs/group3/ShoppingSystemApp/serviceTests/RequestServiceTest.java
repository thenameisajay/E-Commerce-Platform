package com.stacs.group3.ShoppingSystemApp.serviceTests;


import com.stacs.group3.ShoppingSystemApp.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the RequestService class.
 */
public class
RequestServiceTest {
    private RequestService requestService = new RequestService();

    /**
     * Initialise the requestService before each test.
     */
    @BeforeEach
    public void setup() {
        requestService.storeRequest("js123", "admin");
        requestService.storeRequest("mj456", "customer");
        requestService.storeRequest("jj789", "seller");
    }

    /**
     * Tests the storeRequest method of the RequestService.
     * Ensures the storeRequest method throws an exception if the user has allied a request before
     * Ensures the storeRequest method store the request to the system
     */
    @Test
    public void testStoreRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> requestService.storeRequest("js123", "customer"),
                "A Previous Request already exists and admin has not reviewed it yet.");

        requestService.storeRequest("js121", "admin");
        assertEquals(requestService.getRequests().size(), 4);
    }

    /**
     * Tests the getRequests method of the RequestService.
     * Ensures the getRequests method return the correct number of requests in the system
     */
    @Test
    public void testGetRequests() {
        assertEquals(requestService.getRequests().size(), 3);
    }

    /**
     * Tests the wipeAll method of the RequestService.
     * Ensures the wipeAll method clear all the request in the system
     */
    @Test
    public void testWipeAll() {
        requestService.wipeAll();
        assertEquals(requestService.getRequests().size(), 0);
    }
}
