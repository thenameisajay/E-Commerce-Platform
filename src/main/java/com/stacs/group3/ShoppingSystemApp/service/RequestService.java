package com.stacs.group3.ShoppingSystemApp.service;

import com.stacs.group3.ShoppingSystemApp.model.Request;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to create a request service.
 * The service is for the request object.
 */
public class RequestService implements Serializable {
    private Map<String, Request> requests = new HashMap<>();

    /**
     * The method is used to store a request in the system.
     *
     * @param username    username of the user
     * @param requestType type of request
     */
    public void storeRequest(String username, String requestType) {
        if (requests.containsKey(username)) {
            throw new IllegalArgumentException("A Previous Request already exists and admin has not reviewed it yet.");
        } else {
            requests.put(username.toLowerCase(), new Request(username.toLowerCase(), requestType.toLowerCase()));
        }

    }

    /**
     * This method is used to get all the requests for the admin to review.
     *
     * @return a map of all the requests
     */
    public Map<String, Request> getRequests() {
        Map<String, Request> returnRequests = new HashMap<>();
        returnRequests.putAll(requests);
        requests.clear();
        return returnRequests;
    }


    /**
     * This method is used to save the request data to a file.
     *
     * @throws IOException if the file is not found
     */
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/requestData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(requests);
        o.close();
        f.close();
    }

    /**
     * This method is used to load the request data from a file.
     *
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException            if the file is not found
     */
    public void loadData() {
        try {
            FileInputStream fi = new FileInputStream("src/main/resources/data/requestData.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            // read object from file
            requests = (Map<String, Request>) oi.readObject();
            oi.close();
            fi.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to wipe all the request data.
     */
    public void wipeAll() {
        requests.clear();
    }

}
