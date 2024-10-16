package com.manish.moodle.service;

import com.manish.moodle.entity.Book;
import com.manish.moodle.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    // Read Operation
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    // Read Operation
    public Optional<Book> findByName(String title){
        return  bookRepository.findByTitle(title);
    }


    // Update Operation
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    // Delete Operation
    public void deleteBook(String title){
        bookRepository.deleteByTitle(title);
    }
}
