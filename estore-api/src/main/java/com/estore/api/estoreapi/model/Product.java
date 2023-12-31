package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Product entity
 *
 * @author Md Tanvirul Alam
 */
public class Product {
    // Package private for tests
    static final String STRING_FORMAT = "Product [id=%d, name=%s price=%f quantity=%d description=%s type=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("price") private float price;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("description") private String description;
    @JsonProperty("type") private String type;
    @JsonProperty("image") private String image;

    /**
     * Create a Product with the given id, name, price and quantity
     *
     * @param id       The id of the product
     * @param name     The name of the product
     * @param price    The price of the product
     * @param quantity The quantity of the product
     * @param type     The type of the product
     *                 <p>
     *                 {@literal @}JsonProperty is used in serialization and deserialization
     *                 of the JSON object to the Java object in mapping the fields.  If a field
     *                 is not provided in the JSON object, the Java field gets the default Java
     *                 value, i.e. 0 for int
     */
    public Product(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("price") float price, @JsonProperty("quantity") int quantity, @JsonProperty("description") String description, @JsonProperty("type") String type,  @JsonProperty("image") String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.type = type;
        this.image = image;
    }

    /**
     * Retrieves the id of the product
     * @return The id of the product
     */
    public int getId() {return id;}

    /**
     * Sets the name of the product - necessary for JSON object to Java object deserialization
     * @param name The name of the product
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the product
     * @return The name of the product
     */
    public String getName() {return name;}

    /**
     * Sets the price of the product - necessary for JSON object to Java object deserialization
     * @param price The price of the product
     */
    public void setPrice(float price) {this.price = price;}

    /**
     * Retrieves the price of the product
     * @return The price of the product
     */
    public float getPrice() {return price;}

    /**
     * Sets the quantity of the product - necessary for JSON object to Java object deserialization
     * @param quantity The quantity of the product
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}

    /**
     * Retrieves the quantity of the product
     * @return The quantity of the product
     */
    public int getQuantity() {return quantity;}

    /**
     * Sets the type of the product
     * @param type The type of the product
     */
    public void setType(String type) {this.type = type;}

    /**
     * Retrieves the type of the product
     * @return The type of the product
     */
    public String getType() {return type;}


    /**
     * Sets the description of the product
     * @param description The description of the product
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * Retrieves the description of the product
     * @return The description of the product
     */
    public String getDescription() {return description;}


    /**
     * Sets the image of the product
     * @param image The image of the product
     */
    public void setImage(String image) {this.image = image;}

    /**
     * Retrieves the image of the product
     * @return The image of the product
     */
    public String getImage() {return this.image;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,price,quantity,description,type);
    }
}