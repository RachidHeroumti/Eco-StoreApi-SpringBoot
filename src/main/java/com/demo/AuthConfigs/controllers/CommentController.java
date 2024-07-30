package com.demo.AuthConfigs.controllers;


import com.demo.AuthConfigs.DTO.CommnetDto;
import com.demo.AuthConfigs.models.Comment;
import com.demo.AuthConfigs.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    CommentService commentService ;

    @PostMapping("get-comment")
    public ResponseEntity<Comment> addComment(@RequestBody CommnetDto commnetDto){
        return commentService.addComment(commnetDto) ;

    }
}
