package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the ShoppingCartItem class
 * 
 * @author Md Tanvirul Alam
 */
@Tag("Model-tier")
public class ShoppingCartItemTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_shopping_cart_id = 1;
        int expected_product_id = 22;
        String expected_color = "red";
        String expected_size = "S";
        String expected_image = "dummy";
        int expected_user_id = 55;
        int expected_shopping_cart_quantity = 1;        

        // Invoke
        ShoppingCartItem item = new ShoppingCartItem(expected_shopping_cart_id, expected_product_id, expected_color,
        expected_size, expected_image, expected_user_id, expected_shopping_cart_quantity);

        // Analyze
        assertEquals(expected_shopping_cart_id,item.getShoppingCartId());
        assertEquals(expected_product_id,item.getProductId());
        assertEquals(expected_color,item.getColor());
        assertEquals(expected_size,item.getSize());
        assertEquals(expected_image,item.getImage());
        assertEquals(expected_user_id,item.getUserId());
        assertEquals(expected_shopping_cart_quantity,item.getshoppingCartQuantity());
    }

    @Test
    public void testShoppingCartAttributes() {
       // Setup
       int shopping_cart_id = 1;
       int product_id = 22;
       String color = "red";
       String size = "S";
       String image = "dummy";
       int user_id = 55;
       int shopping_cart_quantity = 1;        

       ShoppingCartItem item = new ShoppingCartItem(shopping_cart_id, product_id, color,
       size, image, user_id, shopping_cart_quantity);

       int expected_product_id = 23;
       String expected_color = "green";
       String expected_size = "medium";
       String expected_image = "updated";
       int expected_user_id = 66;
       int expected_shopping_cart_quantity = 2;

        // Invoke
        item.setProductID(expected_product_id);
        item.setColor(expected_color);
        item.setSize(expected_size);
        item.setImage(expected_image);
        item.setUserId(expected_user_id);
        item.setshoppingCartQuantity(expected_shopping_cart_quantity);

        // Analyze
        assertEquals(expected_product_id, item.getProductId());
        assertEquals(expected_color, item.getColor());
        assertEquals(expected_size, item.getSize());
        assertEquals(expected_image, item.getImage());
        assertEquals(expected_user_id, item.getUserId());
        assertEquals(expected_shopping_cart_quantity, item.getshoppingCartQuantity());
    }

}