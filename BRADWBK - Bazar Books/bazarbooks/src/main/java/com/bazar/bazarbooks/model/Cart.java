package com.bazar.bazarbooks.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(name = "Cart", description = "Representa o carrinho de compras de um usuário")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do carrinho", example = "cart1234")
    private int id_cart;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser", nullable = false, unique = true)
    @Schema(description = "Usuário dono do carrinho")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de itens do carrinho")
    @JsonManagedReference
    private List<CartItem> items;

    public Cart() {}

    public Cart(User user) {
        this.user = user;
    }

    public int getId() {
        return id_cart;
    }

    public void setId(int id) {
        this.id_cart = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Transient
    @Schema(description = "Valor total de todos os itens do carrinho", example = "89.90")
    public double getTotal() {
        return items.stream()
                    .mapToDouble(CartItem::getSubtotal)
                    .sum();
    }
}