package com.serb_backend.dto;

import java.util.ArrayList;
import static io.qala.datagen.RandomShortApi.*;
import com.github.javafaker.Faker;

import lombok.Data;


@Data
public class BookDTO {
	private long id;
	private String referenceLink;
	private String description;
	private ArrayList<String> authors;
	private String ISBN;
	private String title;
	private String image;
	
	BookDTO(){}
	
	public static BookDTO random() {
		BookDTO book = new BookDTO();
		Faker faker = new Faker();

		book.id = Math.abs(Long());
		// book.referenceLink = ;
		book.description = faker.lorem().paragraph();

		book.authors = new ArrayList<String>();
		if(weighedTrue(.9)){
			book.authors.add(faker.book().author());
			if (bool()){
				book.authors.add(faker.book().author());
				if (bool()) {
					book.authors.add(faker.book().author());
				}
			}
		}

		if(weighedTrue(.7)){
			book.ISBN = faker.code().isbn13();
		}
		else{
			book.ISBN = faker.code().isbn10();
		}

		book.title = faker.book().title();
		book.image = nullOr("https://picsum.photos/200/300?random=1"); //no image in database
		return book;
	}
	
}
