package com.serb.serb_backend.dto;

import java.util.Date;

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



	public float getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(float pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	public OfferDTO getOffer() {
		return offer;
	}
	public void setOffer(OfferDTO offer) {
		this.offer = offer;
	}
	
}
