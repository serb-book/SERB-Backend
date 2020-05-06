package com.serb_backend;

import javax.sql.DataSource;

import com.serb_backend.dal.AccountDAO;
import com.serb_backend.dal.AccountDAOimp;
import com.serb_backend.dal.BookDAOimp;
import com.serb_backend.dal.ClientDAOimp;
import com.serb_backend.dal.OfferDAO;
import com.serb_backend.dal.OfferDAOimp;
import com.serb_backend.dal.hiDAO;
import com.serb_backend.dto.AccountDTO;
import com.serb_backend.dto.BookDTO;
import com.serb_backend.dto.ClientDTO;
import com.serb_backend.dto.ExchangeDTO;
import com.serb_backend.dto.OfferDTO;
import com.serb_backend.dto.RentDTO;
import com.serb_backend.dto.SellDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.qala.datagen.RandomShortApi.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class FillData {

    private DataSource dataSource;
    
    
    @Autowired
    public FillData(DataSource dataSource){ 
        this.dataSource = dataSource;
    }
    
    private void fillOffers(ArrayList<ClientDTO> clients , ArrayList<BookDTO> books){

        OfferDAO offerRepo = new OfferDAOimp(dataSource);

        for (ClientDTO client : clients) {
            List<BookDTO> offeredBooks = sampleMultiple(integer(0,10),books);
            for (BookDTO book : offeredBooks) {
                OfferDTO offer = OfferDTO.random(client, book);
                switch (integer(1, 3)) {
                    case 1:
                        offerRepo.addExchangingOffer(ExchangeDTO.random(offer));
                        break;
                    case 2:
                        offerRepo.addRentingOffer(RentDTO.random(offer));
                        break;
                    case 3:
                        offerRepo.addSellingOffer(SellDTO.random(offer));
                        break;                    
                    default:
                        break;
                }       
            }
        }
    }
    
    private ArrayList<AccountDTO> fillAccounts(int numb){
        
        AccountDAO acountRepo =new AccountDAOimp(dataSource);

        ArrayList<AccountDTO> accounts= new ArrayList<>();
        for (int i = 0; i < numb; i++) {
            AccountDTO account = AccountDTO.random();
            acountRepo.saveAccount(account);
            
            accounts.add(account);       
            if(weighedTrue(.8))
            { acountRepo.grantSuperUserPriviliges(account.getId());}
        }
        return accounts;
    }
    
    private ArrayList<ClientDTO> fillClients(int numb){
        AccountDAOimp acountRepo = new AccountDAOimp(dataSource);
        ClientDAOimp clientRepo = new ClientDAOimp(dataSource);

        ArrayList<ClientDTO> clients = new ArrayList<>();
        for (int i = 0; i < numb; i++) {
            AccountDTO account = AccountDTO.random();
            acountRepo.saveAccount(account);

            ClientDTO client = ClientDTO.random(account);
            clientRepo.save(client);

            clients.add(client);
        }
        return clients;
    }


    private ArrayList<BookDTO> fillBooks(int numb){
        BookDAOimp bookRepo = new BookDAOimp(dataSource);
        ArrayList<BookDTO> books = new ArrayList<>();

        for (int i = 0; i < numb; i++) {
            BookDTO book = BookDTO.random();
            bookRepo.addBook(book);

            books.add(book);
        }
        return books;
    }

    public void testConnection(){

        hiDAO hi = new hiDAO(dataSource);
        System.out.println(hi.sayhi()); 
        
    }

    public void fillAll() {

        fillAccounts(10); //as only admins
        System.out.println("admins added");

        ArrayList<ClientDTO> clients = fillClients(20);
        System.out.println("clients added");

        ArrayList<BookDTO> books =  fillBooks(40);
        System.out.println("books added");

        fillOffers(clients, books);
        System.out.println("offers added");
        
        System.out.println("fill all finished");
    }
}