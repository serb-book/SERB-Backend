package com.serb.serb_backend.dto;

import java.util.ArrayList;

public class ExchangeDTO {
	private ArrayList<String> categoriesOfInterest;
	private float negotiationPrice;
	private OfferDTO offer;
	
	public ExchangeDTO() {
	}
	
	public ExchangeDTO(ArrayList<String> categoriesOfInterest, float negotiationPrice, OfferDTO offer) {
		super();
		this.categoriesOfInterest = categoriesOfInterest;
		this.negotiationPrice = negotiationPrice;
		this.offer = offer;
	}
	public ArrayList<String> getCategoriesOfInterest() {
		return categoriesOfInterest;
	}
	public void setCategoriesOfInterest(ArrayList<String> categoriesOfInterest) {
		this.categoriesOfInterest = categoriesOfInterest;
	}
	public float getNegotiationPrice() {
		return negotiationPrice;
	}
	public void setNegotiationPrice(float negotiationPrice) {
		this.negotiationPrice = negotiationPrice;
	}
	public OfferDTO getOffer() {
		return offer;
	}
	public void setOffer(OfferDTO offer) {
		this.offer = offer;
	}
	
}
