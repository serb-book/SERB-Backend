package com.serb_backend.dal;

import java.util.List;

import javax.sql.DataSource;

import com.serb_backend.dto.BookDTO;

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
    
    public void addBook(BookDTO book){
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(book);
        // try {
        this.namedParameterJdbcTemplate.update(
            "INSERT INTO book VALUES (:id, :referenceLink, :description, :ISBN, :title)",
            namedParameters);

        for (String auth_name : book.getAuthors()) {
            MapSqlParameterSource auth_par = new MapSqlParameterSource("auth_name", auth_name);
            
            auth_par.addValue("id", book.getId());
            
            this.namedParameterJdbcTemplate.update(
            "insert into BOOK_AUTHORES VALUES (:auth_name, :id)",auth_par);
        }            
        // } catch (Exception e) {
        // System.out.println(book);
        // System.out.println(e);
        // }
    }


    private final RowMapper<BookDTO> bookRowMapper = (resultSet, rowNum) -> {
        BookDTO book = new BookDTO();
        book.setId(resultSet.getLong("ID"));
        book.setReferenceLink(resultSet.getString("REFRENCE_LINK"));
        book.setDescription(resultSet.getString("DESCRIPTION"));
        // book.setAuthors(resultSet.getString(columnLabel));
        book.setISBN(resultSet.getString("ISBN"));
        book.setTitle(resultSet.getString("TITLE"));
        // book.setImage(resultSet.getString(columnLabel));
    
        return book;
    };
    
    public List<BookDTO> findAllBooks(){

        return this.namedParameterJdbcTemplate.query(
            "SELECT ID , REFRENCE_LINK, DESCRIPTION, ISBN, TITLE from BOOK ", bookRowMapper);
            //TODO select authors,images
    }
}
