package com.estore.api.estoreapi.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("Model-tier")
public class UserTest {
	@Test
	public void testCtor() {
		// Setup
		int expected_id = 1;
		String expected_name = "Dipkamal";

		// Invoke
		User user = new User(expected_id,expected_name);
    
		// Analyze
		assertEquals(expected_id,user.getId());
		assertEquals(expected_name,user.getName());
	}

	@Test
	public void testName() {
		// Setup
		int id = 2;
		String name = "Tanvir";
		User user = new User(id,name);
        
		String expected_name = "Calua";

		// Invoke
		user.setName(expected_name);

		// Analyze
		assertEquals(expected_name,user.getName());
	}

	@Test
	public void testToString() {
		// Setup
		int id = 1;
		String name = "Dip";
		String expected_string = String.format(User.STRING_FORMAT,id,name); 
        User user = new User(id,name); 
        
		// Invoke
		String actual_string = user.toString();

		// Analyze
		assertEquals(expected_string,actual_string);
	}
}