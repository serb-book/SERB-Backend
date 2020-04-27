package com.serb_backend.dto;

import lombok.Data;
import static io.qala.datagen.RandomShortApi.*;

// implement offer
@Data
public class OfferDTO {
	private boolean available;
	private State state;
	
	OfferDTO(){}

	public OfferDTO random(){
		OfferDTO offer = new OfferDTO();

		offer.state = new State().random();
		offer.available = weighedTrue(.9);
		
		return offer;
	}
}
