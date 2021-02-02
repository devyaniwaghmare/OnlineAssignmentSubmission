package com.app.pojos;

import javax.validation.constraints.NotEmpty;

public class Login {

	@NotEmpty
	private String emailId;
	@NotEmpty
	private String password;

	public Login() {
		super();
		System.out.println("In constructor of "+getClass().getName());
	}

	public Login(@NotEmpty String emailId, @NotEmpty String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [emailId=" + emailId + ", password=" + password + "]";
	}
	

	
	
}
