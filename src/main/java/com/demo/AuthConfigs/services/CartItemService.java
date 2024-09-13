package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.Exceptions.SrcNotFoundException;
import com.demo.AuthConfigs.Responces.ResponceApi;
import com.demo.AuthConfigs.models.Cart;
import com.demo.AuthConfigs.models.CartItem;
import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.repositories.CartItemRepo;
import com.demo.AuthConfigs.repositories.CartRepo;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    CartService cartService ;

    @Autowired
    CartRepo cartRepo ;


    public ResponseEntity<ResponceApi> addItemToCart(Product product, int quantity,long cartId) {
try{
   // Product p =productServices.convertToDtoProduct(product);

    CartItem cartItem=new CartItem(cartId,product,quantity);
       cartItemRepo.save(cartItem);
    return  new ResponseEntity<>(new ResponceApi("added success",cartItem), HttpStatus.CREATED);
}catch (Exception e){
    System.out.println(e.getMessage());
}
return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    public ResponseEntity<ResponceApi> RemoveItemFromCart(long cartId, long productId) {
        Cart cart ;
         cart = cartService.getCart(cartId);
         CartItem itemToRemove = getCartItem(cartId, productId);
         cart.removeItem(itemToRemove);
         cartRepo.save(cart);
       return   new ResponseEntity<>(new ResponceApi("deleted ",null),HttpStatus.OK);

    }

    private CartItem getCartItem(long cartId, long productId) {
        Cart cart = cartService.getCart(cartId);
        return  cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new SrcNotFoundException("Item not found"));
    }

    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());;
                });
        cart.updateTotalAmount();
        BigDecimal totalAmount = cart.getTotalAmount();

        cart.setTotalAmount(totalAmount);
        cartRepo.save(cart);
    }
}
