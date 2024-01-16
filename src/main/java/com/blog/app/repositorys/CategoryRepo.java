package com.blog.app.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

	

}
