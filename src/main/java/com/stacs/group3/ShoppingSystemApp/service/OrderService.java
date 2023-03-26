package com.stacs.group3.ShoppingSystemApp.service;


import com.stacs.group3.ShoppingSystemApp.model.Order;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OrderService implements Serializable {

    private Map<Integer, Order> order = new HashMap<>();


    public void addOrder(int orderID, String productID, String productName, float productPrice, int productQuantity, float productTotal, String sellerName, String userName) {
        order.put(orderID, new Order(orderID, productID, productName, productPrice, productQuantity, productTotal, sellerName, userName));
    }

    public Map<Integer,Order>  getOrderByCustomer(String userName) {
        Map<Integer, Order> orderCustomer = new HashMap<>();
        for (Map.Entry<Integer, Order> entry : order.entrySet()) {
            if(entry.getValue().getCustomerName().equalsIgnoreCase(userName))
                orderCustomer.put(entry.getKey(), entry.getValue());
        }
        return orderCustomer;
    }

    public Map<Integer,Order>  getOrderBySeller(String sellerUsername) {
        Map<Integer, Order> orderSeller = new HashMap<>();
        for (Map.Entry<Integer, Order> entry : order.entrySet()) {
            if(entry.getValue().getSellerName().equalsIgnoreCase(sellerUsername))
                orderSeller.put(entry.getKey(), entry.getValue());
        }

        return orderSeller;
    }

    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/orderData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(order);
        o.close();
        f.close();
    }

    public void loadData()  {
        try{
            FileInputStream fi = new FileInputStream("src/main/resources/orderData.ser");
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


