package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a User entity
 *
 * @author Dipkamal Bhusal
 */

public class User {
    // Package private for tests
    static final String STRING_FORMAT = "User [id=%d, name=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;

    /**
     * Create a User with the given id,
     * @param id The id of the user
     * @param name The name of the user
      */
    
      public User(@JsonProperty("id") int id, @JsonProperty("name") String name) {
          this.id = id;
          this.name = name;
      }
  
          /**
     * Retrieves the id of the user
     * @return The id of the user
     */
    public int getId() {return id;}

    /**
     * Sets the name of the user - necessary for JSON object to Java object deserialization
     * @param name The name of the user
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the user
     * @return The name of the user
     */
    public String getName() {return name;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name);
    }

}
