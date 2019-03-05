package com.revature.cognito.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CognitoUser {
	@JsonProperty("Attributes")
	private List<CognitoUserAttributes> attributes;

	public CognitoUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CognitoUser(List<CognitoUserAttributes> attributes) {
		super();
		this.attributes = attributes;
	}

	public List<CognitoUserAttributes> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<CognitoUserAttributes> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "CognitoUser [attributes=" + attributes + "]";
	}

}
