package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

/**
 * This class is used to create a Cart object.
 * It contains the following attributes:
 * cartID, productId, customerName, productName, productPrice, productQuantity, productTotal, sellerName
 * It also contains the following methods:
 * getCartID, setCartID, getProductId, setProductId, getCustomerName, setCustomerName, getProductName, setProductName, getProductPrice, setProductPrice, getProductQuantity, setProductQuantity, getProductTotal, setProductTotal, getSellerName, setSellerName
 */
public class Cart implements Serializable {

    private int cartID;
    private String productID;
    private String customerName;
    private String productName;
    private float productPrice;
    private int productQuantity;
    private float productTotal;
    private String sellerName;

    public Cart(int cartID, String productID, String customerName, String productName, float productPrice, int productQuantity, float productTotal, String sellerName) {
        this.cartID = cartID;
        this.productID = productID;
        this.customerName = customerName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productTotal = productTotal;
        this.sellerName = sellerName;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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


    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}