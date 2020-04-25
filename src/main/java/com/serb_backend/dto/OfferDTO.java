package com.serb_backend.dto;

import lombok.Data;

// implement offer
@Data
public class OfferDTO {
	private boolean available;
	private State state;
	
	OfferDTO(){}
}
