package com.app.pojos;

public class LoginDetailsDto {

	private int userId;
	private UserType role;
	public LoginDetailsDto() {
		super();
		System.out.println("in constructor of"+getClass().getName());
	}
	public LoginDetailsDto(int userId, UserType role) {
		super();
		this.userId = userId;
		this.role = role;
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
	@Override
	public String toString() {
		return "LoginDetailsDto [userId=" + userId + ", role=" + role + "]";
	}
	
	
}
