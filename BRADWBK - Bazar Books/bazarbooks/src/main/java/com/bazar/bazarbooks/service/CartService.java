package com.bazar.bazarbooks.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazar.bazarbooks.exception.CartNotFoundException;
import com.bazar.bazarbooks.model.Cart;
import com.bazar.bazarbooks.model.User;
import com.bazar.bazarbooks.repository.CartRepository;
import com.bazar.bazarbooks.repository.UserRepository;

import jakarta.transaction.Transactional;

   @Service
    public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    // Método para buscar usuário e validar sua existência
    public User validateAndGetUser(int userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CartNotFoundException("Usuário com ID " + String.valueOf(userId) + " não encontrado"));
    }

    // Método para buscar carrinho por usuário
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user)
            .orElseThrow(() -> new CartNotFoundException("Carrinho não encontrado para o usuário: " + user.getIdUser()));
    }

    public Cart createCartIfNotExists(int userId) {
        User user = validateAndGetUser(userId);

        return cartRepository.findByUser(user)
            .orElseGet(() -> {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setItems(new ArrayList<>());
                return cartRepository.save(newCart);
            });
    }

    public Cart getCartByUserId(int userId) {
        User user = validateAndGetUser(userId);
        return cartRepository.findByUser(user)
            .orElseThrow(() -> new CartNotFoundException("Carrinho não encontrado para o usuário: " + userId));
    }

    public Cart updateCart(Integer id, Cart newCart) {
        Cart existing = cartRepository.findById(id)
            .orElseThrow(() -> new CartNotFoundException("Carrinho com ID " + id + " não encontrado"));

        existing.setItems(newCart.getItems());
        return cartRepository.save(existing);
    }

    @Transactional
    public void deleteCart(Integer id) {
        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new CartNotFoundException("Carrinho com ID " + id + " não encontrado"));

        cartRepository.delete(cart);
    }
}