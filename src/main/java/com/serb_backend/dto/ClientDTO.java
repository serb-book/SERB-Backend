package com.serb_backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientDTO extends AccountDTO {
	private String firstName,lastName;
	private String SSN;
	private Address address;
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
		SSN = sSN;
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
	public ClientDTO() {
		// TODO Auto-generated constructor stub
	}

}