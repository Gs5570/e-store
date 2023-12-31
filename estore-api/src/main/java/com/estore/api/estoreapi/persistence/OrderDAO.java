package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Order;

/**
 * Defines the interface for Order object persistence
 * 
 * @author Md Tanvirul Alam
 */
public interface OrderDAO {
    /**
     * Retrieves all {@linkplain ShoppingCartItem items}
     * 
     * @param userID ID of the user
     * @return An array of {@link Order items} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Order[] getOrders(int userID) throws IOException;

    /**
     * 
     * @param order Order to add
     * @throws IOException if an issue with underlying storage
     */
    void addOrder(Order order) throws IOException;
}
