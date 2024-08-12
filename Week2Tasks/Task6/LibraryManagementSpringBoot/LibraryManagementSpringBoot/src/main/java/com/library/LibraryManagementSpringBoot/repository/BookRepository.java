package com.library.LibraryManagementSpringBoot.repository;

import com.library.LibraryManagementSpringBoot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
