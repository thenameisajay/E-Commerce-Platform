package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

/**
 * Product class to store product information.
 * This class is used to store the details of a product.
 * It contains the following attributes:
 * productID, productName, productDescription, author, productPrice, productQuantity, productCategory, sellerUsername
 * It also contains the following methods:
 * getProductID, setProductID, getProductName, setProductName, getProductDescription, setProductDescription, getAuthor, setAuthor, getProductPrice, setProductPrice, getProductQuantity, setProductQuantity, getProductCategory, setProductCategory, getSellerUsername, setSellerUsername
 */
public class Product implements Serializable {
    private String productID;
    private String productName;
    private String productDescription;

    private String author;
    private String productPrice;
    private String productQuantity;
    private String productCategory;

    private String sellerUsername;

    public Product(String productID, String productName, String productDescription, String author, String productPrice, String productQuantity, String productCategory, String sellerUsername) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.author = author;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.sellerUsername = sellerUsername;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

}


