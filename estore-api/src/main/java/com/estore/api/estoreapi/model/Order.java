package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an Order Item
 * 
 * @author Md Tanvirul Alam
 */
public class Order {
    // unique ID of the Order
    @JsonProperty("orderID") private int orderID;
    // ID of the Order
    @JsonProperty("userID") private int userID;
    // how many times this item was added to the cart
    @JsonProperty("items") private ShoppingCartItem[] items;
    // date when the Order was placed
    @JsonProperty("orderDate") private String orderDate;
    // shipping adddress for the Order
    @JsonProperty("address") private String address;


    public Order(@JsonProperty("orderID") int orderID, @JsonProperty("userID") int userID, @JsonProperty("items") ShoppingCartItem[] items, @JsonProperty("orderDate") String orderDate, @JsonProperty("address") String address) {    
        this.orderID = orderID;
        this.userID = userID;
        this.items = items;
        this.orderDate = orderDate;
        this.address = address;
    }

    public int getOrderID() {
        return this.orderID;
    }
    
    public int getUserId() {
        return this.userID;
    }

    public void setUserId(int userID) {
        this.userID = userID;
    }

    public ShoppingCartItem[] getItems() {
        return this.items;
    }

    public void setItems(ShoppingCartItem[] items) {
        this.items = items;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
