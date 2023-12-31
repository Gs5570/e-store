package com.estore.api.estoreapi.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test suite for the Hero class
 *
 * @author SWEN Faculty
 */
@Tag("Model-tier")
public class ProductTest {
	@Test
	public void testCtor() {
		// Setup
		int expected_id = 1;
		String expected_name = "Cool Hoodie";
		float expected_price = 59.0f;
		int expected_quantity = 20;
		String expected_description = "Hoodie for year around!!";
		String expected_type = "Hoodie";
		String expected_image = "dummy/img";


		// Invoke
		Product product = new Product(expected_id,expected_name,expected_price,expected_quantity, expected_description, expected_type, expected_image);

		// Analyze
		assertEquals(expected_id,product.getId());
		assertEquals(expected_name,product.getName());
		assertEquals(expected_price,product.getPrice());
		assertEquals(expected_quantity,product.getQuantity());
		assertEquals(expected_description,product.getDescription());
		assertEquals(expected_type,product.getType());
		assertEquals(expected_image, product.getImage());
	}

	@Test
	public void testAttributes() {
		// Setup
		int id = 1;
		String name = "Cool Hoodie";
		float price = 59.0f;
		int quantity = 20;
		String description = "Hoodie for year around!!";
		String type = "Hoodie";
		String image = "dummy/img";
		Product product = new Product(id,name,price,quantity,description,type,image);


		String expected_name = "Cooler Hoodie";
		float expected_price = 100.0f;
		int expected_quantity = 10;
		String expected_description = "Its better now!!!";
		String expected_type = "Hoodie+";
		String expected_image = "dummy/img2";

		// Invoke
		product.setName(expected_name);
		product.setPrice(expected_price);
		product.setQuantity(expected_quantity);
		product.setDescription(expected_description);
		product.setType(expected_type);
		product.setImage(expected_image);


		// Analyze
		assertEquals(expected_name,product.getName());
		assertEquals(expected_price,product.getPrice());
		assertEquals(expected_quantity,product.getQuantity());
		assertEquals(expected_description,product.getDescription());
		assertEquals(expected_type,product.getType());
		assertEquals(expected_image, product.getImage());
	}

	@Test
	public void testToString() {
		// Setup
		int id = 1;
		String name = "Cool Hoodie";
		float price = 59.0f;
		int quantity = 20;
		String description = "Hoodie for year around!!";
		String type = "Hoodie";
		String image = "dummy/img";
		String expected_string = String.format(Product.STRING_FORMAT,id,name,price,quantity,description,type);
		Product product = new Product(id,name,price,quantity,description,type, image);

		// Invoke
		String actual_string = product.toString();

		// Analyze
		assertEquals(expected_string,actual_string);
	}
}