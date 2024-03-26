package com.bikkadit.blog_app_apis.service;

import java.util.List;

import com.bikkadit.blog_app_apis.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserbyId(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);

}
