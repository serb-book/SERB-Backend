package com.serb_backend;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.serb_backend.dal.AccountDAOimp;
import com.serb_backend.dal.BookDAOimp;
import com.serb_backend.dal.hiDAO;
import com.serb_backend.dal.util.ConnectionProvider;
import com.serb_backend.dal.util.ConnectionProviderImpl;
import com.serb_backend.dto.AccountDTO;
import com.serb_backend.dto.BookDTO;


public class fill_Data {

    private static DataSource dataSource;

    private static void fill_accounts(int numb){
        AccountDAOimp acount_repo = new AccountDAOimp(dataSource);

        for (int i = 1; i < numb; i++) {
            AccountDTO account = AccountDTO.random();
            acount_repo.save(account);

        }
    }
    private static void fill_books(int numb){
        BookDAOimp book_repo = new BookDAOimp(dataSource);

        for (int i = 1; i < numb; i++) {
            BookDTO book = BookDTO.random();
            book_repo.addBook(book);

        }
    }


    public static void main(String[] args) throws SQLException {

        ConnectionProvider prov = new ConnectionProviderImpl();
        // ConnectionProvider prov = new ConnectionProviderWithPoolImpl();
        dataSource = prov.getDataSource("jdbc:oracle:thin:@localhost:49161:xe", "book", "book", 6);
        
        // test connection
        hiDAO hi = new hiDAO(dataSource);
        System.out.println(hi.sayhi());

        fill_accounts(20);
        System.out.println("accounts added");

        fill_books(40);
        System.out.println("books added");
    }
}