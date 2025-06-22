package com.bazar.bazarbooks.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Integer itemId) {
        super("Item não encontrado com id: " + itemId);
    }
}