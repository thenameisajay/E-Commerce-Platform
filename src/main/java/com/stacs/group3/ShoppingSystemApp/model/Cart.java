package com.stacs.group3.ShoppingSystemApp.model;

import java.io.Serializable;

public class Cart implements Serializable {

    private int cartId;
    private String productId;
    private String customerName;
    private String productName;
    private float productPrice;
    private int productQuantity;
    private float ProductTotal;
    private String sellerName;

    public Cart(int cartId, String ProductId, String customerName, String productName, float productPrice, int productQuantity, float productTotal, String sellerName) {
        this.cartId = cartId;
        this.productId = ProductId;
        this.customerName = customerName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.ProductTotal = productTotal;
        this.sellerName = sellerName;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        return ProductTotal;
    }

    public void setProductTotal(float productTotal) {
        ProductTotal = productTotal;
    }


    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}