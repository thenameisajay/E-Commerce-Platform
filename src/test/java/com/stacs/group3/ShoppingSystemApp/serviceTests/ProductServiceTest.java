package com.stacs.group3.ShoppingSystemApp.serviceTests;

import com.stacs.group3.ShoppingSystemApp.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ProductService class.
 */
public class ProductServiceTest {
    private ProductService productService = new ProductService();

    /**
     * Initialise the productService before each test.
     */
    @BeforeEach
    public void setup() {
        productService.addProduct("1", "Harry Potter: Philosopher's Stone",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, " +
                        "when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. " +
                        "Harry makes close friends and a few enemies during his first year at the school and with the help of his friends, " +
                        "Ron Weasley and Hermione Granger, he faces an attempted comeback by the dark wizard Lord Voldemort, " +
                        "who killed Harry's parents, but failed to kill Harry when he was just 15 months old.",
                "J.K. Rowling", "12.99", "100", "Fantasy", "mj456");

        productService.addProduct("2", "A Man Called Ove",
                "A Man Called Ove is a novel by Swedish writer Fredrik Backman published in Swedish by Forum in 2012. " +
                        "The novel was published in English in 2013 and reached the New York Times Best Seller list 18 months " +
                        "after its publication and stayed on the list for 42 weeks",
                "Fredrik Backman", "6.99", "200", "Novel", "mj456");
    }

