package com.serb_backend.dto;

import lombok.Data;

import com.github.javafaker.Faker;

@Data
class State {
	// FIXME no images in database for offer table
	// NOTE should't this be array of images ?
	private String image;
	private String text;
	public State() {}
	public State(String image, String text) {
		super();
		this.image = image;
		this.text = text;
	}

	public static State random(){
		Faker faker = new Faker();
		State state = new State();
		
		state.image = "https://picsum.photos/200/300?random=1";
		state.text = faker.lorem().paragraph();

		return state;

	}
}
