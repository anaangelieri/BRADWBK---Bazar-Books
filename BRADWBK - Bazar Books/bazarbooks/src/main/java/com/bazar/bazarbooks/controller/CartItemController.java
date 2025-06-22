package com.bazar.bazarbooks.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.bazar.bazarbooks.model.CartItem;
import com.bazar.bazarbooks.service.CartItemService;

@RestController
@RequestMapping("/carts/{cartId}/cart-items")
@Tag(name = "Itens do Carrinho", description = "Gerencia os itens dentro de um carrinho de compras")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    @Operation(summary = "Listar itens de um carrinho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Itens listados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum item encontrado para o carrinho informado")
    })
    public ResponseEntity<List<CartItem>> listItems(@PathVariable Integer cartId) {
        List<CartItem> cartItems = cartItemService.getItemsByCartId(cartId);

        if (cartItems == null || cartItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{itemId}")
    @Operation(summary = "Obter item específico do carrinho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item encontrado"),
        @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    public ResponseEntity<CartItem> getCartItem(@PathVariable Integer cartId, @PathVariable Integer itemId) {
        CartItem cartItem = cartItemService.getItem(cartId, itemId);
        return ResponseEntity.ok(cartItem);
    }

    @PostMapping
    @Operation(summary = "Adicionar ou atualizar item no carrinho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Item adicionado ou atualizado")
    })
    public ResponseEntity<CartItem> addCartItem(@PathVariable Integer cartId, @RequestBody CartItem cartItem) {
        CartItem createdCartItem = cartItemService.addOrUpdateItem(cartId, cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartItem);
    }
    
    @PutMapping("/{itemId}/quantity")
    @Operation(summary = "Atualizar a quantidade de um item no carrinho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quantidade atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Quantidade inválida"),
        @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    public ResponseEntity<?> updateQuantity(
            @PathVariable Integer cartId,
            @PathVariable Integer itemId,
            @RequestBody QuantityUpdateRequest quantityUpdateRequest) {

        Integer quantity = quantityUpdateRequest.getQuantity();

        if (quantity == null || quantity < 0) {
            return ResponseEntity
                .badRequest()
                .body("A quantidade deve ser um número positivo ou zero.");
        }

        CartItem updatedItem = cartItemService.updateQuantity(cartId, itemId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    // Classe auxiliar para receber a quantidade no corpo da requisição
    public static class QuantityUpdateRequest {
        private Integer quantity;

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "Remover item do carrinho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Item removido"),
        @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer cartId, @PathVariable Integer itemId) {
        cartItemService.deleteItem(cartId, itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Remover todos os itens do carrinho")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Carrinho esvaziado com sucesso")
    })
    public ResponseEntity<Void> clearCart(@PathVariable Integer cartId) {
        cartItemService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
