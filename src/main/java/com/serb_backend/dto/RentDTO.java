package com.serb_backend.dto;

import java.util.Date;

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
	
}
