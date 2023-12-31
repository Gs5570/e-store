package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an item in the ShoppingCart
 * 
 * @author Md Tanvirul Alam
 */
public class ShoppingCartItem {
    // unique ID of this item in the shopping cart
    @JsonProperty("shoppingCartID") private int shoppingCartId;
    // product ID of the ShoppingCart item
    @JsonProperty("productID") private int productID;
    // user selected color of the item
    @JsonProperty("color") private String color;
    // user selected color of the item
    @JsonProperty("size") private String size;
    // user selected image of the item
    @JsonProperty("image") private String image;
    // user ID of the ShoppingCart owner
    @JsonProperty("userID") private int userID;
    // how many times this item has been added to the cart
    @JsonProperty("shoppingCartQuantity") private int shoppingCartQuantity;

    public ShoppingCartItem(@JsonProperty("shoppingCartID") int shoppingCartId, @JsonProperty("productID") int productID, @JsonProperty("color") String color, @JsonProperty("size") String size, @JsonProperty("image") String image, @JsonProperty("userID") int userID, @JsonProperty("shoppingCartQuantity") int shoppingCartQuantity) {    
        this.shoppingCartId = shoppingCartId;
        this.productID = productID;
        this.color = color;
        this.size = size;
        this.image = image;
        this.userID = userID;
        this.shoppingCartQuantity = shoppingCartQuantity;
    }

    public int getProductId() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUserId() {
        return this.userID;
    }

    public void setUserId(int userID) {
        this.userID = userID;
    }

    public int getshoppingCartQuantity() {
        return this.shoppingCartQuantity;
    }

    public void setshoppingCartQuantity(int shoppingCartQuantity) {
        this.shoppingCartQuantity = shoppingCartQuantity;
    }

    public int getShoppingCartId() {
        return this.shoppingCartId;
    }

}
