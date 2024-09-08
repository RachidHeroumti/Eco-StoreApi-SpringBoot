package com.demo.AuthConfigs.controllers;

import com.demo.AuthConfigs.DTO.ProductDto;
import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {


    @Autowired
  ProductServices productServices;

    @GetMapping("all-pr")
    public ResponseEntity<Iterable<Product>> getAllProduct(){
        return productServices.getProducts();
    }

    @GetMapping("get-pr/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return productServices.getProductDetails(id) ;
    }

    @GetMapping("/by-category/{id}")
    public ResponseEntity<Iterable<Product>> getProductsByCategory( @PathVariable Long id){
        return productServices.getProductsByCategory(id);
    }


    @PostMapping("create-pr")
    public ResponseEntity<ProductDto> AddProduct(@RequestBody ProductDto product){
        return productServices.addProduct(product);
    }



}
