package com.bazar.bazarbooks.repository;

import java.util.Optional;

import com.bazar.bazarbooks.model.Cart;
import com.bazar.bazarbooks.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);
    // Resttante dos métodos CRUD já estão implementados pelo JpaRepository
}