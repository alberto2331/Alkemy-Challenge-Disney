package com.disney.auth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.disney.auth.dto.UserDto;
import com.disney.auth.entity.UserEntity;
import com.disney.auth.repository.UserRepository;
import com.disney.servicios.EmailServicio;

@Service
public class UserDetailsCustomService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailServicio emailServicio;
	
	@Override
	public UserDetails loadUserByUsername(String userName) { //throws UserNotFoundException{
		UserEntity userEntity = userRepository.findByUsername(userName);
		if(userEntity == null) {
			throw new UsernameNotFoundException("Username or password not fount");			
		}
		return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
	}
	
	public boolean save(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword(userDto.getPassword());
		userEntity = userRepository.save(userEntity);
		if(userEntity!=null) {
			emailServicio.sendWelcomeEmailTo(userEntity.getUsername());
		}
		return userEntity!=null;
	}
}
