package com.revature.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "cohorts")
public class Cohort {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cohortId;

	@NotNull
	private String cohortName;

	private String cohortDescription;

	private String cohortToken;

	@ManyToOne
	@JoinColumn(name = "address")
	private Address address;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE }, mappedBy = "cohorts")
	private Set<User> users = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "trainer_id", nullable = false)
	@NotNull
	private User trainer;

	public Cohort() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cohort(int cohortId, @NotNull String cohortName, @NotNull String cohortDescription, String cohortToken,
			Address address, LocalDate startDate, LocalDate endDate, Set<User> users, User trainer) {
		super();
		this.cohortId = cohortId;
		this.cohortName = cohortName;
		this.cohortDescription = cohortDescription;
		this.cohortToken = cohortToken;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.users = users;
		this.trainer = trainer;
	}

	public int getCohortId() {
		return cohortId;
	}

	public void setCohortId(int cohortId) {
		this.cohortId = cohortId;
	}

	public String getCohortName() {
		return cohortName;
	}

	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}

	public String getCohortDescription() {
		return cohortDescription;
	}

	public void setCohortDescription(String cohortDescription) {
		this.cohortDescription = cohortDescription;
	}

	public String getCohortToken() {
		return cohortToken;
	}

	public void setCohortToken(String cohortToken) {
		this.cohortToken = cohortToken;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cohortDescription == null) ? 0 : cohortDescription.hashCode());
		result = prime * result + cohortId;
		result = prime * result + ((cohortName == null) ? 0 : cohortName.hashCode());
		result = prime * result + ((cohortToken == null) ? 0 : cohortToken.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Cohort other = (Cohort) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cohortDescription == null) {
			if (other.cohortDescription != null)
				return false;
		} else if (!cohortDescription.equals(other.cohortDescription))
			return false;
		if (cohortId != other.cohortId)
			return false;
		if (cohortName == null) {
			if (other.cohortName != null)
				return false;
		} else if (!cohortName.equals(other.cohortName))
			return false;
		if (cohortToken == null) {
			if (other.cohortToken != null)
				return false;
		} else if (!cohortToken.equals(other.cohortToken))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cohort [cohortId=" + cohortId + ", cohortName=" + cohortName + ", cohortDescription="
				+ cohortDescription + ", cohortToken=" + cohortToken + ", address=" + address + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

}
