/**
 * @author abdullah
 */
package com.serb.serb_backend.dto;

import java.util.ArrayList;

public class AccountDTO {
	private String username;
	// TODO: This is going to be a hashed string
	private String password;
	private long id;
	private ArrayList<String> phoneNumbers;
	private String profilePictureURL;
	
	public AccountDTO() {
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(String username, String password, long id, ArrayList<String> phoneNumbers,
			String profilePictureURL) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
		this.phoneNumbers = phoneNumbers;
		this.profilePictureURL = profilePictureURL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getProfilePictureURL() {
		return profilePictureURL;
	}

	public void setProfilePictureURL(String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}

	public long getId() {
		return id;
	}
	
	
}
