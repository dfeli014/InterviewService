package com.revature.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.revature.models.Interview;
import com.revature.repos.InterviewRepo;

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
	
	@Override
	public Interview addNewInterview(NewInterviewData i) {
		int associateId = 1;// fetch user from other db
		Date scheduled = new Date(i.getDate());//TODO: check this is valid date
		Interview newInterview = new Interview(0, i.getManagerId(), associateId, scheduled, null, null, i.getLocation(), null, null);
		
		return save(newInterview);
	}
		
}
