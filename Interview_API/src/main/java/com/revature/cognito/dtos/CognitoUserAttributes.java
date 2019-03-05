package com.revature.cognito.dtos;

public class CognitoUserAttributes {
	private String name;
	private String value;

	public CognitoUserAttributes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CognitoUserAttributes(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CognitoUserAttributes [name=" + name + ", value=" + value + "]";
	}

}
