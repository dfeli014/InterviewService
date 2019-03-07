package com.revature.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "interview")
public class Interview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id")
	private int id;
	
	@Column(name = "manager_id")
	private int managerId;
	@Column(name = "associate_id")
	private int associateId;
	
	private Date scheduled;
	private Date notified;
	private Date reviewed;
	private String place;
	

	@OneToOne
	@JoinColumn(name = "interview_feedback")
	private InterviewFeedback feedback;
	
	@OneToOne
	@JoinColumn(name = "associate_input")
	private AssociateInput associateInput;
		

	public Interview() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Interview(int id, int managerId, int associateId, Date scheduled, Date notified, Date reviewed, String place,
			InterviewFeedback feedback, AssociateInput associateInput) {
		super();
		this.id = id;
		this.managerId = managerId;
		this.associateId = associateId;
		this.scheduled = scheduled;
		this.notified = notified;
		this.reviewed = reviewed;
		this.place = place;
		this.feedback = feedback;
		this.associateInput = associateInput;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public int getAssociateId() {
		return associateId;
	}


	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}


	public Date getScheduled() {
		return scheduled;
	}


	public void setScheduled(Date scheduled) {
		this.scheduled = scheduled;
	}


	public Date getNotified() {
		return notified;
	}


	public void setNotified(Date notified) {
		this.notified = notified;
	}


	public Date getReviewed() {
		return reviewed;
	}


	public void setReviewed(Date reviewed) {
		this.reviewed = reviewed;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public InterviewFeedback getFeedback() {
		return feedback;
	}


	public void setFeedback(InterviewFeedback feedback) {
		this.feedback = feedback;
	}


	public AssociateInput getAssociateInput() {
		return associateInput;
	}


	public void setAssociateInput(AssociateInput associateInput) {
		this.associateInput = associateInput;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateId;
		result = prime * result + ((associateInput == null) ? 0 : associateInput.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + id;
		result = prime * result + managerId;
		result = prime * result + ((notified == null) ? 0 : notified.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((reviewed == null) ? 0 : reviewed.hashCode());
		result = prime * result + ((scheduled == null) ? 0 : scheduled.hashCode());
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
		Interview other = (Interview) obj;
		if (associateId != other.associateId)
			return false;
		if (associateInput == null) {
			if (other.associateInput != null)
				return false;
		} else if (!associateInput.equals(other.associateInput))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (id != other.id)
			return false;
		if (managerId != other.managerId)
			return false;
		if (notified == null) {
			if (other.notified != null)
				return false;
		} else if (!notified.equals(other.notified))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (reviewed == null) {
			if (other.reviewed != null)
				return false;
		} else if (!reviewed.equals(other.reviewed))
			return false;
		if (scheduled == null) {
			if (other.scheduled != null)
				return false;
		} else if (!scheduled.equals(other.scheduled))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Interview [id=" + id + ", managerId=" + managerId + ", associateId=" + associateId + ", scheduled="
				+ scheduled + ", notified=" + notified + ", reviewed=" + reviewed + ", place=" + place + ", feedback="
				+ feedback + ", associateInput=" + associateInput + "]";
	}
	
	
}