    /**
     * Tests the addProduct method of ProductService class.
     * Ensures that the addProduct method throw an exception if productID is empty
     * or if product name is empty
     * or if product description is empty
     * or if author is empty or author name is invalid
     * or if product price is empty
     * or if quantity is empty or 0
     * or if category is empty
     * or if seller name is empty
     * Ensures that the addProduct method adds the product successfully if the input is valid
     */
    @Test
    public void testAddProduct() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "50", "Nonfiction", "jj789"),
                "Product ID cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "50", "Nonfiction", "jj789"),
                "Product Name cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "",
                        "Tara Westover", "9.99", "50", "Nonfiction", "jj789"),
                "Product Description cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "", "9.99", "50", "Nonfiction", "jj789"),
                "Author cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "", "50", "Nonfiction", "jj789"),
                "Product Price cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "", "Nonfiction", "jj789"),
                "Product Quantity cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "50", "", "jj789"),
                "Product Category cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "50", "Nonfiction", ""),
                "Seller Username cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "0", "Nonfiction", "jj789"),
                "Product Quantity cannot be 0");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara 1Westover", "9.99", "50", "Nonfiction", "jj789"),
                "Author cannot contain numbers");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99 pounds", "50", "Nonfiction", "jj789"),
                "Product Price cannot contain letters");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "50 books", "Nonfiction", "jj789"),
                "Product Quantity cannot contain letters");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("3", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "50", "Nonfiction1", "jj789"),
                "Product Category cannot contain numbers");

        assertThrows(IllegalArgumentException.class,
                () -> productService.addProduct("2", "Educated",
                        "Educated is a memoir by the American author Tara Westover. " +
                                "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                                "and emphasizes the importance of education in enlarging her world. " +
                                "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                                "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                                "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                        "Tara Westover", "9.99", "50", "Nonfiction", "jj789"),
                "Product ID already exists");

        productService.addProduct("3", "Educated",
                "Educated is a memoir by the American author Tara Westover. " +
                        "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                        "and emphasizes the importance of education in enlarging her world. " +
                        "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                        "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                        "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                "Tara Westover", "9.99", "50", "Nonfiction", "jj789");
        assertEquals(productService.viewAllProducts().size(), 3);
    }

    /**
     * Tests the searchProductByName method of ProductService class.
     * Ensures that the searchProductByName method throw an exception if the product name is empty
<<<<<<< HEAD
     *      or if no product is available in the system
     * Ensures that the searchProductByName method return the correct number of products by given product name
=======
     * or if no product is available in the system
     * Ensures that the searchProductByName method return the correct number of given product name
>>>>>>> fcb1a05a9f28ee9e8972187615e797063d675bcf
     */
    @Test
    public void testSearchProductByName() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByName(""),
                "Product Name cannot be empty");

        assertEquals(productService.searchProductByName("A Man Called Ove").size(), 1);

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByName("A Man Called Ove"),
                "No products available");
    }

    /**
     * Tests the searchProductByCategory method of ProductService class.
     * Ensures that the searchProductByCategory method throw an exception if the product category is empty
<<<<<<< HEAD
     *      or if the product category is invalid
     *      or if no product is available in the system
     * Ensures that the searchProductByCategory method return the correct number of products by given product name
=======
     * or if the product category is invalid
     * or if no product is available in the system
     * Ensures that the searchProductByCategory method return the correct number of given product name
>>>>>>> fcb1a05a9f28ee9e8972187615e797063d675bcf
     */
    @Test
    public void testSearchProductByCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByCategory(""),
                "Product Category cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByCategory("123"),
                "Product Category cannot contain numbers");

        assertEquals(productService.searchProductByCategory("Fantasy").size(), 1);

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByCategory("Fantasy"),
                "No products available");
    }

    /**
     * Tests the searchProductByAuthor method of ProductService class.
<<<<<<< HEAD
     * Ensures that the searchProductByAuthor method throw an exception if the product author is empty
     *      or if the product author is invalid
     *      or if no product is available in the system
     * Ensures that the searchProductByAuthor method return the correct number of product by given product name
=======
     * Ensures that the searchProductByCategory method throw an exception if the product category is empty
     * or if the product category is invalid
     * or if no product is available in the system
     * Ensures that the searchProductByCategory method return the correct number of given product name
>>>>>>> fcb1a05a9f28ee9e8972187615e797063d675bcf
     */
    @Test
    public void testSearchProductByAuthor() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByAuthor(""),
                "Product Author cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByAuthor("123"),
                "Product Author cannot contain numbers");

        assertEquals(productService.searchProductByAuthor("J.K. Rowling").size(), 1);

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.searchProductByAuthor("J.K. Rowling"),
                "No products available");
    }

    /**
     * Tests the viewAllProducts method of ProductService class.
     * Ensures that the viewAllProducts method throw an exception if no product is available in the system
     * Ensures that the viewAllProducts method return the correct number of products
     */
    @Test
    public void testViewAllProducts() {
        assertEquals(productService.viewAllProducts().size(), 2);

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.viewAllProducts(),
                "No products available");
    }

    /**
     * Tests the deleteProduct method of ProductService class.
     * Ensures that the deleteProduct method throw an exception if the product id is empty or invalid
     *      or if product id does not exist or do not have permission to delete
     *      or if there is no product in the system
     * Ensures that deleteProduct method delete the product
     */
    @Test
    public void testDeleteProduct() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.deleteProduct("", "mj456"),
                "Product ID cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.deleteProduct(".1", "mj456"),
                "Product ID can only contain alpha-numeric characters");

        assertThrows(IllegalArgumentException.class,
                () -> productService.deleteProduct("100", "mj456"),
                "Product ID does not exist or you do not have the permission to delete this product.");

        assertThrows(IllegalArgumentException.class,
                () -> productService.deleteProduct("1", "jj456"),
                "Product ID does not exist or you do not have the permission to delete this product.");

        productService.deleteProduct("1", "mj456");
        assertEquals(productService.viewAllProducts().size(), 1);

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.deleteProduct("1", "mj456"),
                "No products available");
    }

    /**
     * Tests the viewSellerProducts method of ProductService class.
     * Ensures that the viewSellerProducts throws an exception if there is no product available
     * Ensures that the viewSellerProducts return the correct number of products of the seller
     */
    @Test
    public void testViewSellerProducts() {
        productService.addProduct("3", "Educated",
                "Educated is a memoir by the American author Tara Westover. " +
                        "Westover recounts overcoming her survivalist Mormon family in order to go to college, " +
                        "and emphasizes the importance of education in enlarging her world. " +
                        "She details her journey from her isolated life in the mountains of Idaho to completing a PhD program " +
                        "in history at Cambridge University. She started college at the age of 17 having had no formal education. " +
                        "She explores her struggle to reconcile her desire to learn with the world she inhabited with her father.",
                "Tara Westover", "9.99", "50", "Nonfiction", "jj789");

        assertEquals(productService.viewSellerProducts("jj789").size(), 1);
        assertEquals(productService.viewSellerProducts("mj456").size(), 2);

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.viewSellerProducts("jj789"),
                "No products available");
    }

    /**
     * Tests the checkValidationToUpdate method of ProductService class.
     * Ensures the checkValidationToUpdate throws an exception if product id is empty
     *      or if product id is invalid
     *      or if there is no product available
     * Ensures the checkValidationToUpdate return the correct permission of updating this product
     */
    @Test
    public void testCheckValidationToUpdate() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.checkValidationToUpdate("mj456", ""),
                "Product ID cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.checkValidationToUpdate("mj456", ".1"),
                "Product ID can only contain alpha-numeric characters");

        assertThrows(IllegalArgumentException.class,
                () -> productService.checkValidationToUpdate("mj456", "100"),
                "Product ID does not exist");

        assertFalse(productService.checkValidationToUpdate("jj456", "1"));

        assertTrue(productService.checkValidationToUpdate("mj456", "1"));

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.checkValidationToUpdate("1", "mj456"),
                "No products available");
    }

    /**
     * Tests the updateProductName method of ProductService class.
     * Ensures the updateProductName method throws an exception if the product name is empty
     *      or if product name is invalid
     * Ensures the updateProductName method changed the product name to expected value
     */
    @Test
    public void testUpdateProductName() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductName("1", ""),
                "Product Name cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductName("1", "123"),
                "Product Name cannot contain numbers");

        productService.updateProductName("1", "Educated");
        assertEquals(productService.searchProductByName("Educated").size(), 1);
    }

    /**
     * Tests the updateProductDescription method of ProductService class.
     * Ensures the updateProductDescription method throws an exception if the product description is empty
     * Ensures the updateProductDescription method changed the product description to expected value
     */
    @Test
    public void testUpdateProductDescription() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductDescription("1", ""),
                "Product Description cannot be empty");

        productService.updateProductDescription("1", "The first novel in the Harry Potter series");
        assertEquals(productService.viewAllProducts().get("1").getProductDescription(),
                "The first novel in the Harry Potter series");
    }

    /**
     * Tests the updatePrice method of ProductService class.
     * Ensures the updatePrice method throws an exception if the product price is empty
     *      or if product price is invalid
     * Ensures the updatePrice method changed the product price to expected value
     */
    @Test
    public void testUpdatePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductPrice("1", ""),
                "Product Price cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductPrice("1", "10 pounds"),
                "Product Price can only contain numbers");

        productService.updateProductPrice("1", "15");
        assertEquals(productService.viewAllProducts().get("1").getProductPrice(), "15");
    }

    /**
     * Tests the updateProductQuantity method of ProductService class.
     * Ensures the updateProductQuantity method throws an exception if the product quantity is empty
     *      or if product quantity is invalid
     * Ensures the updateProductQuantity method changed the product quantity to expected value
     */
    @Test
    public void testUpdateProductQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductQuantity("1", ""),
                "Product Quantity cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductQuantity("1", "10 books"),
                "Product Quantity can only contain numbers");

        productService.updateProductQuantity("1", "600");
        assertEquals(productService.viewAllProducts().get("1").getProductQuantity(), "600");
    }

    /**
     * Tests the updateProductCategory method of ProductService class.
     * Ensures the updateProductCategory method throws an exception if the product category is empty
     *      or if product category is invalid
     * Ensures the updateProductCategory method changed the product category to expected value
     */
    @Test
    public void testUpdateProductCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductCategory("1", ""),
                "Product Category cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductCategory("1", "123"),
                "Product Category cannot contain numbers");

        productService.updateProductCategory("1", "fiction");
        assertEquals(productService.viewAllProducts().get("1").getProductCategory(), "fiction");
    }

    /**
     * Tests the updateProductAuthor method of ProductService class.
     * Ensures the updateProductAuthor method throws an exception if the product author is empty
     *      or if product author is invalid
     * Ensures the updateProductAuthor method changed the product author to expected value
     */
    @Test
    public void testUpdateProductAuthor() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductAuthor("1", ""),
                "Product Author cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductAuthor("1", "123"),
                "Product Author cannot contain numbers");

        productService.updateProductAuthor("1", "Tara");
        assertEquals(productService.viewAllProducts().get("1").getAuthor(), "Tara");
    }

    /**
     * Tests the updateProductAfterOrder method of ProductService class.
     * Ensures the updateProductAfterOrder method throws an exception if the product id is empty
     *      or if product id is invalid or does not exist
     *      or if there is no product available
     * Ensures the updateProductAfterOrder method changed the product quantity to expected value
     */
    @Test
    public void testUpdateProductAfterOrder() {
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductAfterOrder("", 10),
                "Product ID cannot be empty");

        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductAfterOrder(".100", 10),
                "Product ID can only contain alpha-numeric characters");

        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductAfterOrder("100", 10),
                "Product ID does not exist");

        productService.updateProductAfterOrder("1", 10);
        assertEquals(productService.viewAllProducts().get("1").getProductQuantity(), "90");

        productService.wipeAll();
        assertThrows(IllegalArgumentException.class,
                () -> productService.updateProductAfterOrder("1", 10),
                "No products available");
    }
}
