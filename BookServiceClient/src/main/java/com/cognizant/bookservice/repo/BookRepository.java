package com.cognizant.bookservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.bookservice.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
