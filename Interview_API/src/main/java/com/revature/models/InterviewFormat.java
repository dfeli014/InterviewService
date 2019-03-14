package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interview_format")
public class InterviewFormat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_format_id")
	private int id;
	
	@Column(name = "interview_format_description")
	private String formatDesc;

	public InterviewFormat(int id, String formatDesc) {
		super();
		this.id = id;
		this.formatDesc = formatDesc;
	}

	public InterviewFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormatDesc() {
		return formatDesc;
	}

	public void setFormatDesc(String formatDesc) {
		this.formatDesc = formatDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formatDesc == null) ? 0 : formatDesc.hashCode());
		result = prime * result + id;
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
		InterviewFormat other = (InterviewFormat) obj;
		if (formatDesc == null) {
			if (other.formatDesc != null)
				return false;
		} else if (!formatDesc.equals(other.formatDesc))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", formatDesc='" + getFormatDesc() + "'" +
			"}";
	}
	
}
