package com.revature.dtos;

public class NewInterviewData {
  	private String associateEmail;
	private long date;
	private String location;
	public NewInterviewData() {
		super();
	}
	public NewInterviewData(String associateEmail, long date, String location) {
		super();
		this.associateEmail = associateEmail;
		this.date = date;
		this.location = location;
	}
	public String getAssociateEmail() {
		return associateEmail;
	}
	public void setAssociateEmail(String associateEmail) {
		this.associateEmail = associateEmail;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associateEmail == null) ? 0 : associateEmail.hashCode());
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		NewInterviewData other = (NewInterviewData) obj;
		if (associateEmail == null) {
			if (other.associateEmail != null)
				return false;
		} else if (!associateEmail.equals(other.associateEmail))
			return false;
		if (date != other.date)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NewInterviewData [associateEmail=" + associateEmail + ", date=" + date + ", location=" + location + "]";
	}
	
	
}
