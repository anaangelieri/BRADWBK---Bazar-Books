package com.bazar.bazarbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazar.bazarbooks.model.Book;
import com.bazar.bazarbooks.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean updateBook(int id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setIdBook(id);
            bookRepository.save(updatedBook);
            return true;
        }
        return false;
    }

    public boolean deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
