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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "associate_input")
public class AssociateInput {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "associate_input_id")
	private int id;
	
	@Column(name = "received_notifications")
	private Date receivedNotifications;
	@Column(name = "description_provided")
	private boolean descriptionProvided;
	
	@OneToOne(mappedBy="associateInput")
	@JsonIgnore
	private Interview interview;
	
	@OneToOne
	@JoinColumn(name = "interview_format")
	private InterviewFormat interviewFormat;
	
	@OneToOne
	@JoinColumn(name = "proposed_format")
	private InterviewFormat proposedFormat;

	public AssociateInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssociateInput(int id, Date receivedNotifications, boolean descriptionProvided, Interview interview,
			InterviewFormat interviewFormat, InterviewFormat proposedFormat) {
		super();
		this.id = id;
		this.receivedNotifications = receivedNotifications;
		this.descriptionProvided = descriptionProvided;
		this.interview = interview;
		this.interviewFormat = interviewFormat;
		this.proposedFormat = proposedFormat;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReceivedNotifications() {
		return receivedNotifications;
	}

	public void setReceivedNotifications(Date receivedNotifications) {
		this.receivedNotifications = receivedNotifications;
	}

	public boolean isDescriptionProvided() {
		return descriptionProvided;
	}

	public void setDescriptionProvided(boolean descriptionProvided) {
		this.descriptionProvided = descriptionProvided;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public InterviewFormat getInterviewFormat() {
		return interviewFormat;
	}

	public void setInterviewFormat(InterviewFormat interviewFormat) {
		this.interviewFormat = interviewFormat;
	}

	public InterviewFormat getProposedFormat() {
		return proposedFormat;
	}

	public void setProposedFormat(InterviewFormat proposedFormat) {
		this.proposedFormat = proposedFormat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (descriptionProvided ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((interviewFormat == null) ? 0 : interviewFormat.hashCode());
		result = prime * result + ((proposedFormat == null) ? 0 : proposedFormat.hashCode());
		result = prime * result + ((receivedNotifications == null) ? 0 : receivedNotifications.hashCode());
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
		AssociateInput other = (AssociateInput) obj;
		if (descriptionProvided != other.descriptionProvided)
			return false;
		if (id != other.id)
			return false;
		if (interviewFormat == null) {
			if (other.interviewFormat != null)
				return false;
		} else if (!interviewFormat.equals(other.interviewFormat))
			return false;
		if (proposedFormat == null) {
			if (other.proposedFormat != null)
				return false;
		} else if (!proposedFormat.equals(other.proposedFormat))
			return false;
		if (receivedNotifications == null) {
			if (other.receivedNotifications != null)
				return false;
		} else if (!receivedNotifications.equals(other.receivedNotifications))
			return false;
		return true;
	}		

}
