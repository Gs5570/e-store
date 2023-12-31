package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.ShoppingCartItem;

/**
 * Defines the interface for ShoppingCart object persistence
 * 
 * @author Md Tanvirul Alam
 */
public interface ShoppingCartDAO {
    /**
     * Retrieves all {@linkplain ShoppingCartItem items}
     * 
     * @param userID ID of the ShoppingCart owner
     * @return An array of {@link ShoppingCartItem items} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    ShoppingCartItem[] getItemsInShoppingCart(int userID) throws IOException;

    /**
     * 
     * @param id item ID in the shopping cart
     * @return ShoppingCartItem with the corresponding ID
     * @throws IOException if an issue with underlying storage
     */
    ShoppingCartItem geShoppingCartItem(int id) throws IOException;
    
    /**
     * adds a {@linkplain ShoppingCartItem shoppingCartItem}
     * 
     * @param {@link ShoppingCartItem shoppingCartItem} object to be added
     * 
     * @return true if the {@link ShoppingCartItem item} was added
     * <br>
     * false if item cannot be added
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    void addItemtoShoppingCart(ShoppingCartItem shoppingCartItem) throws IOException;

    /**
     * update current quantity in the cart
     */
    void updateCart(ShoppingCartItem shoppingCartItem) throws IOException;

    /**
     * Removes a {@linkplain ShoppingCartItem item} with the given id
     * 
     * @param id The id of the {@link ShoppingCartItem item}
     * 
     * @return true if the {@link ShoppingCartItem item} was removed
     * <br>
     * false if item is not in ShoppingCart
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean removeItemFromShoppingCart(int id) throws IOException;

    /**
     * 
     * @param userId ID of the user
     * @return true if the {@link ShoppingCartItem items} was removed for the user with ID userID
     * <br>
     * false if userID is not present     
     * @throws IOException if underlying storage cannot be accessed
     */
    void clearShoppingCart(int userId) throws IOException;
}
