package com.bikkadit.blog_app_apis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog_app_apis.entity.User;
import com.bikkadit.blog_app_apis.exception.ResourceNotFoundException;
import com.bikkadit.blog_app_apis.payloads.UserDto;
import com.bikkadit.blog_app_apis.repository.UserRepository;
import com.bikkadit.blog_app_apis.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user =this.dtotoUser(userDto);
		User save = this.userRepository.save(user);
		return this.usertoDto(save);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User user2 = this.userRepository.save(user);
		UserDto userDto2 = this.usertoDto(user2);
		return userDto2 ;
	}

	@Override
	public UserDto getUserbyId(Integer userId) {
	   User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userdtos = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepository.delete(user);

	}

	public User dtotoUser(UserDto userDto) {
		
		User user=new User();
	    user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
		
	}
	
	public UserDto usertoDto(User user) {
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
		
	}

}
