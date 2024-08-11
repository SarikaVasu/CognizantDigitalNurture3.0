package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void findBooks() {
        System.out.println("Finding books....");
        bookRepository.getAllBooks();
    }

    public void performService() {
        System.out.println("Performing service...");
        bookRepository.performRepositoryTask();
    }

}
