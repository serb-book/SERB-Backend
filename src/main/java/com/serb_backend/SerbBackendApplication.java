package com.serb_backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SerbBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SerbBackendApplication.class, args);
		
		FillData fillData = context.getBean(FillData.class);
		fillData.testConnection();

		Environment env= context.getBean(Environment.class);

		if(env.getProperty("add_test_data").equalsIgnoreCase("true"))
			fillData.fillAll();       
	}

}
