package com.masai.assignment.RestfulWebService.service;

import com.masai.assignment.RestfulWebService.Exception.BookNotFound;
import com.masai.assignment.RestfulWebService.Exception.UpdateFailed;
import com.masai.assignment.RestfulWebService.entity.Book;
import com.masai.assignment.RestfulWebService.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return  bookRepository.findAll();
    }

    public Book getBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFound("Book Not Found");
        }
        return book.get();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public String updateBook(Book book) {
        Optional<Book> book1 = bookRepository.findById(book.getBookID());
        if(book1.isEmpty()){
            throw new BookNotFound("Book Not Found");
        }
        try{
            book1.get().setBookAuthor(book.getBookAuthor());
            book1.get().setBookName(book.getBookName());
            book1.get().setBookCategory(book.getBookCategory());
            book1.get().setBookPublication(book.getBookPublication());
            book1.get().setBookPages(book.getBookPages());
            book1.get().setBookPrice(book.getBookPrice());
            book1.get().setBook_registration_no(book.getBook_registration_no());
            book1.get().setBook_author_no(book.getBook_author_no());
            bookRepository.save(book1.get());
            return "Update Successfully";
        }catch (Exception ex){
            throw new UpdateFailed("Update Failed");
        }

    }

    public boolean deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        try{
            bookRepository.delete(book);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public List<Book> getAllBooksForReader() {
        return bookRepository.findAll();
    }


}
