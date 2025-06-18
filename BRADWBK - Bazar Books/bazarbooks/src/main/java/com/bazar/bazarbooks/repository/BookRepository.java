package com.bazar.bazarbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bazar.bazarbooks.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
