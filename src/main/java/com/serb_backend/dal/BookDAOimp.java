package com.serb_backend.dal;

import java.util.ArrayList;
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
    
    public boolean addBook(BookDTO book){
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
        return true;
    }


    private final RowMapper<BookDTO> bookRowMapper = (resultSet, rowNum) -> {
        BookDTO book = new BookDTO();
        book.setId(resultSet.getLong("ID"));
        book.setReferenceLink(resultSet.getString("REFRENCE_LINK"));
        book.setDescription(resultSet.getString("DESCRIPTION"));
        
        String authors[] = resultSet.getString("authors").split(",");
        ArrayList<String> authors_list = new ArrayList<String>();
        for (String author : authors) {
            authors_list.add(author);
        }
        book.setAuthors(authors_list);
        
        book.setISBN(resultSet.getString("ISBN"));
        book.setTitle(resultSet.getString("TITLE"));
        // book.setImage(resultSet.getString(columnLabel));
    
        return book;
    };
    
    public String select_books(String fillter){

        return 
            "SELECT b.ID , b.REFRENCE_LINK, b.DESCRIPTION, b.ISBN, b.TITLE,\n"+
            " LISTAGG(b_a.NAME, ',') WITHIN GROUP (ORDER BY b_a.NAME) \"authors\"\n"+
            " from book b\n"+
            " inner join book_authores b_a on b.id = b_a.id\n"+
            fillter +
            " GROUP BY b.ID , b.REFRENCE_LINK, b.DESCRIPTION, b.ISBN, b.TITLE\n";
    }        

    public List<BookDTO> findAllBooks(){

        return this.namedParameterJdbcTemplate.query(
            select_books(""),
            bookRowMapper);
    }

	// @Override
	public List<BookDTO> findBookByAuthor(String author) {

        return this.namedParameterJdbcTemplate.query(
            select_books(" WHERE LOWER(authors) like LOWER(\'%"+author+"%\')")
            , bookRowMapper);
    }

    public List<BookDTO> findBookByTitle(String title) {

        System.out.println(select_books(" WHERE LOWER(b.TITLE) like LOWER(\'%s"+title+"%s\')"));
        return this.namedParameterJdbcTemplate.query(
            select_books(" WHERE LOWER(b.TITLE) like LOWER(\'%"+title+"%\')")
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
}
