package com.serb_backend.dto;

import java.util.ArrayList;

import com.github.javafaker.Faker;
import static io.qala.datagen.RandomShortApi.*;

import lombok.Data;
@Data
public class ExchangeDTO {
	private ArrayList<String> categoriesOfInterest;
	private float negotiationPrice;
	private OfferDTO offer;
	
	public ExchangeDTO() {
	}
	
	public static ExchangeDTO random(OfferDTO offer){
		Faker faker = new Faker();


		ExchangeDTO exchange = new ExchangeDTO();

		exchange.offer = offer;

		exchange.categoriesOfInterest = new ArrayList<String>();
		if (bool()){
			exchange.categoriesOfInterest.add(faker.book().genre());
			if(bool()){exchange.categoriesOfInterest.add(faker.book().genre());}
		}
		
		exchange.negotiationPrice = Math.abs(integer());

		return exchange;
	}
}
