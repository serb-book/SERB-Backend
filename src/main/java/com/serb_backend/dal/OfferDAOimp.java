package com.serb_backend.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serb_backend.dto.BookDTO;
import com.serb_backend.dto.ClientDTO;
import com.serb_backend.dto.ExchangeDTO;
import com.serb_backend.dto.OfferDTO;
import com.serb_backend.dto.RentDTO;
import com.serb_backend.dto.SellDTO;
import com.serb_backend.dto.State;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class OfferDAOimp implements OfferDAO {


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OfferDAOimp(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    @Override
    public void addOffer(OfferDTO offer,int type){
        Map<String, Object> OfferParameters =new ObjectMapper().convertValue(offer, Map.class);
        
        OfferParameters.put("clientID", offer.getClient().getId());
        OfferParameters.put("bookID", offer.getBook().getId());
        OfferParameters.put("state_text", offer.getState().getText());
        OfferParameters.put("type", type);

        

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO OFFER VALUES (:id, :type, NULL, :clientID, :bookID, :state_text)"
            , OfferParameters);

    }

    @Override
    public boolean addExchangingOffer(ExchangeDTO exchangeOffer) {
        Map<String, Object> exchangeOfferParameters =new ObjectMapper().convertValue(exchangeOffer, Map.class);
        exchangeOfferParameters.put("id", exchangeOffer.getOffer().getId());

        addOffer(exchangeOffer.getOffer(), OfferDTO.type.exchange.ordinal()); //exchange type is 1 commented at database
        
        this.namedParameterJdbcTemplate.update(
            "INSERT INTO OFFER_EXCHANGE VALUES (:id, :negotiationPrice)", exchangeOfferParameters);
        
        // TODO implement the other cases
        int intersetsLength= exchangeOffer.getInterests().size();
        SqlParameterSource idNamedParameter[] = new SqlParameterSource[intersetsLength];
        for (int i = 0 ; i< intersetsLength; i++ ) {
        	Map<String, Long> parameter = new HashedMap<String, Long>();
        	parameter.put("offer_id", exchangeOffer.getOffer().getId());
        	parameter.put("book_id", exchangeOffer.getInterests().get(i).getId());
        	idNamedParameter[i] = new MapSqlParameterSource(parameter);
        }
        this.namedParameterJdbcTemplate.batchUpdate(
        		"INSERT INTO OFFER_EXCHANGE_INTEREST_INBOOK VALUES(:book_id,:offer_id)",
        		idNamedParameter);
        return true;
    }

    @Override
    public boolean addOffersFromExcelFile(XSSFWorkbook excelFile) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addRentingOffer(RentDTO rentingOffer) {
        addOffer(rentingOffer.getOffer(), OfferDTO.type.rent.ordinal()); //rent type is 2 commented at database

        Map<String, Object> rentingOfferParameters =new ObjectMapper().convertValue(rentingOffer, Map.class);
        rentingOfferParameters.put("id", rentingOffer.getOffer().getId());

        MapSqlParameterSource namedParameters = new MapSqlParameterSource(rentingOfferParameters);
        namedParameters.addValue("endDate", rentingOffer.getEndingDate()); //use sqlmap to fix datatypes

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO OFFER_RENT VALUES (:pricePerDay, :endDate, :id)", namedParameters);
        

        return true;
    }

    @Override
    public boolean addSellingOffer(SellDTO sellingOffer) {
        addOffer(sellingOffer.getOffer(), OfferDTO.type.sell.ordinal()); //sell type is 0 commented at database

        Map<String, Object> sellingOfferParameters =new ObjectMapper().convertValue(sellingOffer, Map.class);
        sellingOfferParameters.put("id", sellingOffer.getOffer().getId());

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO OFFER_SELL VALUES (:id, :price)", sellingOfferParameters);

        return false;
    }
    
    
    private final RowMapper<ExchangeDTO> exchangeRowMapper = (resultSet, rowNum) -> {
        ExchangeDTO exchange = new ExchangeDTO();
        
        OfferDTO offer = offerRowMapper(resultSet,rowNum);
        exchange.setOffer(offer);

        exchange.setNegotiationPrice(resultSet.getLong("price"));

        return exchange;
    };

    private final RowMapper<SellDTO> sellRowMapper = (resultSet, rowNum) -> {
        SellDTO sell = new SellDTO();
        
        OfferDTO offer = offerRowMapper(resultSet,rowNum);
        sell.setOffer(offer);

        sell.setPrice(resultSet.getLong("price"));

        return sell;
    };

    private final RowMapper<RentDTO> rentRowMapper = (resultSet, rowNum) -> {
        RentDTO rent = new RentDTO();
        
        OfferDTO offer = offerRowMapper(resultSet,rowNum);
        rent.setOffer(offer);

        rent.setPricePerDay(resultSet.getLong("price"));

        return rent;
    };

    private final OfferDTO offerRowMapper(ResultSet resultSet,Integer rowNum)
    throws SQLException{
        /*
            ID
            TYPE
            CLIENT_ID
            BOOK_ID
            STATE_TEXT
            OFFER_END_TIME
            PRICE
            MAXIMUM_RENT_TIME
        */

        OfferDTO offer = new OfferDTO();

        // set offer attributes
        offer.setId(resultSet.getLong("ID"));

        ClientDTO client = new ClientDTO();
        client.setId(resultSet.getLong("client_id"));
        //NOTE should i add client data
        offer.setClient(client);
        
        State state = new State();
        state.setText(resultSet.getString("STATE_TEXT"));
        offer.setState(state);

        // offer.setAvailable(available); //TODO check end date

        BookDTO book = new BookDTO();
        book.setId(resultSet.getLong("book_id"));
        offer.setBook(book);

        return offer;
    };
    

    public Map<String,Object> getOffersByBookId(long id){
        Map<String,Object> offers = new HashMap<String,Object>();

        
        String sql = "select * from offer_full where book_id = :book_id and type = :type";
        
        for (OfferDTO.type type : OfferDTO.type.values()) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource("book_id", id);
            namedParameters.addValue("type", type.ordinal());
            switch (type) {
                case exchange:
                    List<ExchangeDTO> exchange = this.namedParameterJdbcTemplate.query(sql, namedParameters, exchangeRowMapper);
                    offers.put("exchange",exchange);
                case sell:
                    List<SellDTO> sell = this.namedParameterJdbcTemplate.query(sql, namedParameters, sellRowMapper);
                    offers.put("sell",sell);
                case rent:
                    List<RentDTO> rent = this.namedParameterJdbcTemplate.query(sql, namedParameters, rentRowMapper);
                    offers.put("rent",rent);
                default:
                    break;
            }   
        }
        return offers;
    }


    public Object findOfferById(long id){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        int type_num = this.namedParameterJdbcTemplate.queryForObject(
                    "select type from offer where id = :id", namedParameters, int.class);
        
        OfferDTO.type type = OfferDTO.type.values()[type_num];
        namedParameters.addValue("type", type.ordinal());
         
        String sql = "select * from offer_full where id = :id and type = :type";

        if (type == OfferDTO.type.exchange)
        {
            return this.namedParameterJdbcTemplate.queryForObject(
            sql ,namedParameters ,exchangeRowMapper); 
        }
        
        if (type == OfferDTO.type.sell){
            return this.namedParameterJdbcTemplate.queryForObject(
            sql ,namedParameters ,sellRowMapper); 
        }
    
        if (type == OfferDTO.type.rent){
            return this.namedParameterJdbcTemplate.queryForObject(
            sql ,namedParameters ,rentRowMapper); 
        }
        else 
            return null;
        
    }

}