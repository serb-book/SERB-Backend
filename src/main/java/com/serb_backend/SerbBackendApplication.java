package com.serb_backend;

import java.util.List;

import com.serb_backend.dal.BookDAOimp;
import com.serb_backend.dto.BookDTO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SerbBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SerbBackendApplication.class, args);
		
		Fill_Data fill_Data = context.getBean(Fill_Data.class);
		fill_Data.test_connection();

		Environment env= context.getBean(Environment.class);

		if(env.getProperty("add_test_data").equals("true"))
			fill_Data.fill_all();       
	}

}
