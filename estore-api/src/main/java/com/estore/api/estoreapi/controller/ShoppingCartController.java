package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCartItem;
import com.estore.api.estoreapi.persistence.ShoppingCartDAO;

/**
 * Handles the REST API requests for the ShoppingCart resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Md Tanvirul Alam
 */


@RestController
@RequestMapping("shoppingcart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDAO;


    /**
     * Creates a REST API controller to reponds to requests
     */
    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }


    /**
     * Responds to the GET request for all {@linkplain Product products}
     * 
     * @return ResponseEntity with array of {@link Product product} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartItem[]> getShoppingCartItems(@PathVariable int id) {
        LOG.info("GET /shoppingcart/" + id);
        try {
            return new ResponseEntity<ShoppingCartItem[]>(shoppingCartDAO.getItemsInShoppingCart(id),HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Adds a {@linkplain ShoppingCartItem item}
     * 
     * @param product The ShoppingCartItem item to add
     * 
     * @return ResponseEntity HTTP status of OK if added<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not added<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<ShoppingCartItem> addItemToShoppingCart(@RequestBody ShoppingCartItem item) {
        LOG.info("POST /shoppingcart/" + item);

        try {
            shoppingCartDAO.addItemtoShoppingCart(item);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<ShoppingCartItem> updateCart(@RequestBody ShoppingCartItem item) {
        LOG.info("PUT /shoppingcart/" + item);

        try {
            shoppingCartDAO.updateCart(item);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain ShoppingCartItem item} with the given id
     * 
     * @param id The id of the {@link ShoppingCartItem item} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ShoppingCartItem> removeItemFromShoppingCart(@PathVariable int id) {
        LOG.info("DELETE /shoppingcart/" + id);

        try {
            boolean removed = shoppingCartDAO.removeItemFromShoppingCart(id);
            if (removed == true)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<ShoppingCartItem> clearShoppingCart(@RequestParam(value = "userID") int userID) {
        LOG.info("CLEAR /shoppingcart/" + userID);

        try {
            shoppingCartDAO.clearShoppingCart(userID);
            return new ResponseEntity<>(HttpStatus.OK);}
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
