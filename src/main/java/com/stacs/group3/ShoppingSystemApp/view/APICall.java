package com.stacs.group3.ShoppingSystemApp.view;

import com.stacs.group3.ShoppingSystemApp.model.Cart;
import com.stacs.group3.ShoppingSystemApp.model.Order;
import com.stacs.group3.ShoppingSystemApp.model.Product;
import com.stacs.group3.ShoppingSystemApp.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * The APICall class which the terminal client will call.
 */
public class APICall {
    private WebClient webClient;

    /**
     * Constructs the APICall with the given basic uri address.
     * example
     *      "http://localhost:8080"
     * @param baseURI the basic uri address
     */
    public APICall(String baseURI) {
        this.webClient = WebClient.create(baseURI);
    }

    /**
     * Calls the API of addUser using POST method.
     * @param firstName the first name of user
     * @param lastName the last name of user
     * @param username the username
     * @param email the email address
     * @param password the password
     * @param accountType the account type user wants to be
     */
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

    /**
     * Calls the API of adminLogin using GET method.
     * @param username the username
     * @param password the password
     * @return the admin user's account information
     */
    public Map callAdminLogin(String username, String password) {
        return webClient.get()
                .uri("/userService/adminLogin?user=" + username + "&password=" + password)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    /**
     * Calls the API of viewALLUsers using GET method.
     * @return Map of all users in the system
     */
    public Map callViewAllUsers() {
        return webClient.get()
                .uri("/userService/viewAll")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /**
     * Calls the API of userLogin using GET method.
     * @param username the username
     * @param password the password
     * @return the user's account information
     */
    public Map callUserLogin(String username, String password) {
        return webClient.get()
                .uri("/userService/Login?user=" + username + "&password=" + password)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    /**
     * Calls the API of adminGenerate using POST method.
     */
    public void callAdminGenerate() {
        webClient.post()
                .uri("/userService/adminGenerate")
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    /**
     * Calls the API of deleteUserViaAdmin using POST method.
     * @param username the username
     */
    public void callDeleteUserViaAdmin(String username) {
        webClient.post()
                .uri("/userService/deleteViaAdmin/" + username)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    /**
     * Calls the API of deleteSelfAccount using POST method.
     * @param username the username
     * @param password the password
     */
    public void callDeleteSelfAccount(String username, String password) {
        webClient.post()
                .uri("/userService/delete?user=" + username + "&password=" + password)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * Calls the API of updateUserPermission using POST method.
     * @param username the username
     * @param requestType the account type
     */
    public void callUpdateUserPermission(String username, String requestType) {
        webClient.post()
                .uri("/requestService/updatePermission/" + username + "/" + requestType)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * CallsT the API of storeRequest using POST method.
     * @param username the username
     * @param requestType the account type
     */
    public void callStoreRequest(String username, String requestType) {
        webClient.post()
                .uri("/requestService/storeRequest/" + username + "/" + requestType)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    /**
     * Calls the API of viewRequests using GET method.
     * @return all the requests
     */
    public Map callViewRequests() {
        return webClient.get()
                .uri("/requestService/viewRequests")
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    /**
     * Calls the API of addProduct using POST method.
     * @param productID the product id
     * @param productName the product name
     * @param productDescription the product description
     * @param author the author of book
     * @param productPrice the price of product
     * @param productQuantity the quantity of product
     * @param productCategory the category of product
     * @param sellerUsername the seller username of product
     */
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

    /**
     * Calls the API of searchProductByName using GET method.
     * @param productName the product name
     * @return the products named the given product name
     */
    public Map callSearchProductByName(String productName) {
        return webClient.get()
                .uri("/productService/searchName/" + productName)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /**
     * Calls the API of searchProductByCategory using GET method.
     * @param productCategory the product category
     * @return all products belong to the given category
     */
    public Map callSearchProductByCategory(String productCategory) {
        return webClient.get()
                .uri("/productService/searchCategory/" + productCategory)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /**
     * Calls the API of searchProductByAuthor using GET method.
     * @param productAuthor the author of book
     * @return all products which the author is the given author
     */
    public Map callSearchProductByAuthor(String productAuthor) {
        return webClient.get()
                .uri("/productService/searchAuthor/" + productAuthor)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /**
     * Calls the API of viewAllProducts using GET method.
     * @return all products in the system
     */
    public Map callViewAllProducts() {
        return webClient.get()
                .uri("/productService/viewAll")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /**
     * Calls the API of deleteProduct using POST method.
     * @param productID the product id
     * @param sellerUsername the seller username
     */
    public void callDeleteProduct(String productID, String sellerUsername) {
        webClient.post()
                .uri("/productService/delete?id=" + productID + "&seller=" + sellerUsername)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * Calls the API of viewSellerProducts using GET method.
     * @param sellerUsername the seller username
     * @return the products which the seller is given username
     */
    public Map callViewSellerProducts(String sellerUsername) {
        return webClient.get()
                .uri("/productService/view/" + sellerUsername)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    /**
     * Calls the API of checkValidationToUpdate using GET method.
     * @param sellerUsername the seller username
     * @param productID the product id
     * @return whether the user has the permission to update the product
     */
    public boolean callCheckValidationToUpdate(String sellerUsername, String productID) {
        return Boolean.TRUE.equals(webClient.get()
                .uri("/productService/check?seller=" + sellerUsername + "&id=" + productID)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());

    }

    /**
     * Calls the API of updateProductName using POST method.
     * @param productID the product id
     * @param productName the product name
     */
    public void callUpdateProductName(String productID, String productName) {
        webClient.post()
                .uri("/productService/updateName?id=" + productID + "&name=" + productName)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    /**
     * Calls the API of updateProductDescription using POST method.
     * @param productID the product id
     * @param productDescription the product description
     */
    public void callUpdateProductDescription(String productID, String productDescription) {
        webClient.post()
                .uri("/productService/updateDescription?id=" + productID + "&description=" + productDescription)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    /**
     * Calls the API of updateProductPrice using POST method.
     * @param productID the product id
     * @param productPrice the product price
     */
    public void callUpdateProductPrice(String productID, String productPrice) {
        webClient.post()
                .uri("/productService/updatePrice?id=" + productID + "&price=" + productPrice)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    /**
     * Calls the API of updateProductQuantity using POST method.
     * @param productID the product id
     * @param productQuantity the product quantity
     */
    public void callUpdateProductQuantity(String productID, String productQuantity) {
        webClient.post()
                .uri("/productService/updateQuantity?id=" + productID + "&quantity=" + productQuantity)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    /**
     * Calls the API of updateProductCategory using POST method.
     * @param productID the product id
     * @param productCategory the product category
     */
    public void callUpdateProductCategory(String productID, String productCategory) {
        webClient.post()
                .uri("/productService/updateCategory?id=" + productID + "&category=" + productCategory)
                .retrieve()
                .bodyToMono(Void.class)
                .block();


    }

    /**
     * Calls the API of updateProductAuthor using POST method.
     * @param productID the product id
     * @param productAuthor the product author
     */
    public void callUpdateProductAuthor(String productID, String productAuthor) {
        webClient.post()
                .uri("/productService/updateAuthor?id=" + productID + "&author=" + productAuthor)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    /**
     * Calls the API of viewCart using GET method.
     * @param username the username
     * @return all carts of the given user
     */
    public Map callViewCart(String username) {
        return webClient.get()
                .uri("/cartService/view/" + username)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    /**
     * Calls the API of addCart using POST method.
     * @param cartID the cart id
     * @param productID the product id
     * @param customerName the customer username
     * @param productName the product name
     * @param productPrice the product price
     * @param productQuantity the product quantity
     * @param productTotal the product total
     * @param sellerName the seller username
     */
    public void callAddToCart(int cartID, String productID, String customerName, String productName, float productPrice, int productQuantity, float productTotal, String sellerName) {
        Cart cart = new Cart(cartID, productID, customerName, productName,
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

    /**
     * Calls the API of deleteItermFromCart using POST method.
     * @param cartID the cart id
     */
    public void callDeleteItemFromCart(String cartID) {
        webClient.post()
                .uri("/cartService/delete/" + cartID)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    /**
     * Calls the API of addOrder using POST method.
     * @param orderID the order id
     * @param productID the product id
     * @param productName the product name
     * @param productPrice the product price
     * @param productQuantity the product quantity
     * @param productTotal the product total
     * @param sellerName the seller username
     * @param customerName the customer username
     */
    public void callAddOrder(int orderID, String productID, String productName, float productPrice, int productQuantity, float productTotal, String sellerName, String customerName) {
        Order order = new Order(orderID, productID, productName,
                productPrice, productQuantity, productTotal, sellerName, customerName);
        webClient.post()
                .uri("/orderService/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(order))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * Calls the API of getOrderByCustomer using GET method.
     * @param username the customer username
     * @return all orders which the buyer is given user
     */
    public Map callGetOrderByCustomer(String username) {
        return webClient.get()
                .uri("/orderService/searchName/" + username)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /**
     * Calls the API of getOrderBySeller using GET method.
     * @param sellerUsername the seller username
     * @return all orders which the seller is the given username
     */
    public Map callGetOrderBySeller(String sellerUsername) {
        return webClient.get()
                .uri("/orderService/searchSeller/" + sellerUsername)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

    }

    /**
     * Calls the API of updateProductAfterOrder using POST method.
     * @param productID the product id
     * @param productQuantity the quantity of product in the order
     */
    public void callUpdateProductAfterOrder(String productID, int productQuantity) {
        webClient.post()
                .uri("/cartService/update/" + productID + "/" + productQuantity)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * Calls the API of save using POST method.
     */
    public void callSave() {
        webClient.post()
                .uri("/save")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * Calls the API of load using POST method.
     */
    public void callLoad() {
        webClient.post()
                .uri("/load")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
