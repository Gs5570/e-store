package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.ShoppingCartItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the ShoppingCart File DAO class
 * 
 * @author Md Tanvirul Alam
 */
@Tag("Persistence-tier")
public class ShoppingCartFileDAOTest {
    ShoppingCartFileDAO shoppingCartFileDAO;
    ShoppingCartItem[] testItems;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupShoppingCartFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testItems = new ShoppingCartItem[3];
        testItems[0] = new ShoppingCartItem(1, 4, "red", "S", "img1", 3, 1);
        testItems[1] = new ShoppingCartItem(2, 8, "blue", "M", "img2", 5, 10);
        testItems[2] = new ShoppingCartItem(3, 16, "cyan", "L", "img3", 5, 10);
    

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("shopping_cart.txt"),ShoppingCartItem[].class))
                .thenReturn(testItems);
                shoppingCartFileDAO = new ShoppingCartFileDAO("shopping_cart.txt",mockObjectMapper);
    }

    @Test
    public void testGetItemsInShoppingCart() throws IOException {
        // Invoke
        ShoppingCartItem[] items = shoppingCartFileDAO.getItemsInShoppingCart(3);

        // Analyze
        assertEquals(items.length, 1);
        assertEquals(items[0],testItems[0]);
    }

    @Test
    public void testAddItemtoShoppingCart() throws IOException {
        // Setup
        ShoppingCartItem item = new ShoppingCartItem(4, 8, "blue", "S", "img2", 5, 10);


        // Invoke
        assertDoesNotThrow(() -> shoppingCartFileDAO.addItemtoShoppingCart(item),
                                 "Unexpected exception thrown");

        // Analyze
        ShoppingCartItem actual = shoppingCartFileDAO.geShoppingCartItem(item.getShoppingCartId());
        assertEquals(actual.getShoppingCartId(),item.getShoppingCartId());
        assertEquals(actual.getProductId(),item.getProductId());
        assertEquals(actual.getColor(),item.getColor());
        assertEquals(actual.getSize(),item.getSize());
        assertEquals(actual.getImage(),item.getImage());
        assertEquals(actual.getUserId(),item.getUserId());
        assertEquals(actual.getshoppingCartQuantity(),item.getshoppingCartQuantity());
    }


    @Test
    public void testRemoveItemFromShoppingCart() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> shoppingCartFileDAO.removeItemFromShoppingCart(3),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        assertEquals(shoppingCartFileDAO.items.size(),testItems.length-1);
    }

    @Test
    public void testClearShoppingCart() {
        // Invoke
        assertDoesNotThrow(() -> shoppingCartFileDAO.clearShoppingCart(5),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(shoppingCartFileDAO.items.size(),1);

        for(ShoppingCartItem item: shoppingCartFileDAO.getAllItemsArray()) {
            assertNotEquals(item.getUserId(), 5);
        }
    }
}
