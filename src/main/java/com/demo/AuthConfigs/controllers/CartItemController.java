package com.demo.AuthConfigs.controllers;

import com.demo.AuthConfigs.DTO.ProductDto;
import com.demo.AuthConfigs.Responces.ResponceApi;
import com.demo.AuthConfigs.models.CartItem;
import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.services.CartItemService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("cartitem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService ;


    @PostMapping("add-itemTocart")
    public ResponseEntity<ResponceApi> AddItemToCart(@RequestBody Product product, @RequestParam int quantity, @RequestParam long cartid){
        return cartItemService.addItemToCart(product,quantity,cartid);
    }

    @DeleteMapping("remove/{cartId}/product/{productid}")
    public ResponseEntity<ResponceApi> RemoveItemFromCart(@PathVariable long cartId,@PathVariable long productId){
        return cartItemService.RemoveItemFromCart(cartId,productId);
    }

    @PutMapping("update/cart/{cartId}/item/{itemId}")

    public ResponseEntity<ResponceApi> UpdateQuantity(@PathVariable long cartId,@PathVariable long itemId,@RequestParam BigDecimal NewQuantity){
        try{
            CartItem cartItem= cartItemService.UpdateQuantity(cartId,itemId,NewQuantity);
          return  new ResponseEntity<>(new ResponceApi("updated successfuly",cartItem),HttpStatus.OK) ;
        }catch (Exception e){
           return new ResponseEntity<>(new ResponceApi(e.getMessage(),null),HttpStatus.NOT_FOUND);
        }
    }

}
