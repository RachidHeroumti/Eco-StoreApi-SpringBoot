package com.demo.AuthConfigs.repositories;

import com.demo.AuthConfigs.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Iterable<Product> findByCategory(Long id);
}
