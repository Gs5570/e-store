package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
// import com.products.api.productsapi.model.Hero;
import com.estore.api.estoreapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Persistence-tier")
public class UserFileDAOTest {
    UserFileDAO userFileDAO;
    User[] testUsers;
    ObjectMapper mockObjectMapper;


     /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupUserFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[4];

        testUsers[0] = new User(1, "Magui");
        testUsers[1] = new User(2, "Jacky");
        testUsers[2] = new User(3, "Penny");
        testUsers[3] = new User(4, "Pecket");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("users.json"),User[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("users.json", mockObjectMapper);

    }

    /*
    @Test
    public void testGetUsers(){

        //invoke
        User[] users = userFileDAO.getUsers();

        // Analyze
        assertEquals(users.length,testUsers.length);
        for (int i = 0; i < testUsers.length;++i)
            assertEquals(users[i],testUsers[i]);
    } */
/*
    @Test
    public void testFindUsers() {
        // Invoke
        User[] users = userFileDAO.findUsers("Pe");

        // Analyze
        assertEquals(users.length,2);  // replace 0 by 2
        assertEquals(users[0],testUsers[2]);
        assertEquals(users[1],testUsers[3]);



    }
 */
    @Test
    public void testGetUser() {
        // Invoke
        User user = userFileDAO.getUser(1);

        // Analzye
        assertEquals(user,testUsers[0]);
    }


    /* 
    @Test
    public void testDeleteUser() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser(1),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test heroes array - 1 (because of the delete)
        // Because heroes attribute of HeroFileDAO is package private
        // we can access it directly
        assertEquals(userFileDAO.users.size(),testUsers.length-1);
    }*/

    @Test
    public void testCreateUser() {
        // Setup
        User user = new User(5, "Phony");

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.createUser(user),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        User actual = userFileDAO.getUser(user.getId());
        assertEquals(actual.getId(),user.getId());
        assertEquals(actual.getName(),user.getName());
    }


    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(User[].class));

        User user = new User(54,"Wall-e");

        assertThrows(IOException.class,
                        () -> userFileDAO.createUser(user),
                        "IOException not thrown");
    }

    @Test
    public void testGetUserNotFound() {
        // Invoke
        User user = userFileDAO.getUser(98);

        // Analyze
        assertEquals(user,null);
    }

    /*
    @Test
    public void testDeleteUserNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser(98),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(userFileDAO.users.size(),testUsers.length);
    } */

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the HeroFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),User[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new UserFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}