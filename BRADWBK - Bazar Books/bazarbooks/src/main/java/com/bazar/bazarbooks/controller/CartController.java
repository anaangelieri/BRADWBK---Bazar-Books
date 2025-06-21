package com.bazar.bazarbooks.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bazar.bazarbooks.model.Cart;
import com.bazar.bazarbooks.service.CartService;

@RestController
@RequestMapping("/carts")
@Tag(name = "Carrinhos", description = "Gerencia carrinhos de compras")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Buscar carrinho por ID do usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrinho encontrado"),
        @ApiResponse(responseCode = "404", description = "Carrinho não encontrado")
    })
    public ResponseEntity<Cart> getCartByUserId(@PathVariable int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Criar novo carrinho para o usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Carrinho criado com sucesso")
    })
    public ResponseEntity<Cart> createCart(@PathVariable int userId) {
        Cart created = cartService.createCartIfNotExists(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover carrinho pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Carrinho removido"),
        @ApiResponse(responseCode = "404", description = "Carrinho não encontrado")
    })
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
