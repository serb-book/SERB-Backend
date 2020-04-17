package com.serb.serb_backend.dto;
// implement offer
public class OfferDTO {
	private boolean available;
	private State state;
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
}
