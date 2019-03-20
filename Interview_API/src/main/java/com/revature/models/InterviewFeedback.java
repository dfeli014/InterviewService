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
@Table(name = "interview_feedback")
public class InterviewFeedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_feedback_id")
	private int id;
	
	public Date getFeedbackDelivered() {
		return feedbackDelivered;
	}

	public void setFeedbackDelivered(Date feedbackDelivered) {
		this.feedbackDelivered = feedbackDelivered;
	}

	@Column(name = "feedback_requested")
	private Date feedbackRequested;
	private String feedback;
	@Column(name = "feedback_received")
	private Date feedbackReceived;
	@Column(name = "feedback_delivered")
	private Date feedbackDelivered;
	
	@OneToOne
	@JoinColumn(name = "feedback_status")
	private FeedbackStatus status;
	
	@OneToOne(mappedBy="feedback")
	@JsonIgnore
	private Interview interview;
	
	public InterviewFeedback(int id, Date feedbackRequested, String feedback, Date feedbackReceived,
			FeedbackStatus status) {
		super();
		this.id = id;
		this.feedbackRequested = feedbackRequested;
		this.feedback = feedback;
		this.feedbackReceived = feedbackReceived;
		this.status = status;
	}

	public InterviewFeedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFeedbackRequested() {
		return feedbackRequested;
	}

	public void setFeedbackRequested(Date feedbackRequested) {
		this.feedbackRequested = feedbackRequested;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getFeedbackReceived() {
		return feedbackReceived;
	}

	public void setFeedbackReceived(Date feedbackReceived) {
		this.feedbackReceived = feedbackReceived;
	}

	public FeedbackStatus getStatus() {
		return status;
	}

	public void setStatus(FeedbackStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((feedbackDelivered == null) ? 0 : feedbackDelivered.hashCode());
		result = prime * result + ((feedbackReceived == null) ? 0 : feedbackReceived.hashCode());
		result = prime * result + ((feedbackRequested == null) ? 0 : feedbackRequested.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		InterviewFeedback other = (InterviewFeedback) obj;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (feedbackDelivered == null) {
			if (other.feedbackDelivered != null)
				return false;
		} else if (!feedbackDelivered.equals(other.feedbackDelivered))
			return false;
		if (feedbackReceived == null) {
			if (other.feedbackReceived != null)
				return false;
		} else if (!feedbackReceived.equals(other.feedbackReceived))
			return false;
		if (feedbackRequested == null) {
			if (other.feedbackRequested != null)
				return false;
		} else if (!feedbackRequested.equals(other.feedbackRequested))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
	
}
