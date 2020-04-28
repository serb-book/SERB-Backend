package com.serb_backend.dto;

import lombok.Data;

import java.util.ArrayList;

import static io.qala.datagen.RandomShortApi.*;
import com.github.javafaker.Faker;

@Data
public class State {
	// FIXME no images in database for offer table
	private ArrayList<String> images;
	private String text;
	public State() {}
	public State(ArrayList<String> images, String text) {
		super();
		this.images = images;
		this.text = text;
	}

	public static State random(){
		Faker faker = new Faker();
		State state = new State();
		state.images =new ArrayList<String>();
		if (weighedTrue(.7)){
			state.images.add("https://picsum.photos/200/300?random=1"); 
			if (bool()){
				state.images.add("https://picsum.photos/200/300?random=1"); 
			}
			if (bool()){
				state.images.add("https://picsum.photos/200/300?random=1"); 
			}
		}
		state.text = faker.lorem().paragraph();

		return state;

	}
}
