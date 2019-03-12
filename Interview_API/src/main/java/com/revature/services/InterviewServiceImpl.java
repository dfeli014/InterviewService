package com.revature.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

import com.revature.dtos.AssociateInterview;
import com.revature.models.Interview;
import com.revature.models.User;
import com.revature.repos.InterviewRepo;
import com.revature.utils.ListToPage;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepo interviewRepo;
	
	@Autowired
    private IUserClient userClient;
	
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
	
	public Interview addNewInterview(NewInterviewData i) {
		int associateId = i.getAssociateId();//TODO: check if id is valid
		Date scheduled = new Date(i.getDate());//TODO: check this is valid date
		int managerId = 0;
		System.out.println("userClient");
		System.out.println(userClient);
		System.out.println("i.getManagerEmail().replace(\"@\", \"%20\")");
		System.out.println(i.getManagerEmail());
		Interview newInterview = new Interview(0, managerId, associateId, scheduled, null, null, i.getLocation(), null, null);	
		return save(newInterview);
		
//		try {
//			ResponseEntity<User> res = userClient.findByEmail(i.getManagerEmail().replace("@", "%20"));
//			System.out.println("res");
//			System.out.println(res);
//			if(res != null) {
//				managerId = res.getBody().getUserId();
//				Interview newInterview = new Interview(0, managerId, associateId, scheduled, null, null, i.getLocation(), null, null);	
//				return save(newInterview);
//			}
//			else return null;
//		} catch (Exception e) {
//			System.out.println("exception" + e);
//			return null;
//		}
	}
  
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
		System.out.println("List created");
		for(AssociateInterview A: associates) {
			try{
				User U = userClient.findById(A.getAssociateId());
				String Name = U.getFirstName();
				if(Name=="") {
					Name=U.getLastName();
				} else {
					Name+=" "+ U.getLastName();
				}
				System.out.println(Name);
				A.setAssociateName(Name);
			} catch (Exception e) {
				System.out.println(e);
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

	@Override
	public List<User> getAssociateNeedFeedback() {
		List<Interview> interviews = interviewRepo.findAll();
		Set<Integer> needFeedback = new TreeSet<Integer>();
		List<User> associates = new ArrayList<User>();
		
		for(Interview I: interviews) {
			if(I.getFeedback() != null && I.getFeedback().getFeedbackDelivered() == null) {
				needFeedback.add(I.getAssociateId());
			}
		}
		for(Integer N: needFeedback) {
			associates.add(userClient.findById(N));
		}
		return associates;
	}

	@Override
	public Page<User> getAssociateNeedFeedback(Pageable page) {
		PageImpl PI = ListToPage.getPage(getAssociateNeedFeedback(), page);
		return PI;
	}
}
