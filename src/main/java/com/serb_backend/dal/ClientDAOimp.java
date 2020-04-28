package com.serb_backend.dal;

import java.util.Map;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serb_backend.dto.Address;
import com.serb_backend.dto.ClientDTO;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOimp {
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientDAOimp(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }


    public void saveAddress(Long id,Address address){
        Map<String, Object> addressParameters =new ObjectMapper().convertValue(address, Map.class);

        addressParameters.put("id", id);

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO CLIENT_ADDRESS VALUES (:city, :government, :street, :country, :longitude, :latitude, :id)"
            ,addressParameters);
        }
    

    public void save(ClientDTO client){
        
  
        Map<String, Object> client_parameters =new ObjectMapper().convertValue(client, Map.class);
  
        this.namedParameterJdbcTemplate.update(
            "INSERT INTO CLIENT VALUES (:fullname , :ssn, :id)"
            , client_parameters);
        
        if (client.getAddress() != null){
            saveAddress(client.getId(), client.getAddress());
        }
        }
}