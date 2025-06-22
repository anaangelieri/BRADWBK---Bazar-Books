package com.bazar.bazarbooks.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String id) {
        super("Carrinho não encontrado com id: " + id);
    }

    public CartNotFoundException() {
        super("Carrinho não encontrado.");
    }
}
