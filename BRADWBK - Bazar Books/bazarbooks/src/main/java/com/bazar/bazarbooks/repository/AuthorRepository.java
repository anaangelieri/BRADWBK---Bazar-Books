package com.bazar.bazarbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazar.bazarbooks.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
