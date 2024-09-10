package com.demo.AuthConfigs.controllers;


import com.demo.AuthConfigs.Responces.ResponceApi;
import com.demo.AuthConfigs.models.Cart;
import com.demo.AuthConfigs.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    CartService cartService;


    @GetMapping("/get/{cartid}")
    public ResponseEntity<ResponceApi> getCart(@PathVariable Long cartid){
        try {
            Cart cart = cartService.getCart(cartid);
            return ResponseEntity.ok(new ResponceApi("Success", cart));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ResponceApi(e.getMessage(), null));
        }
    }

    @DeleteMapping("remove-cart/{cartId}")
    public ResponseEntity<ResponceApi> RemoveCart(@PathVariable long cartId){
        try{
            cartService.RemoveCart(cartId);
          return   new ResponseEntity<>(new ResponceApi("cart removed succussfuly",null), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ResponceApi(e.getMessage(), null));
        }
    }

    @GetMapping("get/total/price/{cartId}")
    public ResponseEntity<ResponceApi> getTotalPrice(@PathVariable long cartId){
        try{
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return new  ResponseEntity<>(new ResponceApi("total price",totalPrice),HttpStatus.OK) ;
        }catch (Exception e){
           return  new ResponseEntity<>(new ResponceApi(e.getMessage(),null), NOT_FOUND);
        }
    }


}
