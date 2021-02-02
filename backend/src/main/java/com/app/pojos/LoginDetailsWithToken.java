package com.app.pojos;

public class LoginDetailsWithToken {

	private int userId;
	private UserType role;
	private String token;
	public LoginDetailsWithToken() {
		super();
		System.out.println("in constructor of"+getClass().getName());
	}
	
	public LoginDetailsWithToken(int userId, UserType role, String token) {
		super();
		this.userId = userId;
		this.role = role;
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginDetailsWithToken [userId=" + userId + ", role=" + role + ", token=" + token + "]";
	}

	
	
	
}
