package com.revature.services;

import java.util.List;

import com.revature.dtos.AssociateInterview;
import com.revature.dtos.NewAssociateInput;
import com.revature.dtos.NewInterviewData;
import com.revature.models.Interview;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterviewService {

	Interview save(Interview i);
	Interview update(Interview i);
	Interview delete(Interview i);
	
	List<Interview> findAll();
	Interview addNewInterview(NewInterviewData i);
	Interview addAssociateInput(NewAssociateInput a);
	Interview findById(int i);
	Page<Interview> findAll(Pageable page);
	Page<AssociateInterview> findInterviewsPerAssociate(Pageable page);

}
