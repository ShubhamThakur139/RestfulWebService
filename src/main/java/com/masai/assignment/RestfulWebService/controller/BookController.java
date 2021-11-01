package com.masai.assignment.RestfulWebService.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.masai.assignment.RestfulWebService.dto.BookDto;
import com.masai.assignment.RestfulWebService.entity.Book;
import com.masai.assignment.RestfulWebService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BookController {

    @Autowired
    BookService bookService;

    //Author Api Endpoints

    @GetMapping("/author/books")
    public ResponseEntity<MappingJacksonValue>  getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book:books){
            bookDtos.add(new BookDto(book));
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
        FilterProvider filters = new SimpleFilterProvider().addFilter("BookFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(bookDtos);
        mapping.setFilters(filters);

        return new ResponseEntity<>(mapping, HttpStatus.OK);
    }

    @GetMapping("/author/books/{id}")
    public ResponseEntity<MappingJacksonValue> getBook(@PathVariable("id") Long bookId) {
        Book book = bookService.getBook(bookId);
        BookDto bookDto = new BookDto(book);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
        FilterProvider filters = new SimpleFilterProvider().addFilter("BookFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(bookDto);
        mapping.setFilters(filters);
        return new ResponseEntity<>(mapping,HttpStatus.OK);
    }

    @PostMapping("/author/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book),HttpStatus.OK);
    }

    @PutMapping("/author/book")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(book),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/author/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long bookId) {
        boolean flag = bookService.deleteBook(bookId);
        if (flag) return  new ResponseEntity<>("delete Successfully",HttpStatus.OK) ;
        else return new ResponseEntity<>("delete Failed", HttpStatus.BAD_REQUEST);
    }

    //Reader Api Endpoints

    @GetMapping("/reader/books/{id}")
    public ResponseEntity<MappingJacksonValue> getAllBookForReader(@PathVariable("id") Long bookId) {
        Book book = bookService.getBook(bookId);
        BookDto bookDto = new BookDto(book);
//        1. To decide which values you want
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("bookName", "bookAuthor", "bookPublication", "bookCategory", "bookPages", "bookPrice");

//        2. Configuring Filter Provider
        FilterProvider filters = new SimpleFilterProvider().addFilter("BookFilter", filter);

//        3. Creating Object and Applying filter
        MappingJacksonValue mapping = new MappingJacksonValue(bookDto);
        mapping.setFilters(filters);
        return new ResponseEntity<>(mapping,HttpStatus.OK);
    }

    @GetMapping("/reader/books")
    public  ResponseEntity<MappingJacksonValue> getAllBooksForReader() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book:books){
            bookDtos.add(new BookDto(book));
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("bookName", "bookAuthor", "bookPublication", "bookCategory", "bookPages", "bookPrice");
        FilterProvider filters = new SimpleFilterProvider().addFilter("BookFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(bookDtos);
        mapping.setFilters(filters);
        return new ResponseEntity<>(mapping,HttpStatus.OK);
    }

}
