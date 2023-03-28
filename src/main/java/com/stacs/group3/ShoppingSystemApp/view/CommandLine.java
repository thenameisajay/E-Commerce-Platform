package com.stacs.group3.ShoppingSystemApp.view;

import org.springframework.web.reactive.function.client.WebClientException;

import java.io.Serializable;
import java.util.*;

public class CommandLine implements Serializable {


    // For testing purpose ONLY.

    APICall apiCall;

    private Scanner scanner;

    private boolean setLoginStatus = false;
    private Map<String, Map<String, String>> adminInfo = new HashMap<>();


    // Saving the data as local cache for the app , for referencing purpose ONLY.
    private Map<String, Map<String, String>> userInfo = new HashMap<>();
    private Map<String, Map<String, String>> sellerInfo = new HashMap<>();
    private Map<String, Map<String, String>> productInfo = new HashMap<>();
    private Map<Integer, Map<String, String>> cartInfo = new HashMap<>();

    public CommandLine(String baseURI) {
        this.apiCall = new APICall(baseURI);
    }

    public void main(String[] args) {
        start();
    }

    public void start() {
        // Generate the admin account if it doesn't exist.
        apiCall.callAdminGenerate();
        messageDisplay();
        String line;
        scanner = new Scanner(System.in);
        try {
            do {
                line = scanner.nextLine().trim();
                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1' -> viewProducts();
                        case '2' -> adminLogin();
                        case '3' -> userLogin();
                        case '4' -> registerUser();
                        case '5' -> {
                            System.out.println("*****************************");
                            System.out.println("*** Saving App Data ***");
                            try {
                                apiCall.callSave();
                                System.out.println("Data saved successfully");
                                start();
                            } catch (Exception e) {
                                System.out.println("Error in saving the file.");
                            }

                        }
                        case '6' -> {
                            System.out.println("*********************");
                            System.out.println("*** Loading the App Data ***");
                            try {
                                apiCall.callLoad();
                                System.out.println("Data loaded successfully");
                                start();
                            } catch (Exception e) {
                                System.out.println("Error in loading the file.");
                                start();
                            }
                        }
                        case '7' -> {
                            System.out.println("**** Exiting the program ****");
                            System.out.println("Thank you for using Alpha System, Goodbye!");
                            System.out.println("*****************************");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid input, please try again");
                    }
                } else {
                    System.out.println("Invalid Action\n");
                    start();
                }
            } while (line.charAt(0) != '7' || line.length() != 1);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Empty input, Exiting the program.");
            System.exit(0);
        }
    }

    private void registerUser() {
        System.out.println("*****************************");
        System.out.println("User Registration Page");
        System.out.println("*****************************");
        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine().trim();
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        System.out.println("Are you registering as a:");
        System.out.println("1. Seller");
        System.out.println("2. Customer");
        String line = scanner.nextLine().trim();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    // Register as a Seller
                    try {
                        apiCall.callAddUser(firstName, lastName, username, email, password, "Seller");
                        System.out.println("Registration Successful");
                        start();
                    } catch (WebClientException e) {
                        System.out.println(e.getMessage() + "\n");
                        start();
                    }
                }

                case '2' -> {
                    // Register as a Customer
                    try {
                        apiCall.callAddUser(firstName, lastName, username, email, password, "Customer");
                        System.out.println("Registration Successful");
                        start();
                    } catch (WebClientException e) {
                        System.out.println(e.getMessage() + "\n");
                        start();
                    }
                }
                default -> System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Invalid Action\n");
            start();
        }

    }

    private void userLogin() {
        System.out.println("*****************************");
        System.out.println("User Login Page");
        System.out.println("*****************************");
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        try {
            Map<String, Map<String, String>> userMap;
            userMap = apiCall.callUserLogin(username, password);
            setLoginStatus = true;

            // TO: Display the appropriate menu based on the account type
            // To check the user type, use the following code:
            if (!userMap.isEmpty()) {
                if (userMap.get(username).get("accountType").equalsIgnoreCase("Seller")) {
                    sellerInfo.putAll(userMap);
                    System.out.println("Login Successful");
                    userMap.clear();
                    sellerMenu();
                } else if (userMap.get(username).get("accountType").equalsIgnoreCase("Customer")) {
                    userInfo.putAll(userMap);
                    System.out.println("Login Successful");
                    userMap.clear();
                    customerMenu();
                } else {
                    System.out.println("Admin cannot login here. Please login as Admin");
                    start();
                }
            } else {
                System.out.println("Please register as a user first");
                start();
            }
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            start();
        }
    }

    private void customerMenu() {
        System.out.println("*****************************");
        System.out.println("Customer Menu");
        System.out.println("*****************************");
        System.out.println("Welcome " + userInfo.get(userInfo.keySet().toArray()[0]).get("firstName") + " " + userInfo.get(userInfo.keySet().toArray()[0]).get("lastName") + "!");
        System.out.println("1. View Products");
        System.out.println("2. Cart Menu and Checkout");
        System.out.println("3. View Orders");
        System.out.println("4. Submit a request to Admin to change your account type");
        System.out.println("5. View my account details");
        System.out.println("6. Delete my account");
        System.out.println("7. Logout");
        System.out.println("*****************************");
        System.out.println("Please enter your choice: ");
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> viewProducts();

                case '2' -> {
                    cartMenu();
                }
                case '3' -> {
                    viewMyOrders();
                }
                case '4' -> submitRequestCustomer();
                case '5' -> viewMyUserDetails();
                case '6' -> deleteMyAccount();
                case '7' -> {
                    System.out.println("Logging out...");
                    setLoginStatus = false;
                    userInfo.clear();
                    start();
                }
                default -> System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Invalid Action\n");
            customerMenu();
        }
    }

    private void cartMenu() {
        System.out.println("*****************************");
        System.out.println("Cart Menu");
        System.out.println("*****************************");
        System.out.println("1. View Cart");
        System.out.println("2. Delete an item from the cart");
        System.out.println("3. Checkout");
        System.out.println("4. Go back to Customer Menu");
        System.out.println("*****************************");
        System.out.println("Please enter your choice: ");
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> viewMyCart();
                case '2' -> deleteItemFromCart();
                case '3' -> checkout();
                case '4' -> customerMenu();
                default -> {
                    System.out.println("Invalid input, please try again");
                    cartMenu();
                }
            }
        } else {
            System.out.println("Invalid Action\n");
            customerMenu();
        }
    }

    private void checkout() {
        System.out.println("*****************************");
        System.out.println("Checkout");
        System.out.println("*****************************");
        String userName = userInfo.get(userInfo.keySet().toArray()[0]).get("username");
        try {
            cartInfo = apiCall.callViewCart(userName);
            if (cartInfo.isEmpty()) {
                throw new IllegalArgumentException("Your cart is empty");
            } else {
                int totalQuantity = 0;
                float totalCost = 0;
                System.out.println("*****************************");
                System.out.println("Your Cart");
                System.out.println("*****************************");
                for (Map.Entry<Integer, Map<String, String>> entry : cartInfo.entrySet()) {
                    System.out.println("*****************************");
                    System.out.println(" Cart ID: " + entry.getKey());
                    System.out.println(" Product ID: " + entry.getValue().get("productId"));
                    System.out.println(" Product Name: " + entry.getValue().get("productName"));
                    System.out.println(" Product Price: " + "£" + entry.getValue().get("productPrice"));
                    System.out.println(" Product Quantity: " + entry.getValue().get("productQuantity"));
                    System.out.println(" Product Total: " + "£" + entry.getValue().get("productTotal"));
                    System.out.println(" Seller Name: " + entry.getValue().get("sellerName"));
                    System.out.println("*****************************");
                    totalQuantity += Integer.valueOf(entry.getValue().get("productQuantity"));
                    totalCost += Float.valueOf(entry.getValue().get("productTotal"));
                }
                System.out.println("Total number of items in your cart: " + totalQuantity);
                System.out.println("Total cost of your cart: " + "£" + totalCost);
                System.out.println("*****************************");
                System.out.println("Do you want to proceed with the checkout? (Y/N)");
                String line = scanner.nextLine();
                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case 'Y' | 'y' -> {
                            orderPlaced();
                            System.out.println("Checkout successful");
                            customerMenu();
                        }
                        case 'N' | 'n' -> {
                            throw new IllegalArgumentException("Checkout cancelled");
                        }
                        default -> {
                            throw new IllegalArgumentException("Invalid input, please try again");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Invalid input, please try again");
                }
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            cartMenu();
        }
    }

    private void orderPlaced() {
        // To be called after checkout
        // Create Random Order ID
        Random rand = new Random();
        int orderID = rand.nextInt(1000000);
        ArrayList<Integer> orderIDList = new ArrayList<>();
        // Store the order ID in an array list and if the order ID is already in the list, generate a new one.
        while (orderIDList.contains(orderID)) {
            orderID = rand.nextInt(1000000);
        }
        String userName = "";
        String productID = "";
        String productName = "";
        float productPrice = 0;
        int productQuantity = 0;
        float productTotal = 0;
        String sellerName = "";
        int cartID = 0;
        for (Map.Entry<Integer, Map<String, String>> entry : cartInfo.entrySet()) {
            cartID = entry.getKey();
            productID = entry.getValue().get("productId");
            productName = entry.getValue().get("productName");
            productPrice = Float.valueOf(entry.getValue().get("productPrice"));
            productQuantity = Integer.valueOf(entry.getValue().get("productQuantity"));
            productTotal = Float.valueOf(entry.getValue().get("productTotal"));
            sellerName = entry.getValue().get("sellerName");
            userName = entry.getValue().get("customerName");
            try {
                orderID++;
                apiCall.callAddOrder(orderID, productID, productName, productPrice, productQuantity, productTotal, sellerName, userName);
                apiCall.callUpdateProductAfterOrder(productID, productQuantity);
                apiCall.callDeleteItemFromCart(String.valueOf(cartID));
            } catch (WebClientException e) {
                System.out.println(e.getMessage() + "\n");
                cartMenu();
            }
        }
        System.out.println("Order Placed Successfully!");
        // Clear cache data of cart
        cartInfo.clear();
        // Clear cache data of product
        productInfo.clear();
        customerMenu();
    }

    private void deleteItemFromCart() {

        System.out.println("*****************************");
        System.out.println("Delete an item from the cart");
        System.out.println("*****************************");
        System.out.println("Please enter the cart ID (can be seen from View cart) of the item you want to delete: ");
        String cartID = scanner.nextLine().trim();
        try {
            apiCall.callDeleteItemFromCart(cartID);
            System.out.println("Item deleted from cart");
            cartMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            cartMenu();
        }
    }

    private void viewOrders() {
        String sellerUsername = "";
        Map<Integer, Map<String, String>> orderInfo = new HashMap<>();
        System.out.println("*****************************");
        System.out.println("Orders Placed by Customer");
        System.out.println("*****************************");
        for (Map.Entry<String, Map<String, String>> entry : sellerInfo.entrySet()) {
            sellerUsername = entry.getKey();
        }
        try {
            orderInfo = apiCall.callGetOrderBySeller(sellerUsername);
            if (orderInfo.isEmpty()) {
                throw new IllegalArgumentException("No orders placed by customers");
            }
            for (Map.Entry<Integer, Map<String, String>> entry : orderInfo.entrySet()) {
                System.out.println("*****************************");
                System.out.println(" Customer Name: " + entry.getValue().get("customerName"));
                System.out.println(" Order ID: " + entry.getKey());
                System.out.println(" Product ID: " + entry.getValue().get("productID"));
                System.out.println(" Product Name: " + entry.getValue().get("productName"));
                System.out.println(" Product Quantity: " + entry.getValue().get("productQuantity"));
                System.out.println(" Product Total: " + "£" + entry.getValue().get("productTotal"));
                System.out.println("*****************************");
            }
            sellerMenu();
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }


    private void submitRequestCustomer() {
        String customerUsername = "";
        for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
            customerUsername = entry.getKey();
        }
        System.out.println("*****************************");
        System.out.println("Submit Request");
        System.out.println("*****************************");
        System.out.println("Please select the type of request you want to submit:");
        System.out.println("1. Change my account type to Seller");
        System.out.println("2. Change my account type to Admin");
        System.out.println("3. Go back to Customer Menu");
        System.out.println("Please enter your choice: ");
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    try {
                        apiCall.callStoreRequest(customerUsername, "seller");
                        System.out.println("Request submitted successfully.");
                        customerMenu();
                    } catch (WebClientException e) {
                        System.out.println(e.getMessage() + "\n");
                        customerMenu();
                    }
                }
                case '2' -> {
                    try {
                        apiCall.callStoreRequest(customerUsername, "admin");
                        System.out.println("Request submitted successfully.");
                        customerMenu();
                    } catch (WebClientException e) {
                        System.out.println(e.getMessage() + "\n");
                        customerMenu();
                    }
                }
                case '3' -> customerMenu();
                default -> System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Invalid Action\n");
            customerMenu();
        }
    }

    private void submitRequestSeller() {
        String sellerUsername = "";
        for (Map.Entry<String, Map<String, String>> entry : sellerInfo.entrySet()) {
            sellerUsername = entry.getKey();
        }
        System.out.println("*****************************");
        System.out.println("Submit Request");
        System.out.println("*****************************");
        System.out.println("Please select the type of request you want to submit:");
        System.out.println("1. Change my account type to Customer");
        System.out.println("2. Change my account type to Admin");
        System.out.println("3. Go back to Seller Menu");
        System.out.println("Please enter your choice: ");
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    try {
                        apiCall.callStoreRequest(sellerUsername, "customer");
                        System.out.println("Request submitted successfully.");
                        sellerMenu();
                    } catch (WebClientException e) {
                        System.out.println(e.getMessage() + "\n");
                        sellerMenu();
                    }
                }
                case '2' -> {
                    try {
                        apiCall.callStoreRequest(sellerUsername, "admin");
                        System.out.println("Request submitted successfully.");
                        sellerMenu();
                    } catch (WebClientException e) {
                        System.out.println(e.getMessage() + "\n");
                        sellerMenu();
                    }
                }
                case '3' -> sellerMenu();
                default -> System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Invalid Action\n");
            sellerMenu();
        }
    }

    private void sellerMenu() {
        System.out.println("*****************************");
        System.out.println("Seller Menu");
        System.out.println("*****************************");
        System.out.println("Welcome " + sellerInfo.get(sellerInfo.keySet().toArray()[0]).get("firstName") + " " + sellerInfo.get(sellerInfo.keySet().toArray()[0]).get("lastName") + "!");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View your Products");
        System.out.println("5. View Customer Orders");
        System.out.println("6. Submit a request to Admin to change your account");
        System.out.println("7. View my account details");
        System.out.println("8. Delete my account");
        System.out.println("9. Logout");
        System.out.println("Please enter your choice:");
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> addProduct();
                case '2' -> updateProduct();
                case '3' -> deleteProduct();
                case '4' -> viewMyProducts();
                case '5' -> viewOrders();
                case '6' -> submitRequestSeller();
                case '7' -> viewMySellerDetails();
                case '8' -> deleteMyAccount();
                case '9' -> {
                    System.out.println("Logging out...");
                    setLoginStatus = false;
                    sellerInfo.clear();
                    start();
                }
                default -> System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Invalid Action\n");
            sellerMenu();
        }
    }

    private void viewMyOrders() {
        String username = "";
        for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
            username = entry.getKey();
        }
        try {
            Map<Integer, Map<String, String>> orders = apiCall.callGetOrderByCustomer(username);
            if (orders.isEmpty()) {
                throw new IllegalArgumentException("You have not placed any orders yet.");
            } else {
                float totalCost = 0;
                int totalQuantity = 0;
                for (Map.Entry<Integer, Map<String, String>> entry : orders.entrySet()) {
                    System.out.println("*****************************");
                    System.out.println("Order ID: " + entry.getKey());
                    System.out.println("Product Name: " + entry.getValue().get("productName"));
                    System.out.println("Product Price: " + "£" + entry.getValue().get("productPrice"));
                    System.out.println("Product Quantity: " + entry.getValue().get("productQuantity"));
                    System.out.println("Product Total Price: " + "£" + entry.getValue().get("productTotal"));
                    totalCost += Float.valueOf(entry.getValue().get("productTotal"));
                    totalQuantity += Integer.valueOf(entry.getValue().get("productQuantity"));
                    System.out.println("*****************************");
                }
                System.out.println("Total Quantity: " + totalQuantity);
                System.out.println("Total Cost: " + "£" + totalCost);
                System.out.println("*****************************");
                orders.clear();
                customerMenu();
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            customerMenu();
        }

    }

    private void viewMyProducts() {
        System.out.println("*****************************");
        System.out.println("View My Products");
        System.out.println("*****************************");
        String sellerUsername = "";
        for (Map.Entry<String, Map<String, String>> entry : sellerInfo.entrySet()) {
            sellerUsername = entry.getKey();
        }
        try {
            Map<String, Map<String, String>> sellerProducts = apiCall.callViewSellerProducts(sellerUsername);
            if (sellerProducts.isEmpty()) {
                throw new IllegalArgumentException("You have not added any products yet.");
            } else {
                for (Map.Entry<String, Map<String, String>> entry : sellerProducts.entrySet()) {
                    System.out.println("*****************************");
                    System.out.println("Product ID: " + entry.getKey());
                    System.out.println("Product Name: " + entry.getValue().get("productName"));
                    System.out.println("Product Price: " + entry.getValue().get("productPrice"));
                    System.out.println("Product Quantity: " + entry.getValue().get("productQuantity"));
                    System.out.println("Product Description: " + entry.getValue().get("productDescription"));
                    System.out.println("Product Category: " + entry.getValue().get("productCategory"));
                    System.out.println("Product Author: " + entry.getValue().get("author"));
                    System.out.println("*****************************");
                }
                System.out.println("Search has returned " + sellerProducts.size() + " results.");
                sellerMenu();
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void deleteProduct() {

        System.out.println("*****************************");
        System.out.println("Delete a Product");
        System.out.println("*****************************");
        System.out.println("Please enter the Product ID:");
        String productID = scanner.nextLine().trim();
        System.out.println("Are you sure you want to delete this product? (Y/N)");
        String line = scanner.nextLine().trim();
        String sellerUsername = "";
        for (Map.Entry<String, Map<String, String>> entry : sellerInfo.entrySet()) {
            if (entry.getValue().get("username").equals(sellerUsername)) {
                sellerUsername = entry.getKey();
            }
        }
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case 'Y' | 'y' -> {
                    try {
                        apiCall.callDeleteProduct(productID, sellerUsername);
                        System.out.println("Product deleted successfully.");
                        sellerMenu();
                    } catch (WebClientException e) {
                        System.out.println(e.getMessage() + "\n");
                        sellerMenu();
                    }
                }
                case 'N' | 'n' -> sellerMenu();
                default -> System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Invalid Action\n");
            sellerMenu();
        }
    }

    private void updateProduct() {
        String sellerUsername = "";
        for (Map.Entry<String, Map<String, String>> entry : sellerInfo.entrySet()) {
            sellerUsername = entry.getKey();
        }
        System.out.println("*****************************");
        System.out.println("Update a Product");
        System.out.println("*****************************");
        System.out.println("Please enter the Product ID:");
        String productID = scanner.nextLine().trim();
        try {
            if (apiCall.callCheckValidationToUpdate(sellerUsername, productID)) {
                System.out.println("*************************");
                System.out.println("Which field would you like to update?");
                System.out.println("Note : To update the Product-ID , Delete the product and add it again.");
                System.out.println("1. Product Name");
                System.out.println("2. Product Description");
                System.out.println("3. Product Price");
                System.out.println("4. Product Quantity");
                System.out.println("5. Product Category");
                System.out.println("6. Product Author");
                System.out.println("7. Exit");
                System.out.println("*************************");
                String line = scanner.nextLine();
                if (line.length() == 1) {
                    switch (line) {
                        case "1" -> updateProductName(productID);
                        case "2" -> updateProductDescription(productID);
                        case "3" -> updateProductPrice(productID);
                        case "4" -> updateProductQuantity(productID);
                        case "5" -> updateProductCategory(productID);
                        case "6" -> updateProductAuthor(productID);
                        case "7" -> sellerMenu();
                        default -> System.out.println("Invalid input, please try again");
                    }
                } else {
                    System.out.println("Invalid Action\n");
                    sellerMenu();
                }

            }
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }

    }

    private void updateProductAuthor(String productID) {
        System.out.println("*****************************");
        System.out.println("Update Product Author");
        System.out.println("*****************************");
        System.out.println("Please enter the new Product Author:");
        String productAuthor = scanner.nextLine().trim();
        try {
            apiCall.callUpdateProductAuthor(productID, productAuthor);
            System.out.println("Product Author updated successfully.");
            sellerMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void updateProductCategory(String productID) {
        System.out.println("*****************************");
        System.out.println("Update Product Category");
        System.out.println("*****************************");
        System.out.println("Please enter the new Product Category:");
        String productCategory = scanner.nextLine().trim();
        try {
            apiCall.callUpdateProductCategory(productID, productCategory);
            System.out.println("Product Category updated successfully.");
            sellerMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void updateProductQuantity(String productID) {
        System.out.println("*****************************");
        System.out.println("Update Product Quantity");
        System.out.println("*****************************");
        System.out.println("Please enter the new Product Quantity:");
        String productQuantity = scanner.nextLine().trim();
        try {
            apiCall.callUpdateProductQuantity(productID, productQuantity);
            System.out.println("Product Quantity updated successfully.");
            sellerMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void updateProductPrice(String productID) {
        System.out.println("*****************************");
        System.out.println("Update Product Price");
        System.out.println("*****************************");
        System.out.println("Please enter the new Product Price:");
        String productPrice = scanner.nextLine().trim();
        try {
            apiCall.callUpdateProductPrice(productID, productPrice);
            System.out.println("Product Price updated successfully.");
            sellerMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void updateProductDescription(String productID) {
        System.out.println("*****************************");
        System.out.println("Update Product Description");
        System.out.println("*****************************");
        System.out.println("Please enter the new Product Description:");
        String productDescription = scanner.nextLine().trim();
        try {
            apiCall.callUpdateProductDescription(productID, productDescription);
            System.out.println("Product Description updated successfully.");
            sellerMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void updateProductName(String productID) {
        System.out.println("*****************************");
        System.out.println("Update Product Name");
        System.out.println("*****************************");
        System.out.println("Please enter the new Product Name:");
        String productName = scanner.nextLine().trim();
        try {
            apiCall.callUpdateProductName(productID, productName);
            System.out.println("Product Name updated successfully.");
            sellerMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void addProduct() {
        System.out.println("*****************************");
        System.out.println("Add Product");
        System.out.println("*****************************");
        System.out.println("Please enter the Product ID:");
        String productID = scanner.nextLine().trim();
        System.out.println("Please enter the Product Name:");
        String productName = scanner.nextLine().trim();
        System.out.println("Please enter the Product Description:");
        String productDescription = scanner.nextLine().trim();
        System.out.println("Please enter the author:");
        String author = scanner.nextLine().trim();
        System.out.println("Please enter the Product Price:");
        String productPrice = scanner.nextLine().trim();
        System.out.println("Please enter the Product Quantity:");
        String productQuantity = scanner.nextLine().trim();
        System.out.println("Please enter the Product Category:");
        String productCategory = scanner.nextLine().trim();
        String sellerUsername = "";
        for (Map.Entry<String, Map<String ,String>> entry : sellerInfo.entrySet()) {
            sellerUsername = entry.getKey().trim();
        }
        try {
            apiCall.callAddProduct(productID, productName, productDescription, author, productPrice, productQuantity, productCategory, sellerUsername);
            System.out.println("Product added successfully.");
            sellerMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            sellerMenu();
        }
    }

    private void viewProducts() {
        if (!setLoginStatus) {
            System.out.println("*****************************");
            System.out.println("You are not logged in. You can view Products but cannot add to cart.");
            System.out.println("To buy products, please login first or create a user account");
            System.out.println("*****************************");
        }
        System.out.println("*****************************");
        System.out.println("Viewing Products");
        System.out.println("*****************************");
        System.out.println("1. Search by name");
        System.out.println("2. Search by category");
        System.out.println("3. Search by author");
        System.out.println("4. View All Products");
        System.out.println("5. Main Menu");
        System.out.println("Please enter your choice:");
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    searchProductByName();
                }
                case '2' -> {

                    searchProductByCategory();
                }
                case '3' -> {
                    searchProductByAuthor();
                }
                case '4' -> {
                    viewAllProducts();
                }
                case '5' -> {
                    start();
                }
                default -> {
                    System.out.println("Invalid input, please try again");
                    viewProducts();
                }
            }
        } else {
            System.out.println("Invalid Action\n");
            viewProducts();
        }
    }

    private void viewAllProducts() {
        System.out.println("*****************************");
        System.out.println("Viewing the Entire Collection..");
        System.out.println("*****************************");
        try {
            productInfo = apiCall.callViewAllProducts();
            for (Map.Entry<String, Map<String, String>> Entry : productInfo.entrySet()) {
                System.out.println("*****************************");
                System.out.println(" Product ID: " + Entry.getKey());
                System.out.println(" The Product:" + Entry.getValue().get("productName"));
                System.out.println(" The Product Description:" + Entry.getValue().get("productDescription"));
                System.out.println(" The Product Price:" + "£" + Entry.getValue().get("productPrice"));
                System.out.println(" The Product Quantity:" + Entry.getValue().get("productQuantity"));
                System.out.println(" The Product Category:" + Entry.getValue().get("productCategory"));
                System.out.println(" The Product Author:" + Entry.getValue().get("author"));
                System.out.println(" The Product Seller:" + Entry.getValue().get("sellerUsername"));
                System.out.println("*****************************");
            }
            if (setLoginStatus) {
                String accountType = "";
                for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
                    accountType = entry.getValue().get("accountType");
                }
                if (accountType.equalsIgnoreCase("customer")) {
                    System.out.println("1. Enter the Product ID of the product you want to buy");
                    System.out.println("2. View My Cart");
                    System.out.println("3. Go back to Menu");
                    System.out.println("Please enter your choice:");
                    String option = scanner.nextLine().trim();
                    if (option.length() == 1) {
                        switch (option) {
                            case "1" -> {
                                addToCart();

                            }
                            case "2" -> {
                                viewMyCart();

                            }
                            case "3" -> customerMenu();

                            default -> {
                                System.out.println("Invalid option");
                                customerMenu();
                            }
                        }

                    } else {
                        System.out.println("Invalid option");
                        customerMenu();
                    }
                } else {
                    start();
                }
            } else {
                start();
            }
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            if (setLoginStatus) customerMenu();
            else start();
        }

    }

    private void searchProductByAuthor() {
        System.out.println("*****************************");
        System.out.println("Search Product by Author");
        System.out.println("*****************************");
        System.out.println("Please enter the Author's Name:");
        String productAuthor = scanner.nextLine().trim();
        try {
            productInfo = apiCall.callSearchProductByAuthor(productAuthor);
            if (productInfo.isEmpty()) {
                throw new IllegalArgumentException("No Product found with the author " + productAuthor);
            } else {
                for (Map.Entry<String, Map<String, String>> Entry : productInfo.entrySet()) {
                    System.out.println("*****************************");
                    System.out.println(" Product ID: " + Entry.getKey());
                    System.out.println(" The Product:" + Entry.getValue().get("productName"));
                    System.out.println(" The Product Description:" + Entry.getValue().get("productDescription"));
                    System.out.println(" The Product Price:" + "£" + Entry.getValue().get("productPrice"));
                    System.out.println(" The Product Quantity:" + Entry.getValue().get("productQuantity"));
                    System.out.println(" The Product Category:" + Entry.getValue().get("productCategory"));
                    System.out.println(" The Product Seller:" + Entry.getValue().get("sellerUsername"));
                    System.out.println("*****************************");
                }
                System.out.println("Search has returned " + productInfo.size() + " results");
                if (setLoginStatus) {
                    String accountType = "";
                    for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
                        accountType = entry.getValue().get("accountType");
                    }
                    if (accountType.equalsIgnoreCase("customer")) {
                        System.out.println("1. Enter the Product ID of the product you want to buy");
                        System.out.println("2. View My Cart");
                        System.out.println("3. Go back to Main Menu");
                        System.out.println("Please enter your choice:");
                        String option = scanner.nextLine().trim();
                        if (option.length() == 1) {
                            switch (option) {
                                case "1" -> {
                                    addToCart();
                                }
                                case "2" -> {
                                    viewMyCart();
                                }
                                case "3" -> customerMenu();

                                default -> {
                                    System.out.println("Invalid option");
                                    customerMenu();
                                }
                            }

                        } else {
                            System.out.println("Invalid option");
                            customerMenu();
                        }
                    } else {
                        start();
                    }
                } else {
                    start();
                }
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            if (setLoginStatus) customerMenu();
            else start();
        }
    }

    private void searchProductByCategory() {
        System.out.println("*****************************");
        System.out.println("Search Product by Category");
        System.out.println("*****************************");
        System.out.println("Please enter the Product Category:");
        String productCategory = scanner.nextLine().trim();
        try {
            productInfo = apiCall.callSearchProductByCategory(productCategory);
            if (productInfo.isEmpty()) {
                throw new IllegalArgumentException("No Product found with the category " + productCategory);
            }
            for (Map.Entry<String, Map<String, String>> entry : productInfo.entrySet()) {
                System.out.println("*****************************");
                System.out.println(" Product ID: " + entry.getKey());
                System.out.println(" The Product:" + entry.getValue().get("productName"));
                System.out.println(" Category: " + entry.getValue().get("productCategory"));
                System.out.println(" Price: " + "£" + entry.getValue().get("productPrice"));
                System.out.println(" Quantity: " + entry.getValue().get("productQuantity"));
                System.out.println(" Seller: " + entry.getValue().get("sellerUsername"));
                System.out.println("*****************************");
            }
            System.out.println("Search has returned " + productInfo.size() + " results\n");
            if (setLoginStatus) {
                String accountType = "";
                for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
                    accountType = entry.getValue().get("accountType");
                }
                if (accountType.equalsIgnoreCase("customer")) {
                    System.out.println("1. Enter the Product ID of the product you want to buy");
                    System.out.println("2. View My Cart");
                    System.out.println("3. Go back to Menu");
                    System.out.println("Please enter your choice:");
                    String option = scanner.nextLine().trim();
                    if (option.length() == 1) {
                        switch (option) {
                            case "1" -> {
                                addToCart();

                            }
                            case "2" -> {
                                viewMyCart();

                            }
                            case "3" -> customerMenu();

                            default -> {
                                System.out.println("Invalid option");
                                customerMenu();
                            }
                        }

                    } else {
                        System.out.println("Invalid option");
                        customerMenu();
                    }
                } else {
                    start();
                }
            } else {
                start();
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            if (setLoginStatus) customerMenu();
            else start();
        }
    }

    private void searchProductByName() {
        System.out.println("*****************************");
        System.out.println("Search Product by Name");
        System.out.println("*****************************");
        System.out.println("Please enter the Product Name:");
        String productName = scanner.nextLine().trim();
        String quantity = String.valueOf(0);
        try {
            productInfo = apiCall.callSearchProductByName(productName);
            if (productInfo.isEmpty()) {
                throw new IllegalArgumentException("No Product found with the name " + productName);
            }
            for (Map.Entry<String, Map<String, String>> entry : productInfo.entrySet()) {
                System.out.println("*****************************");
                System.out.println(" Product ID: " + entry.getKey());
                System.out.println(" The Product:" + entry.getValue().get("productName"));
                System.out.println(" Category: " + entry.getValue().get("productCategory"));
                System.out.println(" Price: " + "£" + entry.getValue().get("productPrice"));
                System.out.println(" Quantity: " + entry.getValue().get("productQuantity"));
                System.out.println(" Description: " + entry.getValue().get("productDescription"));
                System.out.println(" Author: " + entry.getValue().get("author"));
                System.out.println(" Seller: " + entry.getValue().get("sellerUsername"));
                System.out.println("*****************************");
            }
            System.out.println("Search returned with " + productInfo.size() + " results");
            if (setLoginStatus) {
                String accountType = "";
                for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
                    accountType = entry.getValue().get("accountType");
                }
                if (accountType.equalsIgnoreCase("customer")) {
                    System.out.println("1. Enter the Product ID of the product you want to buy");
                    System.out.println("2. View My Cart");
                    System.out.println("3. Go back to Menu");
                    System.out.println("Please enter your choice:");
                    String option = scanner.nextLine().trim();
                    if (option.length() == 1) {
                        switch (option) {
                            case "1" -> {
                                addToCart();

                            }
                            case "2" -> {
                                viewMyCart();

                            }
                            case "3" -> customerMenu();

                            default -> {
                                System.out.println("Invalid option");
                                customerMenu();
                            }
                        }

                    } else {
                        System.out.println("Invalid option");
                        customerMenu();
                    }

                } else {
                    start();
                }
            } else {
                start();
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            if (setLoginStatus) {
                customerMenu();
            } else {
                start();
            }
        }

    }


    private void addToCart() {
        // Generate a random number for the cart ID
        Random random = new Random();
        int cartID = random.nextInt(1000000);
        ArrayList<Integer> cartIDList = new ArrayList<>();
        // Store the cart ID in an array list and if the cart ID is already in the list, generate a new one.
        while (cartIDList.contains(cartID)) {
            cartID = random.nextInt(1000000);
        }
        cartIDList.add(cartID);
        String quantity;
        System.out.println("Please enter the product ID you want to add to cart: ");
        String productID = scanner.nextLine().trim();
        try {
            // To check if product ID is valid
            if (!productInfo.containsKey(productID)) {
                throw new IllegalArgumentException("Invalid Product ID");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
            searchProductByName();
        }
        String userName = "";
        System.out.println("How much quantity do you want to add to cart?");
        quantity = scanner.nextLine().trim();
        for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
            userName = entry.getValue().get("username");
        }
        try {
            String productName = "";
            float productPrice = 0;
            float productTotal;
            int productQuantity = 0;
            String sellerName = "";

            for (Map.Entry<String, Map<String, String>> entry : productInfo.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(productID)) {
                    productName = entry.getValue().get("productName");
                    productPrice = Float.parseFloat(entry.getValue().get("productPrice"));
                    sellerName = entry.getValue().get("sellerUsername");
                    productQuantity = Integer.parseInt(entry.getValue().get("productQuantity"));
                }
            }
            if (productQuantity < Integer.parseInt(quantity)) {
                throw new IllegalArgumentException("Sorry, we don't have enough quantity of this product");
            }
            productTotal = productPrice * Integer.parseInt(quantity);
            cartID++;

            apiCall.callAddToCart(cartID, productID, userName, productName, productPrice, Integer.parseInt(quantity), productTotal, sellerName);
            System.out.println("Product added to cart successfully");
            customerMenu();
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            customerMenu();
        }
    }

    private void viewMyCart() {
        String userName = "";
        for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
            userName = entry.getValue().get("username");
        }
        System.out.println("*****************************");
        System.out.println("Viewing Your Cart");
        System.out.println("*****************************");
        try {
            cartInfo = apiCall.callViewCart(userName);
            if (cartInfo.isEmpty()) {
                throw new IllegalArgumentException("Your cart is empty");
            } else {
                int totalQuantity = 0;
                float totalCost = 0;
                System.out.println("*****************************");
                System.out.println("Your Cart");
                System.out.println("*****************************");
                for (Map.Entry<Integer, Map<String, String>> entry : cartInfo.entrySet()) {
                    System.out.println("*****************************");
                    System.out.println(" Cart ID: " + entry.getKey());
                    System.out.println(" Product ID: " + entry.getValue().get("productId"));
                    System.out.println(" Product Name: " + entry.getValue().get("productName"));
                    System.out.println(" Product Price: " + "£" + entry.getValue().get("productPrice"));
                    System.out.println(" Product Quantity: " + entry.getValue().get("productQuantity"));
                    System.out.println(" Product Total: " + "£" + entry.getValue().get("productTotal"));
                    System.out.println(" Seller Name: " + entry.getValue().get("sellerName"));
                    System.out.println("*****************************");
                    totalQuantity += Integer.valueOf(entry.getValue().get("productQuantity"));
                    totalCost += Float.valueOf(entry.getValue().get("productTotal"));
                }
                System.out.println("Total number of items in your cart: " + totalQuantity);
                System.out.println("Total cost of your cart: " + "£" + totalCost);
                System.out.println("*****************************");
                cartMenu();
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            customerMenu();
        }
    }

    private void adminLogin() {
        System.out.println("*****************************");
        System.out.println("Admin Login Page");
        System.out.println("*****************************");
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        Map<String, Map<String, String>> adminMap;

        try {
            adminMap = apiCall.callAdminLogin(username, password);
            adminInfo.putAll(adminMap);
            setLoginStatus = true;
            System.out.println("Login Successful");
            adminMap.clear();
            adminMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage() + "\n");
            start();
        }
    }

    private void adminMenu() {
        System.out.println("*****************************");
        System.out.println("Admin Menu");
        System.out.println("*****************************");
        for (String s : Arrays.asList("Welcome " + adminInfo.get("admin").get("username"), "1. View All Users", "2. Add a new User", "3. Delete a User", "4. Update permission of User", "5. View my Account Details", "6. Delete my Account", "7. Logout", "*****************************", "Please enter your choice: ")) {
            System.out.println(s);
        }
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> viewAllUsers();
                case '2' -> addUserViaAdmin();
                case '3' -> deleteUser();
                case '4' -> updateUserPermission();
                case '5' -> viewMyAccountDetails();
                case '6' -> deleteMyAccount();
                case '7' -> {
                    System.out.println("Logging out");
                    setLoginStatus = false;
                    adminInfo.clear();
                    start();
                }
                default -> System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Invalid Action\n");
            adminMenu();
        }
    }

    private void deleteMyAccount() {
        // This method can be used by both admin, customer and seller.
        System.out.println("*****************************");
        System.out.println("Delete Your Account");
        System.out.println("*****************************");
        System.out.println("Enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine().trim();
        System.out.println("Are you sure you want to delete your account? (Y/N)");
        String answer = scanner.nextLine().trim();
        if (answer.equalsIgnoreCase("Y")) {
            try {
                apiCall.callDeleteSelfAccount(username, password);
                System.out.println("Your account has been deleted");
                adminInfo.clear();
                setLoginStatus = false;
                start();
            } catch (WebClientException e) {
                System.out.println(e.getMessage());
                adminMenu();
            }
        } else if (answer.equalsIgnoreCase("N")) {
            adminMenu();
        } else {
            System.out.println("Invalid input, please try again");
            adminMenu();
        }
    }

    /**
     * This method is used to view account details of the admin who is logged in.
     */
    private void viewMyAccountDetails() {
        System.out.println("*****************************");
        System.out.println("Viewing my Account Details");
        System.out.println("*****************************");
        try {
            if (adminInfo.isEmpty()) {
                throw new IllegalArgumentException("No admin is logged in");
            } else {
                for (Map.Entry<String, Map<String, String>> entry : adminInfo.entrySet()) {
                    System.out.println("Username:" + entry.getKey());
                    System.out.println("First Name:" + entry.getValue().get("firstName"));
                    System.out.println("Last Name:" + entry.getValue().get("lastName"));
                    System.out.println("Email:" + entry.getValue().get("email"));
                    System.out.println("Account Type:" + entry.getValue().get("accountType"));
                    System.out.println("*****************************");
                    adminMenu();
                }
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage());
            adminMenu();
        }


    }

    /**
     * This method is used for viewing account info of customers.
     */
    private void viewMyUserDetails() {
        // This method can be used now only by customer.
        System.out.println("*****************************");
        System.out.println("Viewing my Account Details");
        System.out.println("*****************************");
        String accountType = "";

        try {
            if (userInfo.isEmpty()) {
                throw new IllegalArgumentException("No user is logged in");
            } else {
                for (Map.Entry<String, Map<String, String>> entry : userInfo.entrySet()) {
                    System.out.println("Username:" + entry.getKey());
                    System.out.println("First Name:" + entry.getValue().get("firstName"));
                    System.out.println("Last Name:" + entry.getValue().get("lastName"));
                    System.out.println("Email:" + entry.getValue().get("email"));
                    System.out.println("Account Type:" + entry.getValue().get("accountType"));
                    System.out.println("*****************************");
                    accountType = entry.getValue().get("accountType");
                }
                if (accountType.equalsIgnoreCase("customer")) {
                    customerMenu();
                } else if (accountType.equalsIgnoreCase("seller")) {
                    sellerMenu();
                }
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage());
            if (accountType.equalsIgnoreCase("customer")) {
                customerMenu();
            } else if (accountType.equalsIgnoreCase("seller")) {
                sellerMenu();
            }
        }

    }

    private void viewMySellerDetails() {
        // This method can be used now only by customer.
        System.out.println("*****************************");
        System.out.println("Viewing my Account Details");
        System.out.println("*****************************");
        String accountType = "";
        try {
            if (sellerInfo.isEmpty()) {
                throw new IllegalArgumentException("No user is logged in");
            } else {
                for (Map.Entry<String, Map<String, String>> entry : sellerInfo.entrySet()) {
                    System.out.println("Username:" + entry.getKey());
                    System.out.println("First Name:" + entry.getValue().get("firstName"));
                    System.out.println("Last Name:" + entry.getValue().get("lastName"));
                    System.out.println("Email:" + entry.getValue().get("email"));
                    System.out.println("Account Type:" + entry.getValue().get("accountType"));
                    System.out.println("*****************************");
                    accountType = entry.getValue().get("accountType");
                }
                if (accountType.equalsIgnoreCase("customer")) {
                    customerMenu();
                } else if (accountType.equalsIgnoreCase("seller")) {
                    sellerMenu();
                }
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage());
            if (accountType.equalsIgnoreCase("customer")) {
                customerMenu();
            } else if (accountType.equalsIgnoreCase("seller")) {
                sellerMenu();
            }
        }


    }

    private void viewAllUsers() {
        System.out.println("*****************************");
        System.out.println("Viewing All Users");
        System.out.println("*****************************");
        try {
            Map<String, Map<String, String>> allUsers = apiCall.callViewAllUsers();
            for (Map.Entry<String, Map<String, String>> entry : allUsers.entrySet()) {
                System.out.println("Username:" + entry.getKey());
                System.out.println("First Name:" + entry.getValue().get("firstName"));
                System.out.println("Last Name:" + entry.getValue().get("lastName"));
                System.out.println("Email:" + entry.getValue().get("email"));
                System.out.println("Account Type:" + entry.getValue().get("accountType"));
                System.out.println("*****************************");
            }
            System.out.println("All users are displayed above");
            adminMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void addUserViaAdmin() {
        System.out.println("*****************************");
        System.out.println("Adding a new User");
        System.out.println("*****************************");
        System.out.println("Please enter the first name of the new user: ");
        String firstName = scanner.nextLine().trim();
        System.out.println("Please enter the last name of the new user: ");
        String lastName = scanner.nextLine().trim();
        System.out.println("Please enter the username of the new user: ");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter the email of the new user: ");
        String email = scanner.nextLine().trim();
        System.out.println("Please enter the password of the new user: ");
        String password = scanner.nextLine().trim();
        System.out.println("Please enter the account type of the new user: ");
        System.out.println("1. Admin");
        System.out.println("2. Seller");
        System.out.println("3. Customer");
        String accountType = scanner.nextLine().trim();
        try {
            switch (accountType) {
                case "1" -> {
                    apiCall.callAddUser(firstName, lastName, username, email, password, "admin");
                    System.out.println("Admin added successfully");
                    adminMenu();
                }
                case "2" -> {
                    apiCall.callAddUser(firstName, lastName, username, email, password, "seller");
                    System.out.println("Seller Account added successfully");
                    adminMenu();
                }
                case "3" -> {
                    apiCall.callAddUser(firstName, lastName, username, email, password, "customer");
                    System.out.println("Customer Account added successfully");
                    adminMenu();
                }
                default -> throw new IllegalArgumentException("Invalid input, please try again");
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void updateUserPermission() {
        System.out.println("*****************************");
        System.out.println("Updating User Permission");
        System.out.println("*****************************");
        System.out.println("1. View Requests");
        System.out.println("2. Update User Permission");
        String choice = scanner.nextLine().trim();
        try {
            switch (choice) {
                case "1" -> viewRequests();
                case "2" -> updatePermission();
                default -> throw new IllegalArgumentException("Invalid input, please try again");
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void updatePermission() {
        System.out.println("*****************************");
        System.out.println("Updating User Permission");
        System.out.println("*****************************");
        System.out.println("Please enter the username of the user you want to update: ");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter the new permission of the user: ");
        System.out.println("1. Admin");
        System.out.println("2. Seller");
        System.out.println("3. Customer");
        String accountType = scanner.nextLine().trim();
        try {
            switch (accountType) {
                case "1" -> {
                    apiCall.callUpdateUserPermission(username, "admin");
                    System.out.println("User permission updated successfully");
                    adminMenu();
                }
                case "2" -> {
                    apiCall.callUpdateUserPermission(username, "seller");
                    System.out.println("User permission updated successfully");
                    adminMenu();
                }
                case "3" -> {
                    apiCall.callUpdateUserPermission(username, "customer");
                    System.out.println("User permission updated successfully");
                    adminMenu();
                }
                default -> throw new IllegalArgumentException("Invalid input, please try again");
            }
        } catch (IllegalArgumentException | WebClientException e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    private void viewRequests() {
        Map<String, Map<String, String>> allRequests;
        System.out.println("*****************************");
        System.out.println("Viewing Requests");
        System.out.println("*****************************");
        try {
            allRequests = apiCall.callViewRequests();
            if (allRequests.isEmpty()) {
                System.out.println("There are no requests at the moment.");
                adminMenu();
            } else {
                for (Map.Entry<String, Map<String, String>> entry : allRequests.entrySet()) {
                    System.out.println("Username:" + entry.getKey());
                    System.out.println("Request Type:" + entry.getValue().get("requestType"));
                    System.out.println("*****************************");
                }
                System.out.println("All users are displayed above");
                allRequests.clear();
                adminMenu();
            }
        } catch (WebClientException e) {
            System.out.println(e.getMessage());
            adminMenu();

        }

    }

    private void deleteUser() {
        System.out.println("*****************************");
        System.out.println("Deleting a User");
        System.out.println("*****************************");
        System.out.println("Please enter the username of the user you want to delete: ");
        String username = scanner.nextLine().trim();
        try {
            apiCall.callDeleteUserViaAdmin(username);
            System.out.println("User deleted successfully");
            adminMenu();
        } catch (WebClientException e) {
            System.out.println(e.getMessage());
            adminMenu();
        }
    }

    public void messageDisplay() {
        System.out.println("*****************************");
        System.out.println("** Welcome to Alpha System **");
        System.out.println("*****************************");
        System.out.println("1. View Products");
        System.out.println("2. Admin Login");
        System.out.println("3. User Login");
        System.out.println("4. Create a new User");
        System.out.println("5. Save Data");
        System.out.println("6. Load Data");
        System.out.println("7. Exit");
        System.out.println("*****************************");
        System.out.println("Please enter your choice: ");
    }

}
