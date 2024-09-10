package com.demo.AuthConfigs.repositories;

import com.demo.AuthConfigs.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long userId);
}
