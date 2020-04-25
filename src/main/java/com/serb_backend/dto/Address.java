package com.serb_backend.dto;

import com.github.javafaker.Faker;

import lombok.Data;

@Data
public class Address {
	private String country, city, government, street;
	private double longitude,latitude;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
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



	public Address(String country, String city, String government, String street, double longitude, double latitude) {
		super();
		this.country = country;
		this.city = city;
		this.government = government;
		this.street = street;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	
}
