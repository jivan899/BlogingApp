package com.blog.app.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	Page<Post> findByUser(User user,Pageable pageable);
	List<Post> findByCategory(Category category);
	Page<Post> findByCategory(Category category, Pageable pageable);
	
	List<Post> findByTitleContaining(String keyWord);

//	OR
//	@Query("select p from Post p where p.tile like:key")
//	List<Post> serchByTitle(@Param("key") String keyWord);
}
