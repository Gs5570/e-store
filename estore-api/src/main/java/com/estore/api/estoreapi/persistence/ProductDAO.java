package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Product;

/**
 * Defines the interface for Product object persistence
 * 
 * @author Md Tanvirul Alam
 */
public interface ProductDAO {
    /**
     * Retrieves all {@linkplain Product products}
     * 
     * @return An array of {@link Product product} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product[] getProducts() throws IOException;

    /**
     * Finds all {@linkplain Product products} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Product products} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product[] findProducts(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Product product} with the given id
     * 
     * @param id The id of the {@link Product product} to get
     * 
     * @return a {@link Product product} object with the matching id
     * <br>
     * null if no {@link Product product} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product getProduct(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Product product}
     * 
     * @param product {@linkplain Product product} object to be created and saved
     * <br>
     * The id of the product object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Product product} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Product createProduct(Product product) throws IOException;

    /**
     * Updates and saves a {@linkplain Product product}
     * 
     * @param {@link Product product} object to be updated and saved
     * 
     * @return updated {@link Product product} if successful, null if
     * {@link Product product} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Product updateProduct(Product product) throws IOException;

    /**
     * Deletes a {@linkplain Product product} with the given id
     * 
     * @param id The id of the {@link Product product}
     * 
     * @return true if the {@link Product product} was deleted
     * <br>
     * false if product with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteProduct(int id) throws IOException;
}
