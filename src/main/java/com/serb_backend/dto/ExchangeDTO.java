package com.serb_backend.dto;

import java.util.ArrayList;

import lombok.Data;
@Data
public class ExchangeDTO {
	private ArrayList<String> categoriesOfInterest;
	private float negotiationPrice;
	private OfferDTO offer;
	
	public ExchangeDTO() {
	}
	
}
