package com.blog.app.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

	
}
