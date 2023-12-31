package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.User;

/**
 * Defines the interface for Product object persistence
 * 
 * @author Dipkamal Bhusal
 */
public interface UserDAO {
    //User[] getUsers() throws IOException;
   // User[] findUsers(String containsText) throws IOException;
    User getUser(int id) throws IOException;
    User createUser(User user) throws IOException;
    //boolean deleteUser(int id) throws IOException;

}
