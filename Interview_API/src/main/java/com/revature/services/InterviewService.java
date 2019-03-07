package com.revature.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.revature.models.Interview;

public interface InterviewService {
	
	Interview save(Interview i);
	Interview update(Interview i);
	Interview delete(Interview i);
	
	List<Interview> findAll();
	Page<Interview> findAll(Pageable page);
}
