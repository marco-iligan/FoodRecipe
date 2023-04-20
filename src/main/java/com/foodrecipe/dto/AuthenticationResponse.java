package com.foodrecipe.dto;

public class AuthenticationResponse {
	private Long userId;
	private String firstname;
	
	public AuthenticationResponse(Long userId, String firstname) {
		super();
		this.userId = userId;
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Long getUserId() {
		return userId;
	}
	
	
}
