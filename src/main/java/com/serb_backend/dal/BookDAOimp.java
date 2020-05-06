package com.serb_backend.dal;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<String> authorsList = new ArrayList<String>();
        for (String author : authors) {
            authorsList.add(author);
        }
        book.setAuthors(authorsList);
        
        book.setISBN(resultSet.getString("ISBN"));
        book.setTitle(resultSet.getString("TITLE"));

        
        
        //offers 
        //TODO add rest of offer properties
        String offers_id_list[] = resultSet.getString("offer_id").split(",");
        String offers_type_list[] = resultSet.getString("offer_type").split(",");
        String offers_price_list[] = resultSet.getString("offer_price").split(",");

        ArrayList<RentDTO> rent_offers = new ArrayList<RentDTO>();
        ArrayList<ExchangeDTO> exchange_offers = new ArrayList<ExchangeDTO>();
        ArrayList<SellDTO> sell_offers = new ArrayList<SellDTO>();
        
        for (int i = 0; i < offers_id_list.length; i++) {
            try {
                Long offer_id = Long.parseLong(offers_id_list[i]);
                Integer offer_type = Integer.parseInt(offers_type_list[i]);
                Integer offer_price = Integer.parseInt(offers_type_list[i]);

                OfferDTO offer = new OfferDTO();
                offer.setId(offer_id);
                // offer.setState(state);

                switch (offer_type){
                    case 0: //sell
                        SellDTO sellDTO = new SellDTO();
                        sellDTO.setOffer(offer);
                        sell_offers.add(sellDTO);        
                        break;
                    case 1: //exchange
                        ExchangeDTO exchangeDTO = new ExchangeDTO();
                        exchangeDTO.setOffer(offer);
                        exchange_offers.add(exchangeDTO);
                        break;
                    case 2: //rent
                        RentDTO rentDTO = new RentDTO();
                        rentDTO.setOffer(offer);
                        rent_offers.add(rentDTO);
                        break;
                    default:
                        break;
                    }
            } catch (Exception e) {
                //TODO: handle exception
                // offer_type not valid only accept 0,1,2
                // offer doesn't exist offer_type null
            }
        }
        book.setRent_offers(rent_offers);
        book.setExchange_offers(exchange_offers);
        book.setSell_offers(sell_offers);

        



        // book.setImage(resultSet.getString(columnLabel));
    
        return book;
    };
    
    public String select_books_query(String fillter){
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
            select_books_query(""),
            bookRowMapper);
    }

	// @Override
	public List<BookDTO> findBookByAuthor(String author) {

        return this.namedParameterJdbcTemplate.query(
            select_books_query(" WHERE LOWER(authors) like LOWER(\'%"+author+"%\')")
            , bookRowMapper);
    }

    public List<BookDTO> findBookByTitle(String title) {

        return this.namedParameterJdbcTemplate.query(
            select_books_query(" WHERE LOWER(TITLE) like LOWER(\'%"+title+"%\')")
            , bookRowMapper);
    }

    public List<BookDTO> findBookByTitleAndAuthor(String title,String author) {

        return this.namedParameterJdbcTemplate.query(
            select_books_query(" WHERE LOWER(TITLE) like LOWER(\'%"+title+"%\') and LOWER(authors) like LOWER(\'%"+author+"%\')")
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
            select_books_query("where id = :id"), namedParameters,  bookRowMapper);
    }

    public BookDTO findBookByIsbn(String isbn){
        SqlParameterSource namedParameters = new MapSqlParameterSource("isbn", isbn);

        return this.namedParameterJdbcTemplate.queryForObject(
            select_books_query("where isbn = :isbn"), namedParameters,  bookRowMapper);
    }
    
}
