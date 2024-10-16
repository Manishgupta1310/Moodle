package com.manish.moodle.repository;

import com.manish.moodle.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long > {
    Optional<Book> findByTitle(String title);

    void deleteByTitle(String title);
}
