package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

/**
 * Order class to store the details of an order.
 * This class is used to store the details of an order.
 * It contains the following attributes:
 * orderID, productID, productName, productPrice, productQuantity, productTotal, customerName, sellerName
 * It also contains the following methods:
 * getOrderID, setOrderID, getProductID, setProductID, getProductName, setProductName, getProductPrice, setProductPrice, getProductQuantity, setProductQuantity, getProductTotal, setProductTotal, getCustomerName, setCustomerName, getSellerName, setSellerName
 *
 */
public class Order implements Serializable {

    private int orderID;

    private String productID;

    private String productName;

    private float productPrice;

    private int productQuantity;

    private float productTotal;

    private String customerName;

    private String sellerName;

    public Order(int orderID, String productID, String productName, float productPrice, int productQuantity, float productTotal, String sellerName, String customerName) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productTotal = productTotal;
        this.sellerName = sellerName;
        this.customerName = customerName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public float getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(float productTotal) {
        this.productTotal = productTotal;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
