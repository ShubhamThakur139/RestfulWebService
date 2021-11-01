package com.masai.assignment.RestfulWebService.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    private long bookID;
    private String bookName;
    private String bookAuthor;
    private String bookPublication;
    private String bookCategory;
    private int bookPages;
    private int bookPrice;
    private int book_registration_no;
    private int book_author_no;
}
