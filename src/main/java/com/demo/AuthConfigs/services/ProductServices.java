package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.DTO.ProductDto;
import com.demo.AuthConfigs.Responces.ResponceApi;
import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.repositories.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    private final ModelMapper modelMapper;
    private final ProductRepo productRepo;

    @Autowired
    public ProductServices(ModelMapper modelMapper, ProductRepo productRepo) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
    }

    public ResponseEntity<Iterable<Product>> getProducts() {
        Iterable<Product> products = productRepo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<ResponceApi> getProductDetails(Long id) {
        Optional<Product> product = productRepo.findById(id);
        return new ResponseEntity<>( new ResponceApi("Product details",convertToDtoProduct(product.orElse(null))),HttpStatus.OK) ;
                //product.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
        //                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    public ResponseEntity<Iterable<Product>> getProductsByCategory(Long id) {
        Iterable<Product> products = productRepo.findByCategory(id);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    public ResponseEntity<ResponceApi> addProduct(ProductDto productdto) {
        Product p = createProductFromDto(productdto);
        //add code later to upload images than get url;
         productRepo.save(p);
         ProductDto pdto=convertToDtoProduct(p);
        return new ResponseEntity<>(new ResponceApi("product added ",pdto),HttpStatus.CREATED);
    }

    public Product createProductFromDto(ProductDto dto) {
        return new Product(
                dto.getName(),
                dto.getCategory(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getQuantity(),
                dto.getComments(),
                Optional.ofNullable(dto.getImages()).orElse(Collections.emptyList())
        );
    }

    public ProductDto convertToDtoProduct(Product product) {
        return modelMapper.map(product,ProductDto.class);
    }

    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDtoProduct).toList();
    }
}


