package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    // Repository methods for interacting with the database

    public void getAllBooks() {
        System.out.println("Fetching all books from the repository...");
    }
}
