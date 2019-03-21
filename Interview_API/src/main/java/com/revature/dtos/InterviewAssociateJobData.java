package com.revature.dtos;

import java.util.Date;

import com.revature.models.Interview;

public class InterviewAssociateJobData {
	private Date InterviewDate;
	private String assocEmail;
	private String assocName;
	private boolean JD;
	
	public InterviewAssociateJobData(Interview I){
		this.InterviewDate = I.getScheduled();
		this.assocEmail = I.getAssociateEmail();
		this.assocName = "";
		if(I.getAssociateInput() == null) {
			JD=false;
		} else {
			JD=I.getAssociateInput().isDescriptionProvided();
		}
	}

	public String getAssocName() {
		return assocName;
	}

	public void setAssocName(String assocName) {
		this.assocName = assocName;
	}

	public Date getInterviewDate() {
		return InterviewDate;
	}

	public String getAssocEmail() {
		return assocEmail;
	}

	public boolean isJD() {
		return JD;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((InterviewDate == null) ? 0 : InterviewDate.hashCode());
		result = prime * result + (JD ? 1231 : 1237);
		result = prime * result + ((assocEmail == null) ? 0 : assocEmail.hashCode());
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
		InterviewAssociateJobData other = (InterviewAssociateJobData) obj;
		if (InterviewDate == null) {
			if (other.InterviewDate != null)
				return false;
		} else if (!InterviewDate.equals(other.InterviewDate))
			return false;
		if (JD != other.JD)
			return false;
		if (assocEmail == null) {
			if (other.assocEmail != null)
				return false;
		} else if (!assocEmail.equals(other.assocEmail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterviewAssociateJobData [InterviewDate=" + InterviewDate + ", assocEmail=" + assocEmail
				+ ", assocName=" + assocName + ", JD=" + JD + "]";
	}
	
	
}
