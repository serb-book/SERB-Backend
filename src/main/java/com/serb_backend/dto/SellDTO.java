package com.serb_backend.dto;

import static io.qala.datagen.RandomShortApi.*;
import lombok.Data;

@Data
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
	 * See composition vs inheritance for more discussion
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
 
	public static SellDTO random(OfferDTO offer){
		SellDTO sell = new SellDTO();
		sell.offer = offer;
		sell.price = (float)(integer(0,100)/100)+integer(0,500);

		return sell;
	}
	
}
