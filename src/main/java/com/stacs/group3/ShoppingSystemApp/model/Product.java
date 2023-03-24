package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private String description;

    private String author;
    private String price;
    private String quantity;
    private String category;

    private String sellerUsername;

    public Product(String id, String name, String description, String author, String price, String quantity, String category, String sellerUsername) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.sellerUsername = sellerUsername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

}


