package com.serb_backend.dto;

import java.util.ArrayList;

import com.github.javafaker.Faker;
import static io.qala.datagen.RandomShortApi.*;

import lombok.Data;
import lombok.Getter;

@Data
public class AccountDTO {
	private String username;
	// TODO: This is going to be a hashed string
	private String password;
	private @Getter long id;
	private ArrayList<String> phoneNumbers;
	private String profilePictureURL;

	public static AccountDTO random() {
		AccountDTO account = new AccountDTO();

		Faker faker = new Faker();

		account.id = Long();
		account.username = faker.name().firstName();
		account.password = alphanumeric(8, 12);
		account.phoneNumbers = new ArrayList<String>();
		if (weighedTrue(.9)) {
			account.phoneNumbers.add(numeric(14));
			if (bool()) {
				account.phoneNumbers.add(numeric(14));
			}
		}

		if (bool()) {
			account.profilePictureURL = nullOr("https://picsum.photos/200/300?random=1");
		}

		return account;
	}
}