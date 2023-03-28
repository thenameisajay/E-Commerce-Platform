package com.stacs.group3.ShoppingSystemApp.view;

import com.stacs.group3.ShoppingSystemApp.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class APICall {
    private WebClient webClient;

    public APICall(String baseURI) {
        this.webClient = WebClient.create(baseURI);
    }

    public void callAddUser(String firstName, String lastName, String username, String email, String password, String accountType) {
        User user = new User(firstName, lastName, username, email, password, accountType);
        webClient.post()
                .uri("/userService/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public Map callAdminLogin(String username, String password) {
        return webClient.get()
                .uri("/userService/adminLogin?user=" + username + "&password=" + password)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    public Map callViewAllUsers() {
        return webClient.get()
                .uri("/userService/viewAll")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Map callUserLogin(String username, String password) {
        return webClient.get()
                .uri("/userService/Login?user=" + username + "&password=" + password)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    public void callAdminGenerate() {
        webClient.post()
                .uri("/userService/adminGenerate")
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public void callDeleteUserViaAdmin(String username) {
        webClient.post()
                .uri("/userService/deleteViaAdmin/" + username)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public void callDeleteSelfAccount(String username, String password) {
        webClient.post()
                .uri("/userService/delete?user=" + username + "&password=" + password)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void callUpdateUserPermission(String username, String customer) {
        webClient.post()
                .uri("/requestService/updatePermission/" + username + "/" + customer)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void callStoreRequest(String username, String requestType) {
        webClient.post()
                .uri("/requestService/storeRequest/" + username + "/" + requestType)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public Map callViewRequests() {
        return webClient.get()
                .uri("/requestService/viewRequests")
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    public void callAddProduct(String productID, String productName, String productDescription,
                               String author, String productPrice, String productQuantity,
                               String productCategory, String sellerUsername) {
        Product product = new Product(productID, productName, productDescription, author,
                productPrice, productQuantity, productCategory, sellerUsername);
        webClient.post()
                .uri("/productService/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(product))
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public Map callSearchProductByName(String productName) {
        return webClient.get()
                .uri("/productService/searchName/" + productName)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Map callSearchProductByCategory(String productCategory) {
        return webClient.get()
                .uri("/productService/searchCategory/" + productCategory)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Map callSearchProductByAuthor(String productAuthor) {
        return webClient.get()
                .uri("/productService/searchAuthor/" + productAuthor)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public Map callViewAllProducts() {
        return webClient.get()
                .uri("/productService/viewAll")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    public void callDeleteProduct(String productID, String sellerUsername) {
        webClient.post()
                .uri("/productService/delete?id=" + productID + "&seller=" + sellerUsername)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public Map callViewSellerProducts(String sellerUsername) {
        return webClient.get()
                .uri("/productService/view/" + sellerUsername)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    public boolean callCheckValidationToUpdate(String sellerUsername, String productID) {
        return Boolean.TRUE.equals(webClient.get()
                .uri("/productService/check?seller=" + sellerUsername + "&id=" + productID)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());

    }

    public void callUpdateProductName(String productID, String productName) {
        webClient.post()
                .uri("/productService/updateName?id=" + productID + "&name=" + productName)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public void callUpdateProductDescription(String productID, String productDescription) {
        webClient.post()
                .uri("/productService/updateDescription?id=" + productID + "&description=" + productDescription)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    public void callUpdateProductPrice(String productID, String productPrice) {
        webClient.post()
                .uri("/productService/updatePrice?id=" + productID + "&price=" + productPrice)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    public void callUpdateProductQuantity(String productID, String productQuantity) {
        webClient.post()
                .uri("/productService/updateQuantity?id=" + productID + "&quantity=" + productQuantity)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    public void callUpdateProductCategory(String productID, String productCategory) {
        webClient.post()
                .uri("/productService/updateCategory?id=" + productID + "&category=" + productCategory)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    public void callUpdateProductAuthor(String productID, String productAuthor) {
        webClient.post()
                .uri("/productService/updateAuthor?id=" + productID + "&author=" + productAuthor)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public Map callViewCart(String username) {
        return webClient.get()
                .uri("/cartService/view/" + username)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    public void callAddToCart(int cartID, String productID, String userName, String productName, float productPrice, int productQuantity, float productTotal, String sellerName) {
        Cart cart = new Cart(cartID, productID, userName, productName,
                productPrice, productQuantity, productTotal, sellerName);
        webClient.post()
                .uri("/cartService/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(cart))
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public void callDeleteItemFromCart(String cartID) {
        webClient.post()
                .uri("/cartService/delete/" + cartID)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public void callAddOrder(int orderID, String productID, String productName, float productPrice, int productQuantity, float productTotal, String sellerName, String userName) {
        Order order = new Order(orderID, productID, productName,
                productPrice, productQuantity, productTotal, sellerName, userName);
        webClient.post()
                .uri("/orderService/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(order))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public Map callGetOrderByCustomer(String username) {
        return webClient.get()
                .uri("/orderService/searchName/" + username)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    public Map callGetOrderBySeller(String sellerUsername) {
        return webClient.get()
                .uri("/orderService/searchSeller/" + sellerUsername)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    public void callUpdateProductAfterOrder(String productID, int productQuantity) {
        webClient.post()
                .uri("/cartService/update/" + productID + "/" + productQuantity)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    public void callSave() {
        webClient.post()
                .uri("/save")
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    public void callLoad() {
        webClient.post()
                .uri("/load")
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }
}
