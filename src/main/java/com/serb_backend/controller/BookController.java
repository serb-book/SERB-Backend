package com.serb_backend.controller;
// package com.springboot.security.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serb_backend.dal.BookDAOimp;
import com.serb_backend.dto.BookDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    
    
    private BookDAOimp book_repo;
    private ObjectMapper objectMapper;


    @Autowired
    BookController(BookDAOimp book_repo){
        this.book_repo = book_repo;
        this.objectMapper = new ObjectMapper();
    }
    
    @GetMapping("/all")
    public String getAllBooks() throws JsonProcessingException {
        List<BookDTO> books = book_repo.findAllBooks();
        return objectMapper.writeValueAsString(books);  
        
    }


    @GetMapping("/search")
    public String getBooksByTitleOrAuthor(@RequestParam(required = false) String title,@RequestParam(required = false) String author)
    throws JsonProcessingException {
        
        List<BookDTO> books = new ArrayList<BookDTO>();
        
        if(title != null && author == null)
            books.addAll(book_repo.findBookByTitle(title));
        else if(author != null && title == null)
            books.addAll(book_repo.findBookByAuthor(author));
        else if(author != null && title != null)
            books.addAll(book_repo.findBookByTitleAndAuthor(title, author));
        
        return objectMapper.writeValueAsString(books);  
    }

    @GetMapping("/id/{book_id}")
    public String findBookById(@PathVariable Long book_id) throws JsonProcessingException{
        return objectMapper.writeValueAsString(book_repo.findBookByID(book_id));
    }
    
    @GetMapping("/isbn/{book_isbn}")
    public String findBookByIsbn(@PathVariable String book_isbn) throws JsonProcessingException{
        return objectMapper.writeValueAsString(book_repo.findBookByIsbn(book_isbn));
    }
}