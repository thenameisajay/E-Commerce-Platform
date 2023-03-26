package com.stacs.group3.ShoppingSystemApp.service;

import com.stacs.group3.ShoppingSystemApp.model.Cart;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartService implements Serializable {

    private Map<Integer, Cart> cart = new HashMap<>();

    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/cartData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(cart);
        o.close();
        f.close();
    }

    public void loadData() {
        try {
            FileInputStream fi = new FileInputStream("src/main/resources/cartData.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            // read object from file
            cart = (Map<Integer, Cart>) oi.readObject();
            oi.close();
            fi.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void wipeAll() {
        cart.clear();
    }


    public Map<Integer, Cart> viewCart(String userName) {
        Map<Integer, Cart> cartView = new HashMap<>();
        for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
            String storedName = entry.getValue().getCustomerName();
            if (storedName.equalsIgnoreCase(userName)) {
                cartView.put(entry.getKey(), entry.getValue());
            }
        }
        return cartView;
    }


    public void addToCart(int cartID, String productID, String userName, String productName, float productPrice, int productQuantity, float productTotal, String sellerName) {
        // If product ID already exists in cart, throw argument to delete the old one and add the new one
        for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
            String storedProductID = entry.getValue().getProductId();
            String storedUserName = entry.getValue().getCustomerName();
            if (storedProductID.equalsIgnoreCase(productID) && storedUserName.equalsIgnoreCase(userName)) {
                throw new IllegalArgumentException("Product already exists in cart. Please delete the old entry and add the new changes.");
            }
        }
        if (productID.isEmpty() | productID == null) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        } else {
            cart.put(cartID, new Cart(cartID, productID, userName, productName, productPrice, productQuantity, productTotal, sellerName));
        }
    }

    public void deleteItemFromCart(String cartID) {
        // First check if cartID is not empty
        if (cartID.isEmpty() | cartID == null) {
            throw new IllegalArgumentException("Cart ID cannot be empty.");
        }
        int cartIDInt = Integer.parseInt(cartID);
        if (cart.containsKey(cartIDInt)) {
            cart.remove(cartIDInt);
        } else {
            throw new IllegalArgumentException("Product does not exist.");
        }

    }

    public void clearCart(String userName) {
        // First check if cartID is not empty
        if (userName.isEmpty() | userName == null) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
            String storedUserName = entry.getValue().getCustomerName();
            if (storedUserName.equalsIgnoreCase(userName)) {
                cart.remove(entry.getKey());
            }
        }
    }

    public Collection<Cart> viewAllCarts() {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("No cart");
        }
        return cart.values();
    }
}

