package com.bazar.bazarbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazar.bazarbooks.model.Author;
import com.bazar.bazarbooks.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author putAuthor(Author author) {
        return authorRepository.save(author);
    }

    public boolean deleteAuthor(int id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateAuthor(int id, Author updatedAuthor) {
        if (authorRepository.existsById(id)) {
            updatedAuthor.setIdAuthor(id);
            authorRepository.save(updatedAuthor);
            return true;
        }
        return false;
    }
}
