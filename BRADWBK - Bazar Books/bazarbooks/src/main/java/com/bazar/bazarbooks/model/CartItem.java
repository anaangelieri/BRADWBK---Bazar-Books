package com.bazar.bazarbooks.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(name = "CartItem", description = "Representa um item dentro de um carrinho de compras")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do item no carrinho", example = "item456")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @Schema(description = "Carrinho ao qual este item pertence")
    @JsonBackReference
    private Cart cart;

    @Column(nullable = false)
    @Schema(description = "ID do livro associado ao item", example = "book789")
    private Integer bookId;

    @Column(nullable = false)
    @Schema(description = "Preço unitário do livro no momento da adição", example = "29.90")
    private double unitPrice;

    @Column(nullable = false)
    @Schema(description = "Quantidade do livro no carrinho", example = "2")
    private int quantity;

    public CartItem() {}

    public CartItem(Integer id, Cart cart, Integer bookId, double unitPrice, int quantity) {
        this.id = id;
        this.cart = cart;
        this.bookId = bookId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Transient
    @Schema(description = "Subtotal do item no carrinho", example = "59.80")
    public double getSubtotal() {
        return unitPrice * quantity;
    }
}