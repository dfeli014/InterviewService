package com.revature.cognito.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CognitoTokenClaims {
	@JsonProperty("cognito:groups")
	private String groups;

	private String email;

	public CognitoTokenClaims() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CognitoTokenClaims(String groups, String email) {
		super();
		this.groups = groups;
		this.email = email;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CognitoTokenClaims other = (CognitoTokenClaims) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CognitoTokenClaims [groups=" + groups + ", email=" + email + "]";
	}

}
