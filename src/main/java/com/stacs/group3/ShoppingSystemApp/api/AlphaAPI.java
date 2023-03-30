package com.stacs.group3.ShoppingSystemApp.api;

import com.stacs.group3.ShoppingSystemApp.controller.AlphaController;
import com.stacs.group3.ShoppingSystemApp.model.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * The restful API for the Alpha system.
 */
@CrossOrigin(origins = "*")
@RestController
public class AlphaAPI {
    AlphaController alphaController = new AlphaController();

    /**
     * Restful API with POST method to create a new user.
     * "/userService/add" with the request body.
     * <p>
     * Example
     * curl -X POST https://localhost:8080/userService/add
     * -H "Content-Type: application/json"
     * -d '{"firstName":Joe,"lastName":Smith,"username":js123,"emial":js123@example.com,"password":123456789,"accountType":seller}'
     *
     * @param user the user's information
     */
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

    /**
     * Restful API with GET method for admin to log in.
     * "/userService/adminLogin?user=example&password=example"
     *
     * @param username the username
     * @param password the password
     * @return the admin information
     */
    @GetMapping("/userService/adminLogin")
    public Map<String, User> adminLogin(
            @RequestParam(name = "user") String username,
            @RequestParam(name = "password") String password
    ) {
        return alphaController.adminLogin(username, password);
    }

    /**
     * Restful API with GET method to get all the users.
     * "/userService/viewAll"
     *
     * @return all the users in the system
     */
    @GetMapping("/userService/viewAll")
    public Map<String, User> viewAllUsers() {
        return alphaController.viewAllUsers();
    }

    /**
     * Restful API with GET method for user to log in.
     * "/userService/Login?user=example&password=example"
     *
     * @param username the username of user
     * @param password the password of user
     * @return the user information
     */
    @GetMapping("/userService/Login")
    public Map<String, User> userLogin(
            @RequestParam(name = "user") String username,
            @RequestParam(name = "password") String password
    ) {
        return alphaController.userLogin(username, password);
    }

    /**
     * Restful API with POST method to generate an admin.
     * "/userService/adminGenerate"
     * <p>
     * The username of default admin is "admin"
     */
    @PostMapping("/userService/adminGenerate")
    public void adminGenerate() {
        alphaController.adminGenerate();
    }

    /**
     * Restful API with POST method to delete a user through admin.
     * "/userService/deleteViaAdmin/{username}"
     *
     * @param username the username of deleted user
     */
    @PostMapping("/userService/deleteViaAdmin/{username}")
    public void deleteUserViaAdmin(@PathVariable String username) {
        alphaController.deleteUserViaAdmin(username);
    }

    /**
     * Restful API with POST method to delete self account.
     * "/userService/delete?user=example&password=example"
     *
     * @param username the username of user
     * @param password the password of user
     */
    @PostMapping("/userService/delete")
    public void deleteSelfAccount(
            @RequestParam(name = "user") String username,
            @RequestParam(name = "password") String password
    ) {
        alphaController.deleteSelfAccount(username, password);
    }

    /**
     * Restful API with POST method to update one user's permission.
     * "/requestService/updatePermission/{username}/{requestType}"
     *
     * @param username    the username of user
     * @param requestType the accountType which user want to be
     */
    @PostMapping("/requestService/updatePermission/{username}/{requestType}")
    public void updateUserPermission(@PathVariable String username, @PathVariable String requestType) {
        alphaController.updateUserPermission(username, requestType);
    }

    /**
     * Restful API with POST method to request to change the user's permission.
     * "/requestService/storeRequest/{username}/{requestType}"
     *
     * @param username    the username of user
     * @param requestType the accountType which user wants to be
     */
    @PostMapping("/requestService/storeRequest/{username}/{requestType}")
    public void storeRequest(@PathVariable String username, @PathVariable String requestType) {
        alphaController.storeRequest(username, requestType);
    }

    /**
     * Restful API with GET method for admin to view all requests.
     * "/requestService/viewRequests"
     *
     * @return all the requests which user wants to change permission
     */
    @GetMapping("/requestService/viewRequests")
    public Map<String, Request> viewRequests() {
        return alphaController.viewRequests();
    }

    /**
     * Restful API with POST method to add a new product.
     * "/productService/add" with the request body
     * <p>
     * Example
     * curl -X POST https://localhost:8080/productService/add
     * -H "Content-Type: application/json"
     * -d '{"productID":example,"productName":example,"productDescription":example,"author":example,
     * "productPrice":example,"productQuantity":example,"productCategory":example,"sellerUsername":example}'
     *
     * @param product the information contains all product feature
     */
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

