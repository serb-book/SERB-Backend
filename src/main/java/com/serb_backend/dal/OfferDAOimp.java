package com.serb_backend.dal;

import java.util.Map;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serb_backend.dto.ExchangeDTO;
import com.serb_backend.dto.OfferDTO;
import com.serb_backend.dto.RentDTO;
import com.serb_backend.dto.SellDTO;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OfferDAOimp implements OfferDAO {


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OfferDAOimp(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }


    public void addOffer(OfferDTO offer,int type){
        Map<String, Object> OfferParameters =new ObjectMapper().convertValue(offer, Map.class);
        
        OfferParameters.put("clientID", offer.getClient().getId());
        OfferParameters.put("bookID", offer.getBook().getId());
        OfferParameters.put("state_text", offer.getState().getText());
        OfferParameters.put("type", type);

        

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO OFFER VALUES (:id, NULL, :type, NULL, :clientID, :bookID, :state_text)"
            , OfferParameters);

    }

    @Override
    public boolean addExchangingOffer(ExchangeDTO exchangeOffer) {
        Map<String, Object> exchangeOfferParameters =new ObjectMapper().convertValue(exchangeOffer, Map.class);
        exchangeOfferParameters.put("id", exchangeOffer.getOffer().getId());

        addOffer(exchangeOffer.getOffer(), 1); //exchange type is 1 commented at database
        
        this.namedParameterJdbcTemplate.update(
            "INSERT INTO OFFER_EXCHANGE VALUES (:id, :negotiationPrice)", exchangeOfferParameters);
        
        //  TODO add interstes
        return true;
    }

    @Override
    public boolean addOffersFromExcelFile(XSSFWorkbook excelFile) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addRentingOffer(RentDTO rentingOffer) {
        addOffer(rentingOffer.getOffer(), 2); //rent type is 2 commented at database

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
        addOffer(sellingOffer.getOffer(), 0); //sell type is 0 commented at database

        Map<String, Object> sellingOfferParameters =new ObjectMapper().convertValue(sellingOffer, Map.class);
        sellingOfferParameters.put("id", sellingOffer.getOffer().getId());

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO OFFER_SELL VALUES (:id, :price)", sellingOfferParameters);

        return false;
    }

}