package com.revature.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.revature.cognito.dtos.CognitoRegisterBody;
import com.revature.cognito.dtos.CognitoRegisterResponse;
import com.revature.cognito.dtos.CognitoTokenClaims;
import com.revature.dtos.NewInterviewData;
import com.revature.feign.IUserClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.dtos.AssociateInterview;
import com.revature.dtos.NewAssociateInput;
import com.revature.models.AssociateInput;
import com.revature.models.Interview;
import com.revature.repos.AssociateInputRepo;
import com.revature.repos.InterviewRepo;
import com.revature.utils.ListToPage;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepo interviewRepo;

	@Autowired
	private AssociateInputRepo associateRepo;

	@Autowired
	private IUserClient userClient;

	public Interview save(Interview i) {
		return interviewRepo.save(i);
	}

	@Override
	public Interview update(Interview i) {
		return interviewRepo.save(i);
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
	public Interview addNewInterview(NewInterviewData i) {
		int associateId = 1;// fetch user from other db
		Date scheduled = new Date(i.getDate());// TODO: check this is valid date
		Client c = new Client();
		Interview newInterview = new Interview(0, i.getManagerEmail(), i.getAssociateEmail(), scheduled, null, null, i.getLocation(),
				null, null, c);

		return save(newInterview);
	}

	public Page<Interview> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return interviewRepo.findAll(page);
	}

	public List<AssociateInterview> findInterviewsPerAssociate() {
		List<Interview> interviews = interviewRepo.findAll();
		List<AssociateInterview> associates = new ArrayList<AssociateInterview>();

		for (Interview I : interviews) {
			AssociateInterview A = new AssociateInterview(I);
			int index = associates.indexOf(A);
			System.out.println("New: " + A);
			if (index > 0) {
				A = associates.get(index);
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
		// List<AssociateInterview> associates = findInterviewsPerAssociate();
		// int start = page.getPageNumber()*page.getPageSize();
		// int end = ((page.getPageNumber()+1)*page.getPageSize())-1;
		// return new PageImpl<AssociateInterview>(associates.subList(start, end), page,
		// associates.size());
		PageImpl PI = ListToPage.getPage(findInterviewsPerAssociate(), page);
		return PI;
	}

	@Override
	public Interview findById(int i) {
		List<Interview> listInt = interviewRepo.findAll();
		Interview found = new Interview();

		for (Interview it : listInt) {
			if (it.getId() == i) {
					found = it;
			}
		}

		return found;
	}

	@Override
    public Interview addAssociateInput(NewAssociateInput a) {
        
        int interviewNumber = a.getInterviewId();
        Interview temp = this.findById(interviewNumber);     
        AssociateInput ai = new AssociateInput(0, a.getReceivedNotifications(), a.isDescriptionProvided(), temp, a.getInterviewFormat(), 
        a.getProposedFormat());

		temp.setAssociateInput(ai);
		associateRepo.save(ai);
	
		return temp;
    }
}
