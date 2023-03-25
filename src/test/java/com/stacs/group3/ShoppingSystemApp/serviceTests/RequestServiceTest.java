package main.java.alphasystem.test;

import main.java.alphasystem.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class
RequestServiceTest {
    RequestService requestService = new RequestService();

    @BeforeEach
    public void setup() {

        requestService.storeRequest("js123", "admin");
        requestService.storeRequest("mj456", "customer");
        requestService.storeRequest("jj789", "seller");
    }

    @Test
    public void testStoreRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> requestService.storeRequest("js123", "customer"),
                "A Previous Request already exists and admin has not reviewed it yet.");

        requestService.storeRequest("js121", "admin");
        assertEquals(requestService.getRequests().size(), 4);
    }

    @Test
    public void testGetRequests() {
        assertEquals(requestService.getRequests().size(), 3);
    }

    @Test
    public void testWipeAll() {
        requestService.wipeAll();
        assertEquals(requestService.getRequests().size(), 0);
    }
}
