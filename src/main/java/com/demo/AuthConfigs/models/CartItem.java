package com.demo.AuthConfigs.models;

import com.demo.AuthConfigs.DTO.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name ="CartItem")
@Getter
@Setter
public class CartItem {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;
    private Double unitPrice  ;
    private Cart cart ;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product ;
    private int Quantity ;



    public CartItem(Long cartId, Product product, int quantity) {
        this.cart.setId(cartId);
        this.product = product;
        Quantity = quantity;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }



}
