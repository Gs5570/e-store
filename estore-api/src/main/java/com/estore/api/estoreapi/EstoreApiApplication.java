package com.estore.api.estoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstoreApiApplication {

	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(EstoreApiApplication.class, args);
	}

}
