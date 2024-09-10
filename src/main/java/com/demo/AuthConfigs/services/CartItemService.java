package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.Responces.ResponceApi;
import com.demo.AuthConfigs.models.Cart;
import com.demo.AuthConfigs.models.CartItem;
import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.repositories.CartItemRepo;
import com.demo.AuthConfigs.repositories.CartRepo;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    return  new ResponseEntity<>(new ResponceApi("added success",cartItemRepo.save(cartItem)), HttpStatus.CREATED);
}catch (Exception e){
    System.out.println(e.getMessage());
}
return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    public ResponseEntity<ResponceApi> RemoveItemFromCart(long cartId, long productId) {
        Cart cart ;
     try{
         cart = cartService.getCart(cartId);
         CartItem itemToRemove = getCartItem(cartId, productId);
         cart.removeItem(itemToRemove);
         cartRepo.save(cart);
       return   new ResponseEntity<>(new ResponceApi("deleted ",null),HttpStatus.OK);
     }catch (Exception e){
         return   new ResponseEntity<>(new ResponceApi(e.getMessage(),null),HttpStatus.NOT_FOUND);
     }
    }

    private CartItem getCartItem(long cartId, long productId) {
        Cart cart = cartService.getCart(cartId);
        return  cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new FetchNotFoundException("Item not found",null));
    }

    public CartItem UpdateQuantity(long cartId, long itemId, BigDecimal newQuantity) {

   return null ; }
}
