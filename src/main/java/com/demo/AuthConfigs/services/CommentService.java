package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.DTO.CommnetDto;
import com.demo.AuthConfigs.models.Comment;
import com.demo.AuthConfigs.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommentService {

    @Autowired
    CommentRepo commentRepo;
    public ResponseEntity<Comment> addComment(CommnetDto commnetDto) {
        Comment c=new Comment( commnetDto.getUserId(),commnetDto.getProductId(),commnetDto.getCommentContent());
        commentRepo.save(c) ;

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}
