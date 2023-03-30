package com.stacs.group3.ShoppingSystemApp.service;


import com.stacs.group3.ShoppingSystemApp.model.Order;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to create an order service.
 * The service is for the order object.
 */
public class OrderService implements Serializable {

     // The order map is used to store all the orders of all the customers
    private Map<Integer, Order> order = new HashMap<>();


    /**
     * The method is used to add an order to the map.
     * @param orderID     The order ID
     * @param productID  The product ID
     * @param productName The product name
     * @param productPrice The product price
     * @param productQuantity The product quantity
     * @param productTotal The product total
     * @param sellerName The seller userName
     * @param userName The Customer userName
     */
    public void addOrder(int orderID, String productID, String productName, float productPrice, int productQuantity, float productTotal, String sellerName, String userName) {
        order.put(orderID, new Order(orderID, productID, productName, productPrice, productQuantity, productTotal, sellerName, userName));
    }

    public Map<Integer, Order> getOrderByCustomer(String userName) {
        Map<Integer, Order> orderCustomer = new HashMap<>();
        for (Map.Entry<Integer, Order> entry : order.entrySet()) {
            if (entry.getValue().getCustomerName().equalsIgnoreCase(userName))
                orderCustomer.put(entry.getKey(), entry.getValue());
        }
        return orderCustomer;
    }

    public Map<Integer, Order> getOrderBySeller(String sellerUsername) {
        Map<Integer, Order> orderSeller = new HashMap<>();
        for (Map.Entry<Integer, Order> entry : order.entrySet()) {
            if (entry.getValue().getSellerName().equalsIgnoreCase(sellerUsername))
                orderSeller.put(entry.getKey(), entry.getValue());
        }

        return orderSeller;
    }

    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/orderData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(order);
        o.close();
        f.close();
    }

    public void loadData() {
        try {
            FileInputStream fi = new FileInputStream("src/main/resources/data/orderData.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            // read object from file
            order = (Map<Integer, Order>) oi.readObject();
            oi.close();
            fi.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void wipeAll() {
        order.clear();
    }

    public int viewAllOrders() {
        return order.size();
    }
}


