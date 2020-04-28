package com.serb_backend;

import javax.sql.DataSource;

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
public class Fill_Data {

    private DataSource dataSource;
    
    
    @Autowired
    public Fill_Data(DataSource dataSource){ 
        this.dataSource = dataSource;
    }
    
    private void fillOffers(ArrayList<ClientDTO> clients , ArrayList<BookDTO> books){

        OfferDAO offer_repo = new OfferDAOimp(dataSource);

        for (ClientDTO client : clients) {
            List<BookDTO> offered_books = sampleMultiple(integer(0,10),books);
            for (BookDTO book : offered_books) {
                OfferDTO offer = OfferDTO.random(client, book);
                switch (integer(1, 3)) {
                    case 1:
                        offer_repo.addExchangingOffer(ExchangeDTO.random(offer));
                        break;
                    case 2:
                        offer_repo.addRentingOffer(RentDTO.random(offer));
                        break;
                    case 3:
                        offer_repo.addSellingOffer(SellDTO.random(offer));
                        break;                    
                    default:
                        break;
                }       
            }
        }
    }
    
    private ArrayList<AccountDTO> fill_accounts(int numb){
        /* add acoount to database with 80% admins */
        
        AccountDAOimp acount_repo =new AccountDAOimp(dataSource);

        ArrayList<AccountDTO> accounts= new ArrayList<>();
        for (int i = 0; i < numb; i++) {
            AccountDTO account = AccountDTO.random();
            acount_repo.save(account);
            
            accounts.add(account);       
            if(weighedTrue(.8))
            { acount_repo.saveSuperUser(account.getId());}
        }
        return accounts;
    }
    
    private ArrayList<ClientDTO> fill_clients(int numb){
        AccountDAOimp acount_repo = new AccountDAOimp(dataSource);
        ClientDAOimp client_repo = new ClientDAOimp(dataSource);

        ArrayList<ClientDTO> clients = new ArrayList<>();
        for (int i = 0; i < numb; i++) {
            AccountDTO account = AccountDTO.random();
            acount_repo.save(account);

            ClientDTO client = ClientDTO.random(account);
            client_repo.save(client);

            clients.add(client);
        }
        return clients;
    }


    private ArrayList<BookDTO> fill_books(int numb){
        BookDAOimp book_repo = new BookDAOimp(dataSource);
        ArrayList<BookDTO> books = new ArrayList<>();

        for (int i = 0; i < numb; i++) {
            BookDTO book = BookDTO.random();
            book_repo.addBook(book);

            books.add(book);
        }
        return books;
    }

    public void test_connection(){

        hiDAO hi = new hiDAO(dataSource);
        System.out.println(hi.sayhi()); 
        
    }

    public void fill_all() {

        fill_accounts(10); //as only admins
        System.out.println("admins added");

        ArrayList<ClientDTO> clients = fill_clients(20);
        System.out.println("clients added");

        ArrayList<BookDTO> books =  fill_books(40);
        System.out.println("books added");

        fillOffers(clients, books);
        System.out.println("offers added");
        
        System.out.println("fill all finished");
    }
}