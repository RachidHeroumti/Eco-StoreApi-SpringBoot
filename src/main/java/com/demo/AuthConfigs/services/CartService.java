package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.models.Cart;
import com.demo.AuthConfigs.repositories.CartItemRepo;
import com.demo.AuthConfigs.repositories.CartRepo;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo ;
    @Autowired
    CartItemRepo cartItemRepo ;

    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    public Cart getCart(Long cartId)  {
        Cart cart = null;
        try {
            cart = cartRepo.findById(cartId)
                    .orElseThrow(() -> new FetchNotFoundException("Cart ",null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cart;

    }



    public Long initializeNewCart() {
        Cart newCart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return cartRepo.save(newCart).getId();

    }


    public Cart getCartByUserId(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public void RemoveCart(long cartId)  {
        Cart cart = getCart(cartId);
        cartItemRepo.deleteAllByCartId(cartId);
        cart.getItems().clear();
        cartRepo.deleteById(cartId);
    }

    public BigDecimal getTotalPrice(long cartId)  {
        Cart cart = getCart(cartId);
        return cart.getTotalAmount();

    }
}
