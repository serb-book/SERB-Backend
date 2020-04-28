package com.serb_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SerbBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SerbBackendApplication.class, args);
		
		Fill_Data fill_Data = context.getBean(Fill_Data.class);
		fill_Data.test_connection();
		Environment env= context.getBean(Environment.class);
		if(env.getProperty("add_test_data") == "true")
			fill_Data.fill_all();       
	}

}
