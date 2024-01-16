package com.blog.app.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Category;
import com.blog.app.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{

	

}
