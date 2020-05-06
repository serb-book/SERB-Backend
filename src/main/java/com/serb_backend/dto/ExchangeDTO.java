package com.serb_backend.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.javafaker.Faker;
import static io.qala.datagen.RandomShortApi.*;

import lombok.Data;
@Data
public class ExchangeDTO {
	private Set<String> categoriesOfInterest;
	private float negotiationPrice;
	private OfferDTO offer;
	private List<BookDTO> interests;
	
	public ExchangeDTO() {
	}
	
	public static ExchangeDTO random(OfferDTO offer,List<BookDTO> interests){
		Faker faker = new Faker();

		
		ExchangeDTO exchange = new ExchangeDTO();

		exchange.offer = offer;
		exchange.setInterests(interests);
		exchange.categoriesOfInterest = new HashSet<String>();
		if (bool()){
			exchange.categoriesOfInterest.add(faker.book().genre());
			if(bool()){exchange.categoriesOfInterest.add(faker.book().genre());}
		}
		
		exchange.negotiationPrice = Math.abs(integer());

		return exchange;
	}
}
