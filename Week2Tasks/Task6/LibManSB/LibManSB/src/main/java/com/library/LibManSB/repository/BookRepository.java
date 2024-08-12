package com.library.LibManSB.repository;

import com.library.LibManSB.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
