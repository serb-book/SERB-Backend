package com.serb_backend.dto;

import com.github.javafaker.Faker;

import lombok.Data;

@Data
public class Address {
	private String country, city, government, street;
	private double longitude,latitude;
	
	public Address() {	}
	
	public static Address random(){
		Address address = new Address();

		Faker faker = new Faker();
		com.github.javafaker.Address addr = faker.address();

		address.country = addr.country();
		address.city = addr.city();
		address.government = addr.state();
		address.street = addr.streetAddress();

		address.latitude = Double.valueOf(addr.latitude());
		address.longitude = Double.valueOf(addr.longitude());	

		return address;
	}

	
}
