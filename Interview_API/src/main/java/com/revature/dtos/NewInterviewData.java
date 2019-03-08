package com.revature.dtos;

public class NewInterviewData {
	private int managerId;
  	private String firstName;
	private String lastName;
	private long date;
	private String location;
	private String format;
	public NewInterviewData(int managerId, String firstName, String lastName, long date, String location, String format) {
		super();
		this.managerId = managerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.location = location;
		this.format = format;
	}
	public int getManagerId() {
		return managerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public long getDate() {
		return date;
	}
	public String getLocation() {
		return location;
	}
	public String getFormat() {
		return format;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + managerId;
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
		if (date != other.date)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (managerId != other.managerId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NewInterviewData [managerId=" + managerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", date=" + date + ", location=" + location + ", format=" + format + "]";
	}
	
	
}