package com.stacs.group3.ShoppingSystemApp.controller;

import com.stacs.group3.ShoppingSystemApp.model.*;
import com.stacs.group3.ShoppingSystemApp.service.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * This is the controller class for the Alpha application.
 */
public class AlphaController implements Serializable {
    UserService userService = new UserService();
    RequestService requestService = new RequestService();

    ProductService productService = new ProductService();
    CartService cartService = new CartService();

    OrderService orderService = new OrderService();

    /**
     * User creation to the database is handled here (for both Admin and Customer).
     */

    public void addUser(String firstName, String lastName, String username, String email, String password, String accountType) {
        userService.addUser(firstName, lastName, username, email, password, accountType);
    }


    public Map<String, User> adminLogin(String username, String password) {

        return userService.adminLogin(username, password);
    }

    public Map<String, User> viewAllUsers() {
        return userService.viewAllUsers();
    }

    public Map<String, User> userLogin(String username, String password) {
        return userService.userLogin(username, password);
    }

    public void saveData() throws IOException {
        userService.saveData();
        requestService.saveData();
        productService.saveData();
        cartService.saveData();
        orderService.saveData();
    }

    public void loadData() {
        userService.loadData();
        requestService.loadData();
        productService.loadData();
        cartService.loadData();
        orderService.loadData();
    }

    public void adminGenerate() {
        userService.adminGenerate();
    }

    public void deleteUserViaAdmin(String username) {
        userService.deleteUserViaAdmin(username);
    }

    public void deleteSelfAccount(String username, String password) {
        userService.deleteSelfAccount(username, password);
    }

    public void updateUserPermission(String username, String requestType) {
        userService.updateUserPermission(username, requestType);
    }

    public void storeRequest(String username, String requestType) {
        requestService.storeRequest(username, requestType);
    }

    public Map<String, Request> viewRequests() {
        return requestService.getRequests();
    }

    public void addProduct(String productID, String productName, String productDescription, String author, String productPrice, String productQuantity, String productCategory, String sellerUsername) {
        productService.addProduct(productID, productName, productDescription, author, productPrice, productQuantity, productCategory, sellerUsername);
    }

    public Map<String, Product> searchProductByName(String productName) {
        return productService.searchProductByName(productName);
    }

    public Map<String, Product> searchProductByCategory(String productCategory) {
        return productService.searchProductByCategory(productCategory);
    }

    public Map<String, Product> searchProductByAuthor(String productAuthor) {
        return productService.searchProductByAuthor(productAuthor);
    }

    public Map<String, Product> viewAllProducts() {
        return productService.viewAllProducts();
    }

    public void deleteProduct(String productID, String sellerUsername) {
        productService.deleteProduct(productID, sellerUsername);
    }

    public Map<String, Product> viewSellerProducts(String sellerUsername) {
        return productService.viewSellerProducts(sellerUsername);
    }


    public boolean checkValidationToUpdate(String sellerUsername, String productID) {
        return productService.checkValidationToUpdate(sellerUsername, productID);
    }

    public void updateProductName(String productID, String productName) {
        productService.updateProductName(productID, productName);
    }

    public void updateProductDescription(String productID, String productDescription) {
        productService.updateProductDescription(productID, productDescription);

    }

    public void updateProductPrice(String productID, String productPrice) {
        productService.updateProductPrice(productID, productPrice);
    }

    public void updateProductQuantity(String productID, String productQuantity) {

        productService.updateProductQuantity(productID, productQuantity);
    }

    public void updateProductCategory(String productID, String productCategory) {
        productService.updateProductCategory(productID, productCategory);
    }

    public void updateProductAuthor(String productID, String productAuthor) {
        productService.updateProductAuthor(productID, productAuthor);
    }


    public Map<Integer, Cart> viewCart(String customerName) {
        return cartService.viewCart(customerName);
    }


    public void addToCart(int cartID, String productID, String customerName, String productName, float productPrice, int productQuantity, float productTotal, String sellerName) {
        cartService.addToCart(cartID, productID, customerName, productName, productPrice, productQuantity, productTotal, sellerName);
    }

    public void deleteItemFromCart(String cartID) {
        cartService.deleteItemFromCart(cartID);
    }


    public void addOrder(int orderID, String productID, String productName, float productPrice, int productQuantity, float productTotal, String sellerName, String customerName) {
        orderService.addOrder(orderID, productID, productName, productPrice, productQuantity, productTotal, sellerName, customerName);
    }

    public Map<Integer, Order> getOrderByCustomer(String customerName) {
        return orderService.getOrderByCustomer(customerName);
    }


    public Map<Integer, Order> getOrderBySeller(String sellerUsername) {
        return orderService.getOrderBySeller(sellerUsername);
    }

    public void updateProductAfterOrder(String productID, int productQuantity) {
        productService.updateProductAfterOrder(productID, productQuantity);
    }
}