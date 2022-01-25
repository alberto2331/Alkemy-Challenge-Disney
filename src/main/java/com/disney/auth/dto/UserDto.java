package com.disney.auth.dto;

//@Data
public class UserDto {
	//@Email(message = "Username must be an email")
	private String username;
	//@Size(min = 8)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
