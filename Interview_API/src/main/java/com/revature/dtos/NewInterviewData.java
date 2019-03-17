package com.revature.dtos;

public class NewInterviewData {
	private String managerEmail;
  	private int associateId;
	private long date;
	private String location;
	
	public String getManagerEmail() {
		return managerEmail;
	}
	public int getAssociateId() {
		return associateId;
	}
	public long getDate() {
		return date;
	}
	public String getLocation() {
		return location;
	}
	public NewInterviewData() {
		super();
	}
	
	
	
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public NewInterviewData(String managerEmail, int associateId, long date, String location) {
		super();
		this.managerEmail = managerEmail;
		this.associateId = associateId;
		this.date = date;
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateId;
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((managerEmail == null) ? 0 : managerEmail.hashCode());
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
		if (associateId != other.associateId)
			return false;
		if (date != other.date)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (managerEmail == null) {
			if (other.managerEmail != null)
				return false;
		} else if (!managerEmail.equals(other.managerEmail))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NewInterviewData [managerEmail=" + managerEmail + ", associateId=" + associateId + ", date=" + date
				+ ", location=" + location + "]";
	}
	
}
