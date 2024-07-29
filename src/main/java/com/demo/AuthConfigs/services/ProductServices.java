package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.DTO.ProductDto;
import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.models.User;
import com.demo.AuthConfigs.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServices {

    @Autowired
    private ProductRepo productRepo;

    public ResponseEntity<Iterable<Product>> getProducts() {
        Iterable<Product> products = productRepo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductDetails(Long id) {
        Optional<Product> product = productRepo.findById(id);
        return product.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Iterable<Product>> getProductsByCategory(Long id) {
        Iterable<Product> products = productRepo.findByCategory();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    public ResponseEntity<Product> addProduct(ProductDto product) {
        Product p= new Product(product.getName(),product.getCategory(),product.getDescription()
                ,product.getPrice(),product.getQuantity(),product.getComments());

         productRepo.save(p);
        return new ResponseEntity<>(p,HttpStatus.CREATED);
    }
}
