package com.bikkadit.blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikkadit.blog_app_apis.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	

}
