package com.revature.cognito.dtos;

public class CognitoRegisterBody {
	private String email;

	public CognitoRegisterBody(String email) {
		super();
		this.email = email;
	}

	public CognitoRegisterBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CognitoRegisterBody [email=" + email + "]";
	}

}
