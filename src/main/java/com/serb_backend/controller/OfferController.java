package com.serb_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serb_backend.dal.OfferDAOimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {
    
    
    private OfferDAOimp offer_repo;
    private ObjectMapper objectMapper;


    @Autowired
    OfferController(OfferDAOimp offer_repo){
        this.offer_repo = offer_repo;
        this.objectMapper = new ObjectMapper();
    }
   
    
    
    
    @GetMapping("/id/{id}")
    public String findOfferById(@PathVariable long id)
    throws JsonProcessingException{
        
        return objectMapper.writeValueAsString(offer_repo.findOfferById(id));
    }


    @GetMapping("/book_id/{id}")
    public String findOfferByBookId(@PathVariable long id)
    throws JsonProcessingException{
        
        return objectMapper.writeValueAsString(offer_repo.getOffersByBookId(id));
    }
}