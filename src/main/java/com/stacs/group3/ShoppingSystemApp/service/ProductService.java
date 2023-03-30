package com.stacs.group3.ShoppingSystemApp.service;


import com.stacs.group3.ShoppingSystemApp.model.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to create a product service.
 * The service is for the product object.
 */
public class ProductService implements Serializable {
    public Map<String, Product> product = new HashMap<>();

    /**
     * This method is used to save the product data.
     *
     * @throws IOException - if an I/O error occurs
     */
    public void saveData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/productData.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(product);
        o.close();
        f.close();
    }

    /**
     * This method is used to load the product data.
     *
     * @throws ClassNotFoundException - if the class of a serialized object cannot be found
     * @throws IOException            - if an I/O error occurs
     */
    public void loadData() {
        try {
            FileInputStream fi = new FileInputStream("src/main/resources/data/productData.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            // read object from file
            product = (Map<String, Product>) oi.readObject();
            oi.close();
            fi.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method is used to wipe all the product data.
     */
    public void wipeAll() {
        product.clear();
    }

    /**
     * This method is used to add a product to the map.
     *
     * @param productID          - The product ID
     * @param productName        - The product name
     * @param productDescription - The product description
     * @param author             - The author of the product
     * @param productPrice       - The product price
     * @param productQuantity    - The product quantity
     * @param productCategory    - The product category
     * @param sellerUsername     - The seller userName
     */
    public void addProduct(String productID, String productName, String productDescription, String author, String productPrice, String productQuantity, String productCategory, String sellerUsername) {
        // First Check : For empty fields
        if (productID == null || productID.isEmpty())
            throw new IllegalArgumentException("Product ID cannot be empty");
        if (productName == null || productName.isEmpty())
            throw new IllegalArgumentException("Product Name cannot be empty");
        if (productDescription == null || productDescription.isEmpty())
            throw new IllegalArgumentException("Product Description cannot be empty");
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException("Author cannot be empty");
        if (productPrice == null || productPrice.isEmpty())
            throw new IllegalArgumentException("Product Price cannot be empty");
        if (productQuantity == null || productQuantity.isEmpty() || productQuantity.equalsIgnoreCase("0"))
            throw new IllegalArgumentException("Product Quantity cannot be empty");
        if (productCategory == null || productCategory.isEmpty())
            throw new IllegalArgumentException("Product Category cannot be empty");
        if (sellerUsername == null || sellerUsername.isEmpty())
            throw new IllegalArgumentException("Seller Username cannot be empty");

        // Second Check : For unique Name cases
        if (productQuantity.equalsIgnoreCase("0")) {
            throw new IllegalArgumentException("Product Quantity cannot be 0");
        }
        if (author.matches(".*\\d.*"))
            throw new IllegalArgumentException("Author cannot contain numbers");
        if (productPrice.matches(".*[a-zA-Z]+.*"))
            throw new IllegalArgumentException("Product Price cannot contain letters");
        if (productQuantity.matches(".*[a-zA-Z]+.*"))
            throw new IllegalArgumentException("Product Quantity cannot contain letters");
        if (productCategory.matches(".*\\d.*"))
            throw new IllegalArgumentException("Product Category cannot contain numbers");
        // Third Check : For duplicate product ID
        if (product.containsKey(productID)) {
            throw new IllegalArgumentException("Product ID already exists");
        } else {
            // ADD PRODUCT
            product.put(productID.toLowerCase(), new Product(productID.toLowerCase(), productName.toLowerCase(), productDescription.toLowerCase(), author.toLowerCase(), productPrice.toLowerCase(), productQuantity.toLowerCase(), productCategory.toLowerCase(), sellerUsername.toLowerCase()));
        }
    }

    /**
     * This method is used for searching a Product by Name.
     *
     * @param productName - The product name
     * @return - The product details
     */
    public Map<String, Product> searchProductByName(String productName) {
        Map<String, Product> returnProduct = new HashMap<>();
        // First Check : For empty fields
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product Name cannot be empty");
        } else if (product.isEmpty()) {
            // Second Check : If product Map is empty
            throw new IllegalArgumentException("No products available");
        } else {
            for (Map.Entry<String, Product> entry : product.entrySet()) {
                String productSearch = entry.getValue().getProductName();
                if (productSearch.equalsIgnoreCase(productName)) {
                    // Retrieve the product details from the Map.
                    returnProduct.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return returnProduct;
    }

    /**
     * This method is used for searching a Product by Category.
     *
     * @param productCategory - The product category
     * @return - The product details
     */
    public Map<String, Product> searchProductByCategory(String productCategory) {
        Map<String, Product> returnProduct = new HashMap<>();
        // First Check : For empty fields
        if (productCategory == null || productCategory.isEmpty()) {
            throw new IllegalArgumentException("Product Category cannot be empty");
        } else if (product.isEmpty()) {
            // Second Check : If product Map is empty
            throw new IllegalArgumentException("No products available");
        } else if (productCategory.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Product Category cannot contain numbers");
        } else {
            for (Map.Entry<String, Product> entry : product.entrySet()) {
                String productSearch = entry.getValue().getProductCategory();
                if (productSearch.equalsIgnoreCase(productCategory)) {
                    // Retrieve the product details from the Map.
                    returnProduct.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return returnProduct;
    }

    /**
     * This method is used for searching a Product by Author.
     *
     * @param productAuthor - The product author
     * @return - The product details
     */
    public Map<String, Product> searchProductByAuthor(String productAuthor) {
        Map<String, Product> returnProduct = new HashMap<>();
        // First Check : For empty fields
        if (productAuthor == null || productAuthor.isEmpty()) {
            throw new IllegalArgumentException("Product Author cannot be empty");
        } else if (product.isEmpty()) {
            // Second Check : If product Map is empty
            throw new IllegalArgumentException("No products available");
        } else if (productAuthor.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Product Author cannot contain numbers");
        } else {
            for (Map.Entry<String, Product> entry : product.entrySet()) {
                String productSearch = entry.getValue().getAuthor();
                if (productSearch.equalsIgnoreCase(productAuthor)) {
                    // Retrieve the product details from the Map.
                    returnProduct.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return returnProduct;
    }

    /**
     * This method is for the user to view all the products all at once.
     *
     * @return - The product details
     */
    public Map<String, Product> viewAllProducts() {
        Map<String, Product> returnProduct = new HashMap<>();
        if (product.isEmpty()) {
            // Second Check : If product Map is empty
            throw new IllegalArgumentException("No products available");
        } else {
            // Retrieve the product details from the Map.
            returnProduct.putAll(product);
        }
        return returnProduct;
    }

    /**
     * This method is used for deleting a product.
     *
     * @param productID      - The product ID
     * @param sellerUsername - The seller username
     */
    public void deleteProduct(String productID, String sellerUsername) {
        // First Check : For empty fields
        if (productID == null || productID.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        // Second Check : Check if Product ID is of the format alphanumeric.
        else if (!productID.matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Product ID can only contain alpha-numeric characters");
        } else if (product.isEmpty()) {
            throw new IllegalArgumentException("No products available");
        } else {
            // Third Check : Check if Product ID exists in the Map.
            if (product.containsKey(productID) && product.get(productID).getSellerUsername().equalsIgnoreCase(sellerUsername)) {
                product.remove(productID);
            } else {
                throw new IllegalArgumentException("Product ID does not exist or you do not have the permission to delete this product.");
            }
        }
    }

    /**
     * This method is for the seller to view their products.
     *
     * @param sellerUsername - The seller userName
     * @return - The product details
     */
    public Map<String, Product> viewSellerProducts(String sellerUsername) {
        Map<String, Product> returnProduct = new HashMap<>();
        if (product.isEmpty()) {
            // Second Check : If product Map is empty
            throw new IllegalArgumentException("No products available");
        } else {
            for (Map.Entry<String, Product> entry : product.entrySet()) {
                String productSearch = entry.getValue().getSellerUsername();
                if (productSearch.equalsIgnoreCase(sellerUsername)) {
                    // Retrieve the product details from the Map.
                    returnProduct.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return returnProduct;
    }


    /**
     * This method is used validate the product details before updating.
     *
     * @param sellerUsername - The seller username
     * @param productID      - The product ID
     * @return - The product details
     */
    public boolean checkValidationToUpdate(String sellerUsername, String productID) {
        boolean check = false;
        // First Check : For empty fields
        if (productID == null || productID.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        // Second Check : Check if Product ID is of the format alphanumeric.
        else if (!productID.matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Product ID can only contain alpha-numeric characters");
        } else if (product.isEmpty()) {
            throw new IllegalArgumentException("No products available");
        } else {
            // Third Check : Check if Product ID exists in the Map.
            if (product.containsKey(productID) && product.get(productID).getSellerUsername().equalsIgnoreCase(sellerUsername)) {
                check = true;
            } else if (!product.containsKey(productID)) {
                throw new IllegalArgumentException("Product ID does not exist");
            } else {
                check = false;
            }
        }
        return check;
    }

    /**
     * This method is used for updating the product name.
     *
     * @param productID   - The product ID
     * @param productName - The product name
     */
    public void updateProductName(String productID, String productName) {
        // First Check : For empty fields
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product Name cannot be empty");
        } else if (productName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Product Name cannot contain numbers");
        } else {
            product.get(productID).setProductName(productName);
        }
    }

    /**
     * This method is used for updating the product description.
     *
     * @param productID          - The product ID
     * @param productDescription - The product description
     */
    public void updateProductDescription(String productID, String productDescription) {
        // First Check : For empty fields
        if (productDescription == null || productDescription.isEmpty()) {
            throw new IllegalArgumentException("Product Description cannot be empty");
        } else {
            product.get(productID).setProductDescription(productDescription);
        }
    }

    /**
     * This method is used for updating the product price.
     *
     * @param productID    - The product ID
     * @param productPrice - The product price
     */
    public void updateProductPrice(String productID, String productPrice) {
        // First Check : For empty fields and check if price is a float or integer but not a string
        if (productPrice == null || productPrice.isEmpty()) {
            throw new IllegalArgumentException("Product Price cannot be empty");
        } else if (!productPrice.matches("^[0-9]*\\.?[0-9]*$")) {
            throw new IllegalArgumentException("Product Price can only contain numbers");
        } else {
            product.get(productID).setProductPrice(productPrice);
        }
    }

    /**
     * This method is used for updating the product quantity.
     *
     * @param productID       - The product ID
     * @param productQuantity - The product quantity
     */
    public void updateProductQuantity(String productID, String productQuantity) {
        // First Check : For empty fields and check if quantity is a number
        if (productQuantity == null || productQuantity.isEmpty()) {
            throw new IllegalArgumentException("Product Quantity cannot be empty");
        } else if (!productQuantity.matches("^[0-9]*$")) {
            throw new IllegalArgumentException("Product Quantity can only contain numbers");
        } else {
            product.get(productID).setProductQuantity(productQuantity);
        }
    }

    /**
     * This method is used for updating the product category.
     *
     * @param productID       - The product ID
     * @param productCategory - The product category
     */
    public void updateProductCategory(String productID, String productCategory) {
        // First Check : For empty fields
        if (productCategory == null || productCategory.isEmpty()) {
            throw new IllegalArgumentException("Product Category cannot be empty");
        } else if (productCategory.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Product Category cannot contain numbers");
        } else {
            product.get(productID).setProductCategory(productCategory);
        }
    }

    /**
     * This method is used for updating the product author.
     *
     * @param productID     - The product ID
     * @param productAuthor - The product author
     */
    public void updateProductAuthor(String productID, String productAuthor) {
        // First Check : For empty fields
        if (productAuthor == null || productAuthor.isEmpty()) {
            throw new IllegalArgumentException("Product Author cannot be empty");
        } else if (productAuthor.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Product Author cannot contain numbers");
        } else {
            product.get(productID).setAuthor(productAuthor);
        }
    }

    /**
     * This method is used for updating the product after an order is placed.
     *
     * @param productID       - The product ID
     * @param productQuantity - The product quantity
     */
    public void updateProductAfterOrder(String productID, int productQuantity) {
        // First Check : For empty fields
        if (productID == null || productID.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        // Second Check : Check if Product ID is of the format alphanumeric.
        else if (!productID.matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Product ID can only contain alpha-numeric characters");
        } else if (product.isEmpty()) {
            throw new IllegalArgumentException("No products available");
        } else {
            // Third Check : Check if Product ID exists in the Map.
            if (product.containsKey(productID)) {
                int quantity = Integer.parseInt(product.get(productID).getProductQuantity());
                int newQuantity = quantity - productQuantity;
                product.get(productID).setProductQuantity(String.valueOf(newQuantity));
            } else {
                throw new IllegalArgumentException("Product ID does not exist");
            }
        }
    }
}
