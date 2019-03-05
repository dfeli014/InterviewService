package com.revature.cognito.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CognitoRegisterResponse {

	@JsonProperty("User")
	private CognitoUser user;

	public CognitoRegisterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CognitoRegisterResponse(CognitoUser user) {
		super();
		this.user = user;
	}

	public CognitoUser getUser() {
		return user;
	}

	public void setUser(CognitoUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CognitoRegisterResponse [user=" + user + "]";
	}

}
