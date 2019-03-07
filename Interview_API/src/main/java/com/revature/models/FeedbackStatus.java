package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_status")
public class FeedbackStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_status_id")
	private int id;
	
	@Column(name = "feedback_status_description")
	private String statusDesc;
	
	public FeedbackStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeedbackStatus(int feedback_status_id, String feedback_status_desc) {
		super();
		this.id = feedback_status_id;
		this.statusDesc = feedback_status_desc;
	}

	public int getFeedback_status_id() {
		return id;
	}

	public void setFeedback_status_id(int feedback_status_id) {
		this.id = feedback_status_id;
	}

	public String getFeedback_status_desc() {
		return statusDesc;
	}

	public void setFeedback_status_desc(String feedback_status_desc) {
		this.statusDesc = feedback_status_desc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((statusDesc == null) ? 0 : statusDesc.hashCode());
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
		FeedbackStatus other = (FeedbackStatus) obj;
		if (id != other.id)
			return false;
		if (statusDesc == null) {
			if (other.statusDesc != null)
				return false;
		} else if (!statusDesc.equals(other.statusDesc))
			return false;
		return true;
	}


	
	
	
}
