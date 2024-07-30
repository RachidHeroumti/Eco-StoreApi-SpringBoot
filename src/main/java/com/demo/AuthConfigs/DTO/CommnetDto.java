package com.demo.AuthConfigs.DTO;

import com.demo.AuthConfigs.models.Product;
import com.demo.AuthConfigs.models.User;

public class CommnetDto {
    private Long id;
    private User userId;
    private Product productId;
    private String commentContent;


    public CommnetDto(User userId, Product productId, String commentContent) {
        this.userId = userId;
        this.productId = productId;
        this.commentContent = commentContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}

