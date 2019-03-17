package com.revature.models;

import java.util.Date;
import java.util.Objects;

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
	
	@Column(name = "manager_email")
	private String managerEmail;
	@Column(name = "associate_email")
	private String associateEmail;
	
	private Date scheduled;
	private Date notified;
	private Date reviewed;
	private String place;
	

	@OneToOne
	@JoinColumn(name = "interview_feedback")
	private InterviewFeedback feedback;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "associate_input")
	private AssociateInput associateInput;

	@OneToOne
	@JoinColumn(name = "client")
	private Client client;
		

	public Interview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Interview(int id, String managerEmail, String associateEmail, Date scheduled, Date notified, Date reviewed, String place, InterviewFeedback feedback, AssociateInput associateInput) {
		this.id = id;
		this.managerEmail = managerEmail;
		this.associateEmail = associateEmail;
		this.scheduled = scheduled;
		this.notified = notified;
		this.reviewed = reviewed;
		this.place = place;
		this.feedback = feedback;
		this.associateInput = associateInput;
		this.client = client;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManagerEmail() {
		return this.managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getAssociateEmail() {
		return this.associateEmail;
	}

	public void setAssociateEmail(String associateEmail) {
		this.associateEmail = associateEmail;
	}

	public Date getScheduled() {
		return this.scheduled;
	}

	public void setScheduled(Date scheduled) {
		this.scheduled = scheduled;
	}

	public Date getNotified() {
		return this.notified;
	}

	public void setNotified(Date notified) {
		this.notified = notified;
	}

	public Date getReviewed() {
		return this.reviewed;
	}

	public void setReviewed(Date reviewed) {
		this.reviewed = reviewed;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public InterviewFeedback getFeedback() {
		return this.feedback;
	}

	public void setFeedback(InterviewFeedback feedback) {
		this.feedback = feedback;
	}

	public AssociateInput getAssociateInput() {
		return this.associateInput;
	}

	public void setAssociateInput(AssociateInput associateInput) {
		this.associateInput = associateInput;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client c) {
		this.client = c;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associateInput == null) ? 0 : associateInput.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + id;
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
		if (associateEmail != other.associateEmail)
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
		if (managerEmail != other.managerEmail)
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
		return "{" +
			" id='" + getId() + "'" +
			", managerEmail='" + getManagerEmail() + "'" +
			", associateEmail='" + getAssociateEmail() + "'" +
			", scheduled='" + getScheduled() + "'" +
			", notified='" + getNotified() + "'" +
			", reviewed='" + getReviewed() + "'" +
			", place='" + getPlace() + "'" +
			", feedback='" + getFeedback() + "'" +
			", associateInput='" + getAssociateInput() + "'" +
			", client='" + getClient() + "'" +
			"}";
	}


}
