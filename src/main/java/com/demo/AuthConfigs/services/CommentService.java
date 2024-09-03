package com.demo.AuthConfigs.services;

import com.demo.AuthConfigs.DTO.CommnetDto;
import com.demo.AuthConfigs.models.Comment;
import com.demo.AuthConfigs.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class CommentService {

    @Autowired
    CommentRepo commentRepo;
    public ResponseEntity<Comment> addComment(CommnetDto commnetDto) {
        Comment c=new Comment( commnetDto.getUserId(),commnetDto.getProductId(),commnetDto.getCommentContent());
        commentRepo.save(c) ;

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    public ResponseEntity<Comment> updateComment(CommnetDto commnetDto) {
        Comment c=new Comment( commnetDto.getUserId(),commnetDto.getProductId(),commnetDto.getCommentContent());
        commentRepo.save(c) ;
        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteComment(CommnetDto commentDto) {
        // Find the comment by its ID
        Optional<Comment> commentOptional = commentRepo.findById(commentDto.getId());

        // Check if the comment exists
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            // Delete the comment
            commentRepo.delete(comment);
            // Return a success response
            return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        } else {
            // If the comment is not found, return a not found response
            return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
        }
    }
}
