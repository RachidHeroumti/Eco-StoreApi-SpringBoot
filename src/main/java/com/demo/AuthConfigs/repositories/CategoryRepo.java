package com.demo.AuthConfigs.repositories;

import com.demo.AuthConfigs.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
