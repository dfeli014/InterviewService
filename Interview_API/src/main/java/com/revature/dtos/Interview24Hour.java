package com.revature.dtos;

import java.util.Calendar;
import java.util.Date;

import com.revature.models.Interview;

public class Interview24Hour {
	private Date InterviewDate;
	private String assocEmail;
	private String assocName;
	private boolean twentyFourAssoc;
	private boolean twentyFourManager;
	
	public Interview24Hour(Interview I) {
		this.InterviewDate = I.getScheduled();
		this.assocEmail = I.getAssociateEmail();
		this.assocName = "";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(I.getScheduled());
		Date curDate = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date oneDayBefore = cal.getTime();
		
		if(I.getNotified()==null) {
			this.twentyFourManager = false;
		} else {
			this.twentyFourManager =  (I.getNotified().before(oneDayBefore) || !(I.getNotified().after(oneDayBefore)));
		}
		if(I.getAssociateInput()==null) {
			this.twentyFourAssoc = false;
		} else {
			this.twentyFourAssoc = (I.getAssociateInput().getReceivedNotifications().before(oneDayBefore) || !(I.getAssociateInput().getReceivedNotifications().after(oneDayBefore)));
		}
	}

	public Date getInterviewDate() {
		return InterviewDate;
	}
	

	public String getAssocEmail() {
		return assocEmail;
	}
	

	public String getAssocName() {
		return assocName;
	}
	

	public boolean isTwentyFourAssoc() {
		return twentyFourAssoc;
	}
	

	public boolean isTwentyFourManager() {
		return twentyFourManager;
	}

	public void setAssocName(String assocName) {
		this.assocName = assocName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((InterviewDate == null) ? 0 : InterviewDate.hashCode());
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
		Interview24Hour other = (Interview24Hour) obj;
		if (InterviewDate == null) {
			if (other.InterviewDate != null)
				return false;
		} else if (!InterviewDate.equals(other.InterviewDate))
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
		return "Interview24Hour [InterviewDate=" + InterviewDate + ", assocEmail=" + assocEmail + ", assocName="
				+ assocName + ", twentyFourAssoc=" + twentyFourAssoc + ", twentyFourManager=" + twentyFourManager + "]";
	}
	
	
}
