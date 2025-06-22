package com.bazar.bazarbooks.repository;

import com.bazar.bazarbooks.model.Cart;
import com.bazar.bazarbooks.model.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartId(Integer cartId);
    Optional<CartItem> findByIdAndCart(Integer id, Cart cart);
    // Restante dos métodos CRUD já estão implementados pelo JpaRepository
}
