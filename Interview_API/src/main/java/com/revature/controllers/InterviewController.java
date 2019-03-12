package com.revature.controllers;

import java.io.Console;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.dtos.AssociateInterview;

import com.revature.models.Interview;
import com.revature.models.InterviewFeedback;
import com.revature.models.InterviewFormat;
import com.revature.models.FeedbackStatus;
import com.revature.models.AssociateInput;
import com.revature.services.AssociateInputService;
import com.netflix.ribbon.proxy.annotation.Var;
import com.revature.dtos.NewInterviewData;
import com.revature.feign.IUserClient;
import com.revature.models.User;
import com.revature.dtos.AssociateInterview;
import com.revature.services.InterviewService;

@RestController
@RequestMapping("interview")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;
	private AssociateInputService associateService;
	
	@GetMapping
	public List<Interview> findAll() {
		return interviewService.findAll();
	}
	
	@GetMapping("/page")
	public Page<Interview> getInterviewPage(
            @RequestParam(name="orderBy", defaultValue="id") String orderBy,
            @RequestParam(name="direction", defaultValue="ASC") String direction,
            @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue="5") Integer pageSize) {
		// Example url call: ~:8091/interview/page?pageNumber=0&pageSize=3
		// The above url will return the 0th page of size 3.
		Sort sorter = new Sort(Sort.Direction.valueOf(direction), orderBy);
        Pageable pageParameters = PageRequest.of(pageNumber, pageSize, sorter);
        
        return interviewService.findAll(pageParameters);
    }
	
	//returns 2 numbers in a list
	//the first is the number of users
	//the second is the number of users who received 24 hour notice (according to the associate)
	@GetMapping("reports/request24/associate/")
	public List<Integer> getInterviewsWithin24HourNoticeAssociate() {
		//find all interviews
		List<Interview> allUsers = interviewService.findAll();
		//return
		List<Integer> returning;
		//find all interviews where the users were notified in advance
		ArrayList<Interview> allNotifiedUsers = new ArrayList<Interview>();
		
		//count all interviews
		int countAll = allUsers.size();

		//build a new list iteratively for allNotifiedUsers
		for (Interview i : allUsers)
		{
			if (i.getAssociateInput() == null)
				System.out.println("This interview has no associate input");
			if (i.getAssociateInput() != null)
				if (i.getAssociateInput().getReceivedNotifications() == null)
					System.out.println("This interview has associate input but no received notifications date");
			
			//check of non null
			if (i.getAssociateInput() != null)
				//check of non null
				if (i.getAssociateInput().getReceivedNotifications() != null)
				{
					//Singleton Calendar
					Calendar cal = Calendar.getInstance();
					//Set time on calendar to current receivedNotifications date
					cal.setTime(i.getScheduled());
					Date curDate = cal.getTime();
					//Add 24 Hours to the current date
					cal.add(Calendar.DATE, -1);
					//Calculate a new date, one day from the receivedNotifications
					Date oneDayBefore = cal.getTime();
					//If meets criteria, push to new list
					if (i.getAssociateInput().getReceivedNotifications().before(oneDayBefore) || !(i.getAssociateInput().getReceivedNotifications().after(oneDayBefore)))
					{
						allNotifiedUsers.add(i);
					}
			
					System.out.println("getScheduled: "+i.getScheduled()+" oneDayBefore: "+oneDayBefore+" Associate: "+i.getAssociateInput().getReceivedNotifications());
					System.out.println(i.getAssociateInput().getReceivedNotifications().before(oneDayBefore)+" vs "+(!(i.getAssociateInput().getReceivedNotifications().after(oneDayBefore))));
				}
		}
		
		//count only interviews that are within 24 hour notice
		int countNotified = allNotifiedUsers.size();
		returning = Arrays.asList(countAll, countNotified);
        return returning;
    }
	
	@GetMapping("{InterviewId}")
	public Interview getInterviewById(@PathVariable int InterviewId){
		return interviewService.findById(InterviewId);
	}
	
	//returns 2 numbers in a list
	//the first is the number of users
	//the second is the number of users who received 24 hour notice (according to the manager)
	@GetMapping("reports/request24/manager")
	public List<Integer> getInterviewsWithin24HourNoticeManager() {
		//find all interviews
		List<Interview> allUsers = interviewService.findAll();
		//return
		List<Integer> returning;
		//find all interviews where the users were notified in advance
		ArrayList<Interview> allNotifiedUsers = new ArrayList<Interview>();
		
		//count all interviews
		int countAll = allUsers.size();

		//build a new list iteratively for allNotifiedUsers
		for (Interview i : allUsers)
		{
			if (i.getNotified() == null)
			{
				System.out.println("This interview has no manager input");
			}
			
			
			//check of non null
				if (i.getNotified() != null)
				{
					//Singleton Calendar
					Calendar cal = Calendar.getInstance();
					//Set time on calendar to current receivedNotifications date
					cal.setTime(i.getScheduled());
					Date curDate = cal.getTime();
					//Add 24 Hours to the current date
					cal.add(Calendar.DATE, -1);
					//Calculate a new date, one day from the receivedNotifications
					Date oneDayBefore = cal.getTime();
					//If meets criteria, push to new list
					if (i.getNotified().before(oneDayBefore) || !(i.getNotified().after(oneDayBefore)))
					{
						allNotifiedUsers.add(i);
					}
			
					System.out.println("getScheduled: "+i.getScheduled()+" oneDayBefore: "+oneDayBefore+" Manager: "+i.getNotified());
					System.out.println(i.getNotified().before(oneDayBefore)+" vs "+(!(i.getNotified().after(oneDayBefore))));
				}
				
		}
		
		//count only interviews that are within 24 hour notice
		int countNotified = allNotifiedUsers.size();
		returning = Arrays.asList(countAll, countNotified);
        return returning;
    }

	@PostMapping("/saveinterview")
	public Interview newInterview(@Valid @RequestBody Interview i) {
		return interviewService.save(i);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Interview> addNewInterview(@Valid @RequestBody NewInterviewData i) {
		Interview returnedInterview = interviewService.addNewInterview(i);
		if(returnedInterview != null) {
			return ResponseEntity.ok(returnedInterview);
		}
		else {
			return (ResponseEntity<Interview>) ResponseEntity.badRequest();
		}
	}

	@Autowired
    private IUserClient userClient;

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		String o = "failed";
		try {
			System.out.println("userClient");
			System.out.println(userClient);
			o = userClient.findAll().toString();
			System.out.println("userClient.findAll()");
			System.out.println(o);
		} catch (Exception e)
		{
			System.out.println("exception occurred");
			System.out.println(e);
		}		
		return ResponseEntity.ok(o);		
	}

	@PostMapping("/associateInput")
	public AssociateInput newAssociateInput(@Valid @RequestBody AssociateInput a) {
		System.out.println(a);
		return associateService.save(a);
	}
	
	@GetMapping("Feedback/InterviewId/{InterviewId}")
	public InterviewFeedback getInterviewFeedbackByInterviewID(@PathVariable int InterviewId) {
		return interviewService.getInterviewFeedbackByInterviewID(InterviewId);
	}
	
	@GetMapping("reports/InterviewsPerAssociate")
	public List<AssociateInterview> getInterviewsPerAssociate() {
        return interviewService.findInterviewsPerAssociate();
    }
	
	@GetMapping("reports/InterviewsPerAssociate/page")
	public Page<AssociateInterview> getInterviewsPerAssociatePaged(
            @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue="5") Integer pageSize) {
		// Example url call: ~:8091/reports/InterviewsPerAssociate/page?pageNumber=0&pageSize=3
		// The above url will return the 0th page of size 3.
        Pageable pageParameters = PageRequest.of(pageNumber, pageSize);
        
        return interviewService.findInterviewsPerAssociate(pageParameters);
    }
	
	@GetMapping("reports/AssociateNeedFeedback")
	public List<User> getAssociateNeedFeedback() {
        return interviewService.getAssociateNeedFeedback();
    }
	
	@GetMapping("reports/AssociateNeedFeedback/page")
	public Page<User> getAssociateNeedFeedbackPaged(
            @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue="5") Integer pageSize) {
		// Example url call: ~:8091/reports/InterviewsPerAssociate/page?pageNumber=0&pageSize=3
		// The above url will return the 0th page of size 3.
        Pageable pageParameters = PageRequest.of(pageNumber, pageSize);
        
        return interviewService.getAssociateNeedFeedback(pageParameters);
    }
	
	// [0] is the total number of interviews
	// [1] is the number of interviews with feedback requested
	// [2] is the number of interviews with no feedback requested
	// [3] is the number of interviews that received feedback
	// [4] is the number of interviews that have had feedback delivered to associate
	@GetMapping("reports/AssociateNeedFeedback/chart")
	public Integer[] getAssociateNeedFeedbackChart() {
		return interviewService.getAssociateNeedFeedbackChart();
	}
  }
