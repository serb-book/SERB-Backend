package com.serb_backend;

import javax.sql.DataSource;

import com.serb_backend.dal.AccountDAOimp;
import com.serb_backend.dal.BookDAOimp;
import com.serb_backend.dal.ClientDAOimp;
import com.serb_backend.dal.hiDAO;
import com.serb_backend.dto.AccountDTO;
import com.serb_backend.dto.BookDTO;
import com.serb_backend.dto.ClientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class Fill_Data {

    private DataSource dataSource;
    
    
    @Autowired
    public Fill_Data(DataSource dataSource){ 
        this.dataSource = dataSource;
    }
    private void fill_accounts(int numb){
        
        AccountDAOimp acount_repo =new AccountDAOimp(dataSource);

        for (int i = 1; i < numb; i++) {
            AccountDTO account = AccountDTO.random();
            acount_repo.save(account);

        }
    }
    
    private void fill_clients(int numb){
        AccountDAOimp acount_repo = new AccountDAOimp(dataSource);
        ClientDAOimp client_repo = new ClientDAOimp(dataSource);


        for (int i = 1; i < numb; i++) {
            AccountDTO account = AccountDTO.random();
            acount_repo.save(account);

            ClientDTO client = ClientDTO.random(account);
            client_repo.save(client);

        }
    }


    private void fill_books(int numb){
        BookDAOimp book_repo = new BookDAOimp(dataSource);

        for (int i = 1; i < numb; i++) {
            BookDTO book = BookDTO.random();
            book_repo.addBook(book);
        }
    }

    public void test_connection(){

        hiDAO hi = new hiDAO(dataSource);
        System.out.println(hi.sayhi()); 
        
    }

    public void fill_all() {


    }
}