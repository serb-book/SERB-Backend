package com.serb_backend.dto;

import lombok.Data;
import static io.qala.datagen.RandomShortApi.*;

// implement offer
@Data
public class OfferDTO {
	private long id;
	private boolean available;
	private State state;
	private ClientDTO client;
	private BookDTO book;
	
	OfferDTO(){}

	public static OfferDTO random(ClientDTO client, BookDTO book){
		OfferDTO offer = new OfferDTO();

		offer.id = Math.abs(Long());
		offer.client = client;
		offer.book = book;
		offer.state = State.random();
		offer.available = weighedTrue(.8);

		return offer;
	}
}
