package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class MainApp {

    public static void main(String[] args) {
        // Load the application context from the XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent.xml");

        // Retrieve the BookService bean from the application context
        BookService bookService = (BookService) context.getBean("bookService");


        // Call the service method
        bookService.findBooks();
    }
}
