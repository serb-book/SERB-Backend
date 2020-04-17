package com.serb.serb_backend.dto;

public class SellDTO{
	private float price;
	private OfferDTO offer;
	public SellDTO() {}
	
	/* 
	 * NOTE: 
	 * This is left here to demonstrate why i've chosen 
	 * composition over inheritance
	 * (What if i want to edit the offer content i will 
	 * have to edit the constructor of every
	 * Sub-class
	 * See composition v.s. inheritance for more discussion
	 * about that topic
	 * 
	 * public SellDTO(float price,
			boolean available,
			String stateImage,
			String stateText) {
		this.price = price;
		this.setAvailable(available);
		this.getState().setImage(stateImage);
		this.getState().setText(stateText);
	}*/
	
	public SellDTO(float price, OfferDTO offer) {
		super();
		this.price = price;
		this.offer = offer;
	}

	public OfferDTO getOffer() {
		return offer;
	}

	public void setOffer(OfferDTO offer) {
		this.offer = offer;
	}


	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
