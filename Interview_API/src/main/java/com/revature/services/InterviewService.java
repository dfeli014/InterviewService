package com.revature.services;

import java.util.List;

import com.revature.dtos.NewInterviewData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.dtos.AssociateInterview;
import com.revature.models.Interview;
import com.revature.models.InterviewFeedback;
import com.revature.models.User;

public interface InterviewService {

	Interview save(Interview i);
	Interview update(Interview i);
	Interview delete(Interview i);
	Interview findById(int id);
	
	List<Interview> findAll();
	Interview addNewInterview(NewInterviewData i);
	Page<Interview> findAll(Pageable page);
	List<AssociateInterview> findInterviewsPerAssociate();
	Page<AssociateInterview> findInterviewsPerAssociate(Pageable page);
	List<User> getAssociateNeedFeedback();
	Page<User> getAssociateNeedFeedback(Pageable page);
	Integer[] getAssociateNeedFeedbackChart();
	InterviewFeedback getInterviewFeedbackByInterviewID(int interviewId);
}