    /**
     * Restful API with GET method to search products by product name.
     * "/productService/searchName/{productName}"
     *
     * @param productName the product name of product
     * @return all the products called this name
     */
    @GetMapping("/productService/searchName/{productName}")
    public Map<String, Product> searchProductByName(@PathVariable String productName) {
        return alphaController.searchProductByName(productName);
    }

    /**
     * Restful API with GET method to search products by product category.
     * "/productService/searchCategory/{productCategory}"
     *
     * @param productCategory the category of the product
     * @return all products belong to this category
     */
    @GetMapping("/productService/searchCategory/{productCategory}")
    public Map<String, Product> searchProductByCategory(@PathVariable String productCategory) {
        return alphaController.searchProductByCategory(productCategory);
    }

    /**
     * Restful API with GET method to search products by book author.
     * "/productService/searchAuthor/{productAuthor}"
     *
     * @param productAuthor the author of the product(book)
     * @return all the products which is finished by this author
     */
    @GetMapping("/productService/searchAuthor/{productAuthor}")
    public Map<String, Product> searchProductByAuthor(@PathVariable String productAuthor) {
        return alphaController.searchProductByAuthor(productAuthor);
    }

    /**
     * Restful API with GET method to view all the products.
     * "/productService/viewAll"
     *
     * @return all the products in the system
     */
    @GetMapping("/productService/viewAll")
    public Map<String, Product> viewAllProducts() {
        return alphaController.viewAllProducts();
    }

