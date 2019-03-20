package com.revature.services;

import java.util.List;

import com.revature.dtos.AssociateInterview;
import com.revature.dtos.NewAssociateInput;
import com.revature.dtos.NewInterviewData;
import com.revature.models.Interview;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.revature.dtos.AssociateInterview;
import com.revature.dtos.FeedbackData;
import com.revature.dtos.Interview24Hour;
import com.revature.dtos.InterviewAssociateJobData;
import com.revature.models.Interview;
import com.revature.models.InterviewFeedback;
import com.revature.models.User;

public interface InterviewService {

	Interview save(Interview i);
	Interview update(Interview i);
	Interview delete(Interview i);
	Interview findById(int id);
	Interview findByAssociateEmail(String s);
	Interview findByManagerEmail(String s);
	
	List<Interview> findAll();
	Interview addNewInterview(NewInterviewData i);
	Interview addAssociateInput(NewAssociateInput a);
	Page<Interview> findAll(Pageable page);
	Page<Interview> findAllByAssociateEmail(String email, Pageable page);
	List<AssociateInterview> findInterviewsPerAssociate();
	Page<AssociateInterview> findInterviewsPerAssociate(Pageable page);
	List<Integer> getInterviewsWithin24HourNoticeAssociate();
	List<Integer> getInterviewsWithin24HourNoticeManager();
	Interview setFeedback(FeedbackData f);
	List<User> getAssociateNeedFeedback();
	Page<User> getAssociateNeedFeedback(Pageable page);
	List<Interview24Hour> getAll24HourNotice();
	Page<Interview24Hour> getAll24HourNotice(Pageable page);
	List<InterviewAssociateJobData> getAllJD();
	Page<InterviewAssociateJobData> getAllJD(Pageable page);
	Integer[] getAssociateNeedFeedbackChart();
	InterviewFeedback getInterviewFeedbackByInterviewID(int interviewId);
	Interview markReviewed(int interviewId);
}
