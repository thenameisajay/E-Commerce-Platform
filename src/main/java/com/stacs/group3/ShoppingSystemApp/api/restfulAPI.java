package com.stacs.group3.ShoppingSystemApp.api;

import com.stacs.group3.ShoppingSystemApp.controller.AlphaController;
import com.stacs.group3.ShoppingSystemApp.model.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class restfulAPI {
    AlphaController alphaController = new AlphaController();

    @PostMapping("/userService/add")
    public void addUser(@RequestBody Map<String, String> user) {
        String firstName = user.get("firstName");
        String lastName = user.get("lastName");
        String username = user.get("username");
        String email = user.get("email");
        String password = user.get("password");
        String accountType = user.get("accountType");
        alphaController.addUser(firstName, lastName, username, email, password, accountType);
    }

    @GetMapping("/userService/adminLogin")
    public Map<String, User> adminLogin(
            @RequestParam(name = "user") String username,
            @RequestParam(name = "password") String password
    ) {
        return alphaController.adminLogin(username, password);
    }

    @GetMapping("/userService/viewAll")
    public Map<String, User> viewAllUsers() {
        return alphaController.viewAllUsers();
    }

    @GetMapping("/userService/Login")
    public Map<String, User> userLogin(
            @RequestParam(name = "user") String username,
            @RequestParam(name = "password") String password
    ) {
        return alphaController.userLogin(username, password);
    }

    @PostMapping("/userService/adminGenerate")
    public void adminGenerate() {
        alphaController.adminGenerate();
    }

    @PostMapping("/userService/deleteViaAdmin/{username}")
    public void deleteUserViaAdmin(@PathVariable String username) {
        alphaController.deleteUserViaAdmin(username);
    }

    @PostMapping("/userService/delete")
    public void deleteSelfAccount(
            @RequestParam(name = "user") String username,
            @RequestParam(name = "password") String password
    ) {
        alphaController.deleteSelfAccount(username, password);
    }


    @PostMapping("/requestService/updatePermission/{username}/{customer}")
    public void updateUserPermission(@PathVariable String username, @PathVariable String customer) {
        alphaController.updateUserPermission(username, customer);
    }

    @PostMapping("/requestService/storeRequest/{username}/{requestType}")
    public void storeRequest(@PathVariable String username, @PathVariable String requestType) {
        alphaController.storeRequest(username, requestType);
    }

    @GetMapping("/requestService/viewRequests")
    public Map<String, Request> viewRequests() {
        return alphaController.viewRequests();
    }


    @PostMapping("/productService/add")
    public void addProduct(@RequestBody Map<String, String> product) {
        String productID = product.get("productID");
        String productName = product.get("productName");
        String productDescription = product.get("productDescription");
        String author = product.get("author");
        String productPrice = product.get("productPrice");
        String productQuantity = product.get("productQuantity");
        String productCategory = product.get("productCategory");
        String sellerUsername = product.get("sellerUsername");
        alphaController.addProduct(productID, productName, productDescription, author, productPrice, productQuantity, productCategory, sellerUsername);
    }

    @GetMapping("/productService/searchName/{productName}")
    public Map<String, Product> searchProductByName(@PathVariable String productName) {
        return alphaController.searchProductByName(productName);
    }

    @GetMapping("/productService/searchCategory/{productCategory}")
    public Map<String, Product> searchProductByCategory(@PathVariable String productCategory) {
        return alphaController.searchProductByCategory(productCategory);
    }

    @GetMapping("/productService/searchAuthor/{productAuthor}")
    public Map<String, Product> searchProductByAuthor(@PathVariable String productAuthor) {
        return alphaController.searchProductByAuthor(productAuthor);
    }

    @GetMapping("/productService/viewAll")
    public Map<String, Product> viewAllProducts() {
        return alphaController.viewAllProducts();
    }

    @DeleteMapping("/productService/delete")
    public void deleteProduct(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "seller") String sellerUsername) {
        alphaController.deleteProduct(productID, sellerUsername);
    }

    @GetMapping("/productService/view/{sellerUsername}")
    public Map<String, Product> viewSellerProducts(@PathVariable String sellerUsername) {
        return alphaController.viewSellerProducts(sellerUsername);
    }

    @GetMapping("/productService/check")
    public boolean checkValidationToUpdate(
            @RequestParam(name = "seller") String sellerUsername,
            @RequestParam(name = "id") String productID
    ) {
        return alphaController.checkValidationToUpdate(sellerUsername, productID);
    }

    @PostMapping("/productService/updateName")
    public void updateProductName(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "name") String productName) {
        alphaController.updateProductName(productID, productName);
    }

    @PostMapping("/productService/updateDescription")
    public void updateProductDescription(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "name") String productDescription) {
        alphaController.updateProductDescription(productID, productDescription);
    }

    @PostMapping("/productService/updatePrice")
    public void updateProductPrice(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "price") String productPrice) {
        alphaController.updateProductPrice(productID, productPrice);
    }

    @PostMapping("/productService/updateQuantity")
    public void updateProductQuantity(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "quantity") String productQuantity) {
        alphaController.updateProductQuantity(productID, productQuantity);
    }

    @PostMapping("/productService/updateCategory")
    public void updateProductCategory(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "category") String productCategory) {
        alphaController.updateProductCategory(productID, productCategory);
    }

    @PostMapping("/productService/updateAuthor")
    public void updateProductAuthor(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "author") String productAuthor) {
        alphaController.updateProductAuthor(productID, productAuthor);
    }


    @GetMapping("/cartService/view/{username}")
    public Map<Integer, Cart> viewCart(@PathVariable String username) {
        return alphaController.viewCart(username);
    }

    @PostMapping("/cartService/add")
    public void addToCart(@RequestBody Map<Integer, String> cart) {
        int cartID = Integer.parseInt(cart.get("cartID"));
        String productID = cart.get("productID");
        String userName = cart.get("userName");
        String productName = cart.get("productName");
        float productPrice = Float.parseFloat(cart.get("productPrice"));
        int productQuantity = Integer.parseInt(cart.get("productQuantity"));
        float productTotal = Float.parseFloat(cart.get("productTotal"));
        String sellerName = cart.get("sellerName");
        alphaController.addToCart(cartID, productID, userName, productName, productPrice, productQuantity, productTotal, sellerName);
    }

    @DeleteMapping("/cartService/delete/{cartID}")
    public void deleteItemFromCart(@PathVariable int cartID) {
        alphaController.deleteItemFromCart(String.valueOf(cartID));
    }


    @PostMapping("/orderService/add")
    public void addOrder(@RequestBody Map<Integer, String> order) {
        int orderID = Integer.parseInt(order.get("orderID"));
        String productID = order.get("productID");
        String userName = order.get("userName");
        String productName = order.get("productName");
        float productPrice = Float.parseFloat(order.get("productPrice"));
        int productQuantity = Integer.parseInt(order.get("productQuantity"));
        float productTotal = Float.parseFloat(order.get("productTotal"));
        String sellerName = order.get("sellerName");
        alphaController.addOrder(orderID, productID, productName, productPrice, productQuantity, productTotal, sellerName, userName);
    }

    @GetMapping("/orderService/searchName/{username}")
    public Map<Integer, Order> getOrderByCustomer(@PathVariable String username) {
        return alphaController.getOrderByCustomer(username);
    }

    @GetMapping("/orderService/searchSeller/{sellerUsername}")
    public Map<Integer, Order> getOrderBySeller(@PathVariable String sellerUsername) {
        return alphaController.getOrderBySeller(sellerUsername);
    }

    @PostMapping("/orderService/update/{productID}/{productQuantity}")
    public void updateProductAfterOrder(@PathVariable String productID, @PathVariable int productQuantity) {
        alphaController.updateProductAfterOrder(productID, productQuantity);
    }

    @PostMapping("/save")
    public void saveData() throws IOException {
        alphaController.saveData();
    }

    @PostMapping("/load")
    public void loadData() {
        alphaController.loadData();
    }

}