package com.demo.AuthConfigs.repositories;

import com.demo.AuthConfigs.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
