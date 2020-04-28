package com.serb_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SerbBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SerbBackendApplication.class, args);
		
		Fill_Data fill_Data = context.getBean(Fill_Data.class);
		fill_Data.test_connection();
	}

}
