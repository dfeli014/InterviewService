package com.revature.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.dtos.AssociateInterview;
import com.revature.models.Interview;
import com.revature.repos.InterviewRepo;
import com.revature.utils.ListToPage;

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

	public List<AssociateInterview> findInterviewsPerAssociate() {
		List<Interview> interviews = interviewRepo.findAll();
		List<AssociateInterview> associates = new ArrayList<AssociateInterview>();
		
		for(Interview I: interviews) {
			AssociateInterview A = new AssociateInterview(I);
			int index = associates.indexOf(A);
			System.out.println("New: " + A);
			if(index>0) {
				A=associates.get(index);
				A.incrementInterviewCount();
				associates.set(index, A);
				System.out.println("Incremented: " + A);
			} else {
				associates.add(A);
			}
		}
		Collections.sort(associates);
		return associates;
	}

	public Page<AssociateInterview> findInterviewsPerAssociate(Pageable page) {
//		List<AssociateInterview> associates = findInterviewsPerAssociate();
//		int start = page.getPageNumber()*page.getPageSize();
//		int end = ((page.getPageNumber()+1)*page.getPageSize())-1;
//		return new PageImpl<AssociateInterview>(associates.subList(start, end), page, associates.size());
		PageImpl PI = ListToPage.getPage(findInterviewsPerAssociate(), page);
		return PI;
	}
}
