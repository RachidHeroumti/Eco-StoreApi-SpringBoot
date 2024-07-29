package com.demo.AuthConfigs.DTO;

import com.demo.AuthConfigs.models.Category;
import com.demo.AuthConfigs.models.Comment;

import java.util.List;

public class ProductDto {
    private Long id;
    private String name;
    private Category category;
    private String description;
    private Double price;
    private int quantity;
    private List<Comment> comments;

    public ProductDto() {
    }

    public ProductDto(String name, Category category, String description, Double price, int quantity, List<Comment> comments) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
