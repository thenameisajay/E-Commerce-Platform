package com.stacs.group3.ShoppingSystemApp.modelTests;

import main.java.alphasystem.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    Product product1;
    Product product2;
    Product product3;

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

    @Test
    public void testGet() {
        assertEquals(product1.getId(), "1");
        assertEquals(product1.getName(), "Harry Potter: Philosopher's Stone");
        assertEquals(product1.getDescription(), "The first novel in the Harry Potter series and Rowling's debut novel, " +
                "it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, " +
                "when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. " +
                "Harry makes close friends and a few enemies during his first year at the school and with the help of his friends, " +
                "Ron Weasley and Hermione Granger, he faces an attempted comeback by the dark wizard Lord Voldemort, " +
                "who killed Harry's parents, but failed to kill Harry when he was just 15 months old.");
        assertEquals(product1.getAuthor(), "J.K. Rowling");
        assertEquals(product1.getPrice(), "12.99");
        assertEquals(product1.getQuantity(), "100");
        assertEquals(product1.getCategory(), "Fantasy");
        assertEquals(product1.getSellerUsername(), "mj456");

        assertEquals(product2.getId(), "2");
        assertEquals(product2.getName(), "A Man Called Ove");
        assertEquals(product2.getDescription(), "");
        assertEquals(product2.getAuthor(), "Fredrik Backman");
        assertEquals(product2.getPrice(), "6.99");
        assertEquals(product2.getQuantity(), "200");
        assertEquals(product2.getCategory(), "Novel");
        assertEquals(product2.getSellerUsername(), "mj456");

        assertEquals(product3.getId(), "3");
        assertEquals(product3.getName(), "Educated");
        assertEquals(product3.getDescription(), "");
        assertEquals(product3.getAuthor(), "Tara Westover");
        assertEquals(product3.getPrice(), "9.99");
        assertEquals(product3.getQuantity(), "50");
        assertEquals(product3.getCategory(), "Nonfiction");
        assertEquals(product3.getSellerUsername(), "jj789");
    }

    @Test
    public void testSet() {
        product1.setId("4");
        product1.setName("Harry Potter: Chamber of Secrets");
        product1.setDescription("The second novel in the Harry Potter series");
        product1.setAuthor("Joanne Rowling");
        product1.setPrice("11.99");
        product1.setQuantity("150");
        product1.setCategory("Novel");
        product1.setSellerUsername("jj789");

        assertEquals(product1.getId(), "4");
        assertEquals(product1.getName(), "Harry Potter: Chamber of Secrets");
        assertEquals(product1.getDescription(), "The second novel in the Harry Potter series");
        assertEquals(product1.getAuthor(), "Joanne Rowling");
        assertEquals(product1.getPrice(), "11.99");
        assertEquals(product1.getQuantity(), "150");
        assertEquals(product1.getCategory(), "Novel");
        assertEquals(product1.getSellerUsername(), "jj789");
    }

}
