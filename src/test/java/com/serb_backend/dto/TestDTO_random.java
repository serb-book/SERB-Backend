package com.serb_backend.dto;

import org.junit.jupiter.api.Test;

/**
 * TestDTO_random
 */
public class TestDTO_random {

    @Test
    public void test_exceptions(){
        System.out.println("------------------------");
        System.out.println(AccountDTO.random());
        System.out.println("------------------------");
        System.out.println(Address.random());
        System.out.println("------------------------");
        System.out.println(BookDTO.random());
        System.out.println("------------------------");
        System.out.println(ClientDTO.random());
        System.out.println("------------------------");
        System.out.println(ClientDTO.random(AccountDTO.random()));
        System.out.println("------------------------");
        System.out.println(OfferDTO.random());
        System.out.println("------------------------");
        System.out.println(ExchangeDTO.random(OfferDTO.random()));
        System.out.println("------------------------");
        System.out.println(RentDTO.random(OfferDTO.random()));
        System.out.println("------------------------");
        System.out.println(SellDTO.random(OfferDTO.random()));
    }
}