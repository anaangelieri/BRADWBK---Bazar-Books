package com.bazar.bazarbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazar.bazarbooks.exception.CartItemNotFoundException;
import com.bazar.bazarbooks.exception.CartNotFoundException;
import com.bazar.bazarbooks.model.Cart;
import com.bazar.bazarbooks.model.CartItem;
import com.bazar.bazarbooks.repository.CartItemRepository;
import com.bazar.bazarbooks.repository.CartRepository;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    // Método auxiliar para obter o carrinho ou lançar uma exceção se não existir	
    private Cart getCartOrThrow(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(String.valueOf(cartId)));
    }

    public List<CartItem> getItemsByCartId(Integer cartId) {
         @SuppressWarnings("unused")
         Cart cart = getCartOrThrow(cartId);

        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem getItem(Integer cartId, Integer itemId) {
        Cart cart = getCartOrThrow(cartId);

        return cartItemRepository.findByIdAndCart(itemId, cart)
                .orElseThrow(() -> new CartItemNotFoundException(itemId));
    }

    public CartItem addOrUpdateItem(Integer cartId, CartItem newItem) {
        Cart cart = getCartOrThrow(cartId);

        // Se o item tiver id, tenta buscar o item existente
        if (newItem.getId() != null) {
            CartItem existingItem = cartItemRepository.findByIdAndCart(newItem.getId(), cart)
                    .orElse(null);

            if (existingItem != null) {
                existingItem.setQuantity(newItem.getQuantity());
                existingItem.setUnitPrice(newItem.getUnitPrice());
                
                return cartItemRepository.save(existingItem);
            }
        }

        // Se não existe, cria um novo
        newItem.setCart(cart);
        return cartItemRepository.save(newItem);
    }

    public CartItem updateQuantity(Integer cartId, Integer itemId, Integer newQuantity) {
        Cart cart = getCartOrThrow(cartId);

        CartItem item = cartItemRepository.findByIdAndCart(itemId, cart)
                .orElseThrow(() -> new CartItemNotFoundException(itemId));

        item.setQuantity(newQuantity);
        return cartItemRepository.save(item);
    }


    public void deleteItem(Integer cartId, Integer itemId) {
        Cart cart = getCartOrThrow(cartId);
        
        CartItem item = cartItemRepository.findByIdAndCart(itemId, cart)
                .orElseThrow(() -> new CartItemNotFoundException(itemId));
        
        cartItemRepository.delete(item);
    }

    public void clearCart(Integer cartId) {
        @SuppressWarnings("unused")
        Cart cart = getCartOrThrow(cartId);
        
        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        cartItemRepository.deleteAll(items);
    }
}
