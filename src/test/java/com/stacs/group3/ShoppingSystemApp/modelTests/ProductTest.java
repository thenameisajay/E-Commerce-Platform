package com.stacs.group3.ShoppingSystemApp.modelTests;

import com.stacs.group3.ShoppingSystemApp.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test for the Product class.
 */
public class ProductTest {
    private Product product1;
    private Product product2;
    private Product product3;

    /**
     * Initialise the products before each test.
     */
    @BeforeEach
    public void setup() {
        product1 = new Product("1", "Harry Potter: Philosopher's Stone",
                "The first novel in the Harry Potter series and Rowling's debut novel, " +
                        "it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, " +
                        "when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. " +
                        "Harry makes close friends and a few enemies during his first year at the school and with the help of his friends, " +
                        "Ron Weasley and Hermione Granger, he faces an attempted comeback by the dark wizard Lord Voldemort, " +
                        "who killed Harry's parents, but failed to kill Harry when he was just 15 months old.",
                "J.K. Rowling", "12.99", "100", "Fantasy", "mj456");

        product2 = new Product("2", "A Man Called Ove", "",
                "Fredrik Backman", "6.99", "200", "Novel", "mj456");

        product3 = new Product("3", "Educated", "",
                "Tara Westover", "9.99", "50", "Nonfiction", "jj789");
    }

    /**
     * Test for the Get method.
     * Verifies that the data returned by the get method is the same as the data that was inserted
     */
    @Test
    public void testGet() {
        assertEquals(product1.getProductID(), "1");
        assertEquals(product1.getProductName(), "Harry Potter: Philosopher's Stone");
        assertEquals(product1.getProductDescription(), "The first novel in the Harry Potter series and Rowling's debut novel, " +
                "it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, " +
                "when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. " +
                "Harry makes close friends and a few enemies during his first year at the school and with the help of his friends, " +
                "Ron Weasley and Hermione Granger, he faces an attempted comeback by the dark wizard Lord Voldemort, " +
                "who killed Harry's parents, but failed to kill Harry when he was just 15 months old.");
        assertEquals(product1.getAuthor(), "J.K. Rowling");
        assertEquals(product1.getProductPrice(), "12.99");
        assertEquals(product1.getProductQuantity(), "100");
        assertEquals(product1.getProductCategory(), "Fantasy");
        assertEquals(product1.getSellerUsername(), "mj456");

        assertEquals(product2.getProductID(), "2");
        assertEquals(product2.getProductName(), "A Man Called Ove");
        assertEquals(product2.getProductDescription(), "");
        assertEquals(product2.getAuthor(), "Fredrik Backman");
        assertEquals(product2.getProductPrice(), "6.99");
        assertEquals(product2.getProductQuantity(), "200");
        assertEquals(product2.getProductCategory(), "Novel");
        assertEquals(product2.getSellerUsername(), "mj456");

        assertEquals(product3.getProductID(), "3");
        assertEquals(product3.getProductName(), "Educated");
        assertEquals(product3.getProductDescription(), "");
        assertEquals(product3.getAuthor(), "Tara Westover");
        assertEquals(product3.getProductPrice(), "9.99");
        assertEquals(product3.getProductQuantity(), "50");
        assertEquals(product3.getProductCategory(), "Nonfiction");
        assertEquals(product3.getSellerUsername(), "jj789");
    }

    /**
     * Test for the Set method.
     * Verifies that the data in the object is changed to the expected values after calling the set method
     */
    @Test
    public void testSet() {
        product1.setProductID("4");
        product1.setProductName("Harry Potter: Chamber of Secrets");
        product1.setProductDescription("The second novel in the Harry Potter series");
        product1.setAuthor("Joanne Rowling");
        product1.setProductPrice("11.99");
        product1.setProductQuantity("150");
        product1.setProductCategory("Novel");
        product1.setSellerUsername("jj789");

        assertEquals(product1.getProductID(), "4");
        assertEquals(product1.getProductName(), "Harry Potter: Chamber of Secrets");
        assertEquals(product1.getProductDescription(), "The second novel in the Harry Potter series");
        assertEquals(product1.getAuthor(), "Joanne Rowling");
        assertEquals(product1.getProductPrice(), "11.99");
        assertEquals(product1.getProductQuantity(), "150");
        assertEquals(product1.getProductCategory(), "Novel");
        assertEquals(product1.getSellerUsername(), "jj789");
    }

}
