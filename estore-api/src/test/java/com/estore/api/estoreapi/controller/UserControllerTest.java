package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.persistence.UserDAO;
import com.estore.api.estoreapi.model.User;

public class UserControllerTest {
  private UserController userController;
  private UserDAO mockUserDAO;

  @BeforeEach
  public void setupUserController() {
    mockUserDAO = mock(UserDAO.class);
    userController = new UserController(mockUserDAO);
  }

  @Test
  public void testGetUser() throws IOException {
    User user = new User(99,"Fake User");
    when(mockUserDAO.getUser(user.getId())).thenReturn(user);

    ResponseEntity<User> response = userController.getUser(user.getId());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(user, response.getBody());
  }

  @Test 
  public void testGetProductNotFound() throws Exception {
    int userId = 99;
    when(mockUserDAO.getUser(userId)).thenReturn(null);

    ResponseEntity<User> response = userController.getUser(userId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testGetUserHandleException() throws Exception {
    int userId = 99;
    doThrow(new IOException()).when(mockUserDAO).getUser(userId);

    ResponseEntity<User> response = userController.getUser(userId);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  // @Test
  // public void testCreateProductFailed() throws IOException {
  //   Product product = new Product(99,"Fake Product",99.99f,999);
  //   when(mockProductDAO.createProduct(product)).thenReturn(null);
  
  //   ResponseEntity<Product> response = productController.createProduct(product);
  //   assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
  // }

  @Test
  public void testCreateUserHandleException() throws IOException {
    User user = new User(99,"Fake Product");
    doThrow(new IOException()).when(mockUserDAO).createUser(user);

    ResponseEntity<User> response = userController.createUser(user);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  /*
  @Test
  public void testUpdateProduct() throws IOException {
    Product product = new Product(99,"Fake Product",99.99f,999);
    when(mockProductDAO.updateProduct(product)).thenReturn(product);
    ResponseEntity<Product> response = productController.updateProduct(product);
    product.setName("Updated Fake Product");
    product.setPrice(11.11f);
    product.setQuantity(111);

    response = productController.updateProduct(product);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(product, response.getBody());
  } */

/* 

  @Test
  public void testSearchProducts() throws IOException {
    String searchString = "Fak";
    Product[] products = new Product[2];
    products[0] = new Product(99,"Fake Product",99.99f,999);
    products[1] = new Product(100,"Other Fake Product",99.99f,999);

    when(mockProductDAO.findProducts(searchString)).thenReturn(products);
    ResponseEntity<Product[]> response = productController.searchProducts(searchString);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(products, response.getBody());
  } */

/*   @Test public void testSearchProductsHandleException() throws IOException {
    String searchString = "Fak";
    doThrow(new IOException()).when(mockProductDAO).findProducts(searchString);

    ResponseEntity<Product[]> response = productController.searchProducts(searchString);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  } */

  /*
  @Test
  public void testDeleteProduct() throws IOException {
    int productId = 99;
    when(mockProductDAO.deleteProduct(productId)).thenReturn(true);

    ResponseEntity<Product> response = productController.deleteProduct(productId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  } */

  /* 
  @Test
  public void testDeleteProductNotFound() throws IOException {
    int productId = 99;
    when(mockProductDAO.deleteProduct(productId)).thenReturn(false);

    ResponseEntity<Product> response = productController.deleteProduct(productId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  } */

  /* 

  @Test
  public void testDeleteProductHandleException() throws IOException {
    int heroId = 99;

    doThrow(new IOException()).when(mockProductDAO).deleteProduct(heroId);

    ResponseEntity<Product> response = productController.deleteProduct(heroId);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  } */

}
