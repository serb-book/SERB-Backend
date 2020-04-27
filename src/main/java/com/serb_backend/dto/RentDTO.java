package com.serb_backend.dto;

import java.util.Date;

import static io.qala.datagen.RandomShortApi.*;
import static io.qala.datagen.RandomDate.*;

import lombok.Data;
@Data
public class RentDTO {
	private float pricePerDay;
	private Date endingDate;
	private OfferDTO offer;
	public RentDTO() {}
	
	public RentDTO(float pricePerDay, Date endingDate, OfferDTO offer) {
		super();
		this.pricePerDay = pricePerDay;
		this.endingDate = endingDate;
		this.offer = offer;
	}
	
	public RentDTO random(OfferDTO offer){
		RentDTO rent = new RentDTO();
		rent.offer = offer;

		rent.endingDate = Date.from(between(now().minusYears(1), now().plusYears(2)).instant());
		rent.pricePerDay =(float)(integer(0,100)/100)+integer(0,500);

		return rent;
	}
}
