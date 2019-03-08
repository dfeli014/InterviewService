package com.revature.services;

import java.util.List;

import com.revature.dtos.NewInterviewData;
import com.revature.models.Interview;

public interface InterviewService {

	Interview save(Interview i);
	Interview update(Interview i);
	Interview delete(Interview i);
	
	List<Interview> findAll();
	
	Interview addNewInterview(NewInterviewData i);
}
