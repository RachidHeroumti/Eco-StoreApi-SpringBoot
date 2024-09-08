package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.DTO.ProductDto;
import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.repositories.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public ResponseEntity<ProductDto> getProductDetails(Long id) {
        Optional<Product> product = productRepo.findById(id);
        return new ResponseEntity<>(convertToDtoProduct(product.orElse(null)),HttpStatus.OK) ;
                //product.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
        //                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    public ResponseEntity<Iterable<Product>> getProductsByCategory(Long id) {
        Iterable<Product> products = productRepo.findByCategory(id);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    public ResponseEntity<ProductDto> addProduct(ProductDto productdto) {
        Product p = createProductFromDto(productdto);
        //add code later to upload images than get url;

         productRepo.save(p);
         ProductDto pdto=convertToDtoProduct(p);
        return new ResponseEntity<>(pdto,HttpStatus.CREATED);
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
}


