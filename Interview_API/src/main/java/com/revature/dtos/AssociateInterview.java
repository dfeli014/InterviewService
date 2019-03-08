package com.revature.dtos;

import com.revature.models.Interview;

public class AssociateInterview implements Comparable<AssociateInterview>{
	private int associateId;
	private int interviewCount;
	
	public AssociateInterview() {
		associateId = 0;
		interviewCount = 1;
	}

	public AssociateInterview(int associateId) {
		interviewCount = 1;
		this.associateId = associateId;
	}

	public AssociateInterview(Interview I) {
		interviewCount = 1;
		associateId = I.getAssociateId();
	}

	public int getAssociateId() {
		return associateId;
	}

	public int getInterviewCount() {
		return interviewCount;
	}

	public void incrementInterviewCount() {
		interviewCount++;
	}

	@Override
	public int compareTo(AssociateInterview o) {
		// TODO Auto-generated method stub
		return o.getAssociateId()-this.associateId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateId;
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
		if (associateId != other.associateId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Associate [associateId=" + associateId + ", interviewCount=" + interviewCount + "]";
	}

}