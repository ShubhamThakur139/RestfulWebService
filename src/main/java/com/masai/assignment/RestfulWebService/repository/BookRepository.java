package com.masai.assignment.RestfulWebService.repository;

import com.masai.assignment.RestfulWebService.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
