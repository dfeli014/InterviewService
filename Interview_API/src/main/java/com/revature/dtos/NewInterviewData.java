package com.revature.dtos;

import java.util.Objects;

public class NewInterviewData {
  	private String associateEmail;
	private long date;
	private String location;
	private String client; 

	public NewInterviewData() {
		super();
	}

	public NewInterviewData(String associateEmail, long date, String location, String client) {
		super();
		this.associateEmail = associateEmail;
		this.date = date;
		this.location = location;
		this.client = client;
	}

	public String getAssociateEmail() {
		return this.associateEmail;
	}

	public void setAssociateEmail(String associateEmail) {
		this.associateEmail = associateEmail;
	}

	public long getDate() {
		return this.date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public NewInterviewData associateEmail(String associateEmail) {
		this.associateEmail = associateEmail;
		return this;
	}

	public NewInterviewData date(long date) {
		this.date = date;
		return this;
	}

	public NewInterviewData location(String location) {
		this.location = location;
		return this;
	}

	public NewInterviewData client(String client) {
		this.client = client;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof NewInterviewData)) {
			return false;
		}
		NewInterviewData newInterviewData = (NewInterviewData) o;
		return Objects.equals(associateEmail, newInterviewData.associateEmail) && date == newInterviewData.date && Objects.equals(location, newInterviewData.location) && Objects.equals(client, newInterviewData.client);
	}

	@Override
	public int hashCode() {
		return Objects.hash(associateEmail, date, location, client);
	}

	@Override
	public String toString() {
		return "{" +
			" associateEmail='" + getAssociateEmail() + "'" +
			", date='" + getDate() + "'" +
			", location='" + getLocation() + "'" +
			", client='" + getClient() + "'" +
			"}";
	}


	
}
