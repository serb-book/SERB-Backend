package com.serb_backend.controller;
// package com.springboot.security.api.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serb_backend.dal.BookDAOimp;
import com.serb_backend.dto.BookDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookController {
    
    
    private BookDAOimp book_repo;


    @Autowired
    BookController(BookDAOimp book_repo){
        this.book_repo = book_repo;
    }
    
    @GetMapping("/books")
    public String getAllBooks() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        List<BookDTO> books = book_repo.findAllBooks();
        return om.writeValueAsString(books);  
        
    }

    @GetMapping("/book/author/{author_name}")
    public String getBookByAuthor(@PathVariable String author_name)
    throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
        List<BookDTO> books = book_repo.findBookByAuthor(author_name);
        return om.writeValueAsString(books);  
    }

    @GetMapping("/book/title/{title}")
    public String getBookByTitle(@PathVariable String title)
    throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        List<BookDTO> books = book_repo.findBookByTitle(title);
        return om.writeValueAsString(books);  
    }

}