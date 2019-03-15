package com.revature.dtos;

import com.revature.models.Interview;

public class AssociateInterview implements Comparable<AssociateInterview>{
	private String associateEmail;
	private int interviewCount;
	private String AssociateName;
	
	public AssociateInterview() {
		associateEmail = "";
		interviewCount = 1;
		AssociateName = "";
	}

	public AssociateInterview(String associateEmail) {
		interviewCount = 1;
		this.associateEmail = associateEmail;
		AssociateName = "";
	}

	public AssociateInterview(Interview I) {
		interviewCount = 1;
		associateEmail = I.getAssociateEmail();
		AssociateName = "";
	}

	public String getAssociateEmail() {
		return associateEmail;
	}

	public int getInterviewCount() {
		return interviewCount;
	}

	public void incrementInterviewCount() {
		interviewCount++;
	}

	public String getAssociateName() {
		return AssociateName;
	}
	
	public void setAssociateName(String AssociateName) {
		this.AssociateName=AssociateName;
	}

	@Override
	public int compareTo(AssociateInterview o) {
		return this.associateEmail.compareTo(o.getAssociateEmail());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associateEmail == null) ? 0 : associateEmail.hashCode());
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
		AssociateInterview other = (AssociateInterview) obj;
		if (associateEmail == null) {
			if (other.associateEmail != null)
				return false;
		} else if (!associateEmail.equals(other.associateEmail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssociateInterview [associateEmail=" + associateEmail + ", interviewCount=" + interviewCount
				+ ", AssociateName=" + AssociateName + "]";
	}
}