    /**
     * Restful API with POST method to delete the product.
     * "/productService/delete?id=example&seller=example"
     *
     * @param productID      the product id
     * @param sellerUsername the seller username of the product
     */
    @PostMapping("/productService/delete")
    public void deleteProduct(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "seller") String sellerUsername) {
        alphaController.deleteProduct(productID, sellerUsername);
    }

    /**
     * Restful API with GET method to view all products which seller are selling.
     * "/productService/view/{sellerUsername}"
     *
     * @param sellerUsername the username of seller
     * @return all products which seller are selling
     */
    @GetMapping("/productService/view/{sellerUsername}")
    public Map<String, Product> viewSellerProducts(@PathVariable String sellerUsername) {
        return alphaController.viewSellerProducts(sellerUsername);
    }

    /**
     * Restful API with GET method to check weather the user have permission to modify the product.
     * "/productService/check?seller=example&id=example"
     *
     * @param sellerUsername the username of seller
     * @param productID      the product id
     * @return true if the seller has the permission to modify the product
     */
    @GetMapping("/productService/check")
    public boolean checkValidationToUpdate(
            @RequestParam(name = "seller") String sellerUsername,
            @RequestParam(name = "id") String productID
    ) {
        return alphaController.checkValidationToUpdate(sellerUsername, productID);
    }

    /**
     * Restful API with POST method to update the product name.
     * "/productService/updateName?id=example&name=example"
     *
     * @param productID   the product id
     * @param productName the product name
     */
    @PostMapping("/productService/updateName")
    public void updateProductName(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "name") String productName) {
        alphaController.updateProductName(productID, productName);
    }

    /**
     * Restful API with POST method to update the product description.
     * "/productService/updateDescription?id=example&description=example"
     *
     * @param productID          the product id
     * @param productDescription the product description
     */
    @PostMapping("/productService/updateDescription")
    public void updateProductDescription(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "description") String productDescription) {
        alphaController.updateProductDescription(productID, productDescription);
    }

    /**
     * Restful API with POST method to update the product price.
     * "/productService/updatePrice?id=example&price=example"
     *
     * @param productID    the product id
     * @param productPrice the product price
     */
    @PostMapping("/productService/updatePrice")
    public void updateProductPrice(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "price") String productPrice) {
        alphaController.updateProductPrice(productID, productPrice);
    }

    /**
     * Restful API with POST method to update the product quantity.
     * "/productService/updateQuantity?id=example&quantity=example"
     *
     * @param productID       the product id
     * @param productQuantity the product quantity
     */
    @PostMapping("/productService/updateQuantity")
    public void updateProductQuantity(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "quantity") String productQuantity) {
        alphaController.updateProductQuantity(productID, productQuantity);
    }

    /**
     * Restful API with POST method to update the product category.
     * "/productService/updateCategory?id=example&category=example"
     *
     * @param productID       the product id
     * @param productCategory the product category
     */
    @PostMapping("/productService/updateCategory")
    public void updateProductCategory(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "category") String productCategory) {
        alphaController.updateProductCategory(productID, productCategory);
    }

    /**
     * Restful API with POST method to update the product author.
     * "/productService/updateAuthor?id=example&author=example"
     *
     * @param productID     the product id
     * @param productAuthor the product author
     */
    @PostMapping("/productService/updateAuthor")
    public void updateProductAuthor(
            @RequestParam(name = "id") String productID,
            @RequestParam(name = "author") String productAuthor) {
        alphaController.updateProductAuthor(productID, productAuthor);
    }

    /**
     * Restful API with GET method to view the cart of user.
     * "/cartService/view/{username}"
     *
     * @param username the username of user
     * @return all the product and quantity in the user cart
     */
    @GetMapping("/cartService/view/{username}")
    public Map<Integer, Cart> viewCart(@PathVariable String username) {
        return alphaController.viewCart(username);
    }

    /**
     * Restful API with POST method to add product and quantity to the cart.
     * "/cartService/add" with the request body
     * <p>
     * Example
     * curl -X POST https://localhost:8080/cartService/add
     * -H "Content-Type: application/json"
     * -d '{"cartID":example,"productID":example,"customerName":example,"productName":example,
     * "productPrice":example,"productQuantity":example,"productTotal":example,"sellerName":example}'
     *
     * @param cart the cart item
     */
    @PostMapping("/cartService/add")
    public void addToCart(@RequestBody Map<String, String> cart) {
        int cartID = Integer.valueOf(cart.get("cartID"));
        String productID = cart.get("productID");
        String userName = cart.get("customerName");
        String productName = cart.get("productName");
        float productPrice = Float.parseFloat(cart.get("productPrice"));
        int productQuantity = Integer.parseInt(cart.get("productQuantity"));
        float productTotal = Float.parseFloat(cart.get("productTotal"));
        String sellerName = cart.get("sellerName");
        alphaController.addToCart(cartID, productID, userName, productName, productPrice, productQuantity, productTotal, sellerName);
    }

    /**
     * Restful API with POST method to delete the product and quantity in the cart.
     * "/cartService/delete/{cartID}"
     *
     * @param cartID the cart id
     */
    @PostMapping("/cartService/delete/{cartID}")
    public void deleteItemFromCart(@PathVariable String cartID) {
        alphaController.deleteItemFromCart(cartID);
    }

    /**
     * Restful API with POST method to add an order.
     * "/orderService/add" with the request body
     * <p>
     * Example
     * curl -X POST https://localhost:8080/orderService/add
     * -H "Content-Type: application/json"
     * -d '{"orderID":example,"productID":example,"customerName":example,"productName":example,
     * "productPrice":example,"productQuantity":example,"productTotal":example,"sellerName":example}'
     *
     * @param order the data contains order information
     */
    @PostMapping("/orderService/add")
    public void addOrder(@RequestBody Map<String, String> order) {
        int orderID = Integer.valueOf(order.get("orderID"));
        String productID = order.get("productID");
        String customerName = order.get("customerName");
        String productName = order.get("productName");
        float productPrice = Float.parseFloat(order.get("productPrice"));
        int productQuantity = Integer.parseInt(order.get("productQuantity"));
        float productTotal = Float.parseFloat(order.get("productTotal"));
        String sellerName = order.get("sellerName");
        alphaController.addOrder(orderID, productID, productName, productPrice, productQuantity, productTotal, sellerName, customerName);
    }

    /**
     * Restful API with GET method to view the order by customer.
     * "/orderService/searchName/{username}"
     *
     * @param username the username of customer
     * @return the order which the customer is this user
     */
    @GetMapping("/orderService/searchName/{username}")
    public Map<Integer, Order> getOrderByCustomer(@PathVariable String username) {
        return alphaController.getOrderByCustomer(username);
    }

    /**
     * Restful API with GET method to view orders by seller.
     * "/orderService/searchSeller/{sellerUsername}"
     *
     * @param sellerUsername the username of seller
     * @return the orders which the seller is this user
     */
    @GetMapping("/orderService/searchSeller/{sellerUsername}")
    public Map<Integer, Order> getOrderBySeller(@PathVariable String sellerUsername) {
        return alphaController.getOrderBySeller(sellerUsername);
    }

    /**
     * Restful API with POST method to update the product quantity after order.
     * "/orderService/update/{productID}/{productQuantity}"
     *
     * @param productID       the product id
     * @param productQuantity the product quantity in the order
     */
    @PostMapping("/orderService/update/{productID}/{productQuantity}")
    public void updateProductAfterOrder(@PathVariable String productID, @PathVariable int productQuantity) {
        alphaController.updateProductAfterOrder(productID, productQuantity);
    }

    /**
     * Restful API with POST method to save the system data in the server.
     *
     * @throws IOException if it failed to save the data
     */
    @PostMapping("/save")
    public void saveData() throws IOException {
        alphaController.saveData();
    }

    /**
     * Restful API with POST method to load the system data.
     */
    @PostMapping("/load")
    public void loadData() {
        alphaController.loadData();
    }

}
