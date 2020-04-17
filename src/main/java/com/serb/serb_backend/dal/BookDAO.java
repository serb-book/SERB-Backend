/**
 * Specific book related operations, this doesn't deal with offering
 * For offer related operations see OfferDAO
 * 
 * @date Apr17
 * @author abdullah
 */
package com.serb.serb_backend.dal;

import java.util.ArrayList;

import com.serb.serb_backend.dto.BookDTO;

public interface BookDAO {
	
	/**
	 * @param title
	 * @return a list of book DTOs
	 */
	ArrayList<BookDTO> findBookByTitle(String title);
	
	/**
	 * @param author
	 * @return a list of book DTOs
	 */
	ArrayList<BookDTO> findBookByAuthor(String author);
	
	/**
	 * @param categoryName
	 * @return a list of book DTOs
	 */
	ArrayList<BookDTO> findBookByCategory(String categoryName);
	
	/**
	 * @param ISBN
	 * @return
	 */
	BookDTO findBookByISBN (String ISBN);
	
	/**
	 * searching a combination of inputs (the non null attributes of book)
	 * @param book
	 * @return
	 */
	ArrayList<BookDTO> findBook(BookDTO book);
	
	/**
	 * @param book
	 * @return if operation done successfully
	 */
	boolean addBook(BookDTO book);
}
