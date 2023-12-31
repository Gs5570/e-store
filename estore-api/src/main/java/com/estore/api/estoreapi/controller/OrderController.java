package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Order;
import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCartItem;
import com.estore.api.estoreapi.persistence.OrderDAO;

/**
 * Handles the REST API requests for the Order resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Md Tanvirul Alam
 */


@RestController
@RequestMapping("order")
public class OrderController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private OrderDAO orderDAO;


    /**
     * Creates a REST API controller to reponds to requests
     */
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * Responds to the GET request for all {@linkplain Order orders} of a user
     * 
     * @return ResponseEntity with array of {@link Product product} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{userID}")
    public ResponseEntity<Order[]> getOrders(@PathVariable int userID) {
        LOG.info("GET /order/" + userID);
        try {
            return new ResponseEntity<Order[]>(orderDAO.getOrders(userID),HttpStatus.OK);
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
    @PutMapping("")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        LOG.info("PUT /order/" + order);

        try {
            orderDAO.addOrder(order);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
