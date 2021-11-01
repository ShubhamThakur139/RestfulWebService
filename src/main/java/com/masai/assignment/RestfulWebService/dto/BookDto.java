package com.masai.assignment.RestfulWebService.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.masai.assignment.RestfulWebService.entity.Book;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonFilter("BookFilter")
public class BookDto {

    private long bookID;
    private String bookName;
    private String bookAuthor;
    private String bookPublication;
    private String bookCategory;
    private int bookPages;
    private int bookPrice;
    private int book_registration_no;
    private int book_author_no;

    public BookDto(Book book){
        this.bookID = book.getBookID();
        this.bookName = book.getBookName();
        this.bookAuthor = book.getBookAuthor();
        this.bookPublication = book.getBookPublication();
        this.bookCategory = book.getBookCategory();
        this.bookPrice = book.getBookPrice();
        this.bookPages = book.getBookPages();
        this.book_registration_no = book.getBook_registration_no();
        this.book_author_no = book.getBook_author_no();
    }

}
