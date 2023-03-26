package com.stacs.group3.ShoppingSystemApp.service;

import com.stacs.group3.ShoppingSystemApp.model.Request;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RequestService implements Serializable {
    private Map<String, Request> requests = new HashMap<>();

    public void storeRequest(String username, String requestType) {
        if (requests.containsKey(username)) {
            throw new IllegalArgumentException("A Previous Request already exists and admin has not reviewed it yet.");
        } else {
            requests.put(username.toLowerCase(), new Request(username.toLowerCase(), requestType.toLowerCase()));
        }

    }

    public Map<String, Request> getRequests() {
        Map<String, Request> returnRequests = new HashMap<>();
        returnRequests.putAll(requests);
        requests.clear();
        return returnRequests;
    }


    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/requestData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(requests);
        o.close();
        f.close();
    }

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

    public void wipeAll() {
        requests.clear();
    }

}
