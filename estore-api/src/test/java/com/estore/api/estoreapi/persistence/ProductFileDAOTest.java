package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Persistence-tier")
public class ProductFileDAOTest {
    ProductFileDAO productFileDAO;
    Product[] testProducts;
    ObjectMapper mockObjectMapper;


     /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupProductFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testProducts = new Product[4];

        testProducts[0] = new Product(1, "comfort hoodie", 10.05f, 3, "Cool hoodie", "Hoodie", "img1");
        testProducts[1] = new Product(2, "winter jacket", 5.0f,4, "Jacket for winter", "Jacket", "img2");
        testProducts[2] = new Product(3, "plain shirt", 7.0f,4, "Plain but stylish!", "Shirt", "img3");
        testProducts[3] = new Product(4, "Cool hoodie", 8.0f,100, "Gives comfort too", "Hoodie", "img4");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("products.json"),Product[].class))
                .thenReturn(testProducts);
        productFileDAO = new ProductFileDAO("products.json", mockObjectMapper);

    }

    @Test
    public void testGetProducts(){

        //invoke
        Product[] products = productFileDAO.getProducts();

        // Analyze
        assertEquals(products.length,testProducts.length);
        for (int i = 0; i < testProducts.length;++i)
            assertEquals(products[i],testProducts[i]);
    }

    @Test
    public void testFindProducts() {
        // Invoke
        Product[] products = productFileDAO.findProducts("hoodie");

        // Analyze
        assertEquals(products.length,2);  
        assertEquals(products[0],testProducts[0]);
        assertEquals(products[1],testProducts[3]);

    }

    @Test
    public void testGetProduct() {
        // Invoke
        Product product = productFileDAO.getProduct(1);

        // Analzye
        assertEquals(product,testProducts[0]);
    }

    @Test
    public void testDeleteProduct() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(1),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test heroes array - 1 (because of the delete)
        // Because heroes attribute of HeroFileDAO is package private
        // we can access it directly
        assertEquals(productFileDAO.products.size(),testProducts.length-1);
    }

    @Test
    public void testCreateProduct() {
        // Setup
        Product product = new Product(5, "placeholder", 8.0f,100, "Just a placeholder", "Placeholder", "dummy/img");


        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.createProduct(product),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Product actual = productFileDAO.getProduct(product.getId());
        assertEquals(actual.getId(),product.getId());
        assertEquals(actual.getName(),product.getName());
    }

    @Test
    public void testUpdateProduct() {
        // Setup
        Product product = new Product(1, "placeholder", 8.0f,100, "Just a placeholder", "Placeholder", "img");

        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Product actual = productFileDAO.getProduct(product.getId());
        assertEquals(actual,product);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Product[].class));

        Product product = new Product(3, "placeholder", 8.0f,100, "Just a placeholder", "Placeholder", "img");

        assertThrows(IOException.class,
                        () -> productFileDAO.createProduct(product),
                        "IOException not thrown");
    }

    @Test
    public void testGetProductNotFound() {
        // Invoke
        Product product = productFileDAO.getProduct(98);

        // Analyze
        assertEquals(product,null);
    }

    @Test
    public void testDeleteProductNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(98),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(productFileDAO.products.size(),testProducts.length);
    }

    @Test
    public void testUpdateProductNotFound() {
        // Setup
        Product product = new Product(98, "placeholder", 8.0f,100, "Just a placeholder", "Placeholder", "img");

        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product),
                                                "Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }

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
                .readValue(new File("doesnt_matter.txt"),Product[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new ProductFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}
