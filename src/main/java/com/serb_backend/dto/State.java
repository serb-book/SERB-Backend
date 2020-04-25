package com.serb_backend.dto;

import lombok.Data;

@Data
class State {
	private String image;
	private String text;
	public State() {}
	public State(String image, String text) {
		super();
		this.image = image;
		this.text = text;
	}
}
