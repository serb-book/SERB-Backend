package com.serb.serb_backend.dto;

import java.util.ArrayList;

// implement book
public class BookDTO {
	private long id;
	private String referenceLink;
	private String description;
	private ArrayList<String> authors;
	private String ISBN;
	private String title;
	private String image;
	
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BookDTO(long id, String referenceLink, String description, ArrayList<String> authors, String iSBN,
			String title, String image) {
		super();
		this.id = id;
		this.referenceLink = referenceLink;
		this.description = description;
		this.authors = authors;
		ISBN = iSBN;
		this.title = title;
		this.image = image;
	}



	public String getReferenceLink() {
		return referenceLink;
	}
	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getId() {
		return id;
	}
	public ArrayList<String> getAuthors() {
		return authors;
	}
	
}
