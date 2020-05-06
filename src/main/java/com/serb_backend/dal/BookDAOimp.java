package com.serb_backend.dal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import com.serb_backend.dto.BookDTO;
import com.serb_backend.dto.ExchangeDTO;
import com.serb_backend.dto.OfferDTO;
import com.serb_backend.dto.RentDTO;
import com.serb_backend.dto.SellDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOimp /* implements BookDAO */ {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    public BookDAOimp(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public boolean addBook(BookDTO book){
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(book);
        // try {
        this.namedParameterJdbcTemplate.update(
            "INSERT INTO book VALUES (:id, :referenceLink, :description, :ISBN, :title)",
            namedParameters);

        for (String authorName : book.getAuthors()) {
            MapSqlParameterSource authParam = new MapSqlParameterSource("auth_name", authorName);
            
            authParam.addValue("id", book.getId());
            
            this.namedParameterJdbcTemplate.update(
            "insert into BOOK_AUTHORES VALUES (:auth_name, :id)",authParam);
        }
        return true;
    }


    private final RowMapper<BookDTO> bookRowMapper = (resultSet, rowNum) -> {
        BookDTO book = new BookDTO();
        book.setId(resultSet.getLong("ID"));
        book.setReferenceLink(resultSet.getString("REFRENCE_LINK"));
        book.setDescription(resultSet.getString("DESCRIPTION"));
        
        String authors[] = resultSet.getString("authors").split(",");
        Set<String> authorsList = new HashSet<String>();
        for (String author : authors) {
            authorsList.add(author);
        }
        book.setAuthors(authorsList);
        
        book.setISBN(resultSet.getString("ISBN"));
        book.setTitle(resultSet.getString("TITLE"));

        // FIXME no image in database
        // book.setImage(resultSet.getString(columnLabel));
        return book;
    };
    
    public String selectBooksQuery(String fillter){
    /*
    book_all view:
        ID
        REFRENCE_LINK
        DESCRIPTION
        ISBN
        TITLE
        AUTHORS
        OFFER_ID
        OFFER_TYPE
        OFFER_PRICE
    */
        return 
            "SELECT * \n"+
            " from book_all \n"+
            fillter;    
    }        

    public List<BookDTO> findAllBooks(){

        return this.namedParameterJdbcTemplate.query(
            selectBooksQuery(""),
            bookRowMapper);
    }

	// @Override
	public List<BookDTO> findBookByAuthor(String author) {

        return this.namedParameterJdbcTemplate.query(
            selectBooksQuery(" WHERE LOWER(authors) like LOWER(\'%"+author+"%\')")
            , bookRowMapper);
    }

    public List<BookDTO> findBookByTitle(String title) {

        return this.namedParameterJdbcTemplate.query(
            selectBooksQuery(" WHERE LOWER(TITLE) like LOWER(\'%"+title+"%\')")
            , bookRowMapper);
    }

    public List<BookDTO> findBookByTitleAndAuthor(String title,String author) {

        return this.namedParameterJdbcTemplate.query(
            selectBooksQuery(" WHERE LOWER(TITLE) like LOWER(\'%"+title+"%\') and LOWER(authors) like LOWER(\'%"+author+"%\')")
            , bookRowMapper);
    }
    List<BookDTO> findBookByCategory(String categoryName){
        // FIXME no relation bertwean book and catagories
        // MapSqlParameterSource namedParameters = new MapSqlParameterSource("categoryName", categoryName);
        // return this.namedParameterJdbcTemplate.query(
        //     select_books+ " WHERE LOWER(b.TITLE) like \"%sLOWER(:categoryName)%s\""
        //     ,namedParameters, bookRowMapper);
        return null;
    }

    public BookDTO findBookByID(long id){
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);

        return this.namedParameterJdbcTemplate.queryForObject(
            selectBooksQuery("where id = :id"), namedParameters,  bookRowMapper);
    }

    public BookDTO findBookByIsbn(String isbn){
        SqlParameterSource namedParameters = new MapSqlParameterSource("isbn", isbn);

        return this.namedParameterJdbcTemplate.queryForObject(
            selectBooksQuery("where isbn = :isbn"), namedParameters,  bookRowMapper);
    }
    
}
