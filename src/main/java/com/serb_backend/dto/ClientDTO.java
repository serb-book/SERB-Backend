package com.serb_backend.dto;

import com.github.javafaker.Faker;
import static io.qala.datagen.RandomShortApi.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)  //TODO Why do we need this function?
@EqualsAndHashCode(callSuper = false) //TODO Why do we need this function?
public class ClientDTO extends AccountDTO {
	private String firstName,lastName;
	private String SSN;
	private Address address;

	public String getFullname(){
		firstName = firstName!=null?firstName:"";
		lastName = lastName!=null?lastName:"";

		return (firstName + " "  + lastName).trim();
	}
	public ClientDTO(String firstName,
					 String lastName,
					 String sSN,
					 String city,
					 String  government,
					 String street,
					 String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = sSN;
		this.address.setCity(city);
		this.address.setCountry(country);
		this.address.setGovernment(government);
		this.address.setStreet(street);
	}
	
	public ClientDTO(String firstName,
			 String lastName,
			 String sSN,
			 String city,
			 String  government,
			 String street,
			 String country,
			 double longitude,
			 double latitude) {
				super();
				this.firstName = firstName;
				this.lastName = lastName;
				SSN = sSN;
				this.address.setCity(city);
				this.address.setCountry(country);
				this.address.setGovernment(government);
				this.address.setStreet(street);
				this.address.setLatitude(latitude);
				this.address.setLongitude(longitude);
			}
	
	
	public ClientDTO() {	}

	public static ClientDTO random(){
		Faker faker = new Faker();

		AccountDTO acc = AccountDTO.random();
		ClientDTO client = new ClientDTO();


		/* NOTE no casting to child check why
		Exception in thread "main" java.lang.ClassCastException: com.serb_backend.dto.AccountDTO cannot be cast to com.serb_backend.dto.ClientDTO
        at com.serb_backend.dto.ClientDTO.random(ClientDTO.java:62)	
		*/

		client.setUsername(acc.getUsername()); 
		client.setPassword(acc.getPassword());
		client.setId(acc.getId());
		client.setPhoneNumbers(acc.getPhoneNumbers());
		client.setProfilePictureURL(acc.getProfilePictureURL());
		client.setEmail(acc.getEmail());

		client.firstName = faker.name().firstName();
		client.lastName = faker.name().lastName();

		client.SSN = numeric(14);
		client.address = Address.random();		

		return client; }
	
	public static ClientDTO random(AccountDTO acc){
		Faker faker = new Faker();

		ClientDTO client = new ClientDTO();

		client.setUsername(acc.getUsername()); 
		client.setPassword(acc.getPassword());
		client.setId(acc.getId());
		client.setPhoneNumbers(acc.getPhoneNumbers());
		client.setProfilePictureURL(acc.getProfilePictureURL());
		client.setEmail(acc.getEmail());

		client.firstName = faker.name().firstName();
		client.lastName = faker.name().lastName();

		client.SSN = numeric(14);
		if(weighedTrue(.8))
			{client.address = Address.random();		}

		return client; }
}
