package com.serb_backend.dal;


import javax.sql.DataSource;

import com.serb_backend.dto.BookDTO;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class BookDAOimp /* implements BookDAO */ {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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


}
