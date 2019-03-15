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

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Interview)) {
			return false;
		}
		Interview interview = (Interview) o;
		return id == interview.id && Objects.equals(managerEmail, interview.managerEmail) && Objects.equals(associateEmail, interview.associateEmail) && Objects.equals(scheduled, interview.scheduled) && Objects.equals(notified, interview.notified) && Objects.equals(reviewed, interview.reviewed) && Objects.equals(place, interview.place) && Objects.equals(feedback, interview.feedback) && Objects.equals(associateInput, interview.associateInput);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, managerEmail, associateEmail, scheduled, notified, reviewed, place, feedback, associateInput);
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
			"}";
	}


}
