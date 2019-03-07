package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.revature.models.Interview;
import com.revature.repos.InterviewRepo;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepo interviewRepo;
	
	public Interview save(Interview i) {
		return interviewRepo.save(i);
	}
	
	public Interview update(Interview i) {
		return null;
	}
	
	public Interview delete(Interview i) {
		return null;
	}

	@Override
	public List<Interview> findAll() {
		// TODO Auto-generated method stub
		return interviewRepo.findAll();
	}
	
	@Override
    public Page<Interview> findAll(Pageable page) {
        // TODO Auto-generated method stub
        return interviewRepo.findAll(page);
    }   
}
