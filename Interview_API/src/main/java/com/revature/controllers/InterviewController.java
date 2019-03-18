package com.revature.controllers;

import java.io.Console;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.revature.dtos.FeedbackData;
import com.revature.dtos.Interview24Hour;
import com.revature.dtos.InterviewAssociateJobData;
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
import com.revature.dtos.NewAssociateInput;
import com.revature.models.Interview;
import com.revature.services.AssociateInputService;
import com.revature.services.InterviewService;

@RestController
@RequestMapping("interview")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;
	@Autowired
	private AssociateInputService associateInputService;
	
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
	@GetMapping("reports/request24/associate")
	public List<Integer> getInterviewsWithin24HourNoticeAssociate() {
		return interviewService.getInterviewsWithin24HourNoticeAssociate();
    }
	
	@GetMapping("{InterviewId}")
	public Interview getInterviewById(@PathVariable int InterviewId){
		return interviewService.findById(InterviewId);
	}

	@GetMapping("markReviewed/{InterviewId}")
	public Interview markReviewed(@PathVariable int InterviewId){
		return interviewService.markReviewed(InterviewId);
	}
	
	//returns 2 numbers in a list
	//the first is the number of users
	//the second is the number of users who received 24 hour notice (according to the manager)
	@GetMapping("reports/request24/manager")
	public List<Integer> getInterviewsWithin24HourNoticeManager() {
		return interviewService.getInterviewsWithin24HourNoticeManager();
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

	@GetMapping("/findInterview")
	public Interview findInterviewById(
		@RequestParam(name="interviewId", defaultValue="id") int interviewId) {

		return interviewService.findById(interviewId);
	}

	@PostMapping("/associateInput")
	public ResponseEntity<Interview> newAssociateInput(@Valid @RequestBody NewAssociateInput a) {
		//System.out.println(a);
		return ResponseEntity.ok(interviewService.addAssociateInput(a));
	}
	
	@PostMapping("/feedback")
	public ResponseEntity<Interview> updateInterviewFeedback(@Valid @RequestBody FeedbackData f) {
		Interview result = interviewService.setFeedback(f);
		if(result != null) {
			return ResponseEntity.ok(interviewService.setFeedback(f));
		}
		return new ResponseEntity<Interview>(HttpStatus.BAD_REQUEST);
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
	

	@GetMapping("reports/interview24")
	public List<Interview24Hour> getAll24HourNotice() {
        return interviewService.getAll24HourNotice();
    }
	
	@GetMapping("reports/interview24/page")
	public Page<Interview24Hour> getAll24HourNotice(
            @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue="5") Integer pageSize) {
		// Example url call: ~:8091/reports/getAll24HourNotice/page?pageNumber=0&pageSize=3
		// The above url will return the 0th page of size 3.
        Pageable pageParameters = PageRequest.of(pageNumber, pageSize);
        
        return interviewService.getAll24HourNotice(pageParameters);
    }
	

	@GetMapping("reports/interviewJD")
	public List<InterviewAssociateJobData> getAllJD() {
        return interviewService.getAllJD();
    }
	
	@GetMapping("reports/interviewJD/page")
	public Page<InterviewAssociateJobData> getAllJD(
            @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue="5") Integer pageSize) {
		// Example url call: ~:8091/reports/getAll24HourNotice/page?pageNumber=0&pageSize=3
		// The above url will return the 0th page of size 3.
        Pageable pageParameters = PageRequest.of(pageNumber, pageSize);
        
        return interviewService.getAllJD(pageParameters);
    }
	
	
	// [0] is the total number of interviews
	// [1] is the number of interviews with no feedback requested
	// [2] is the number of interviews with feedback requested
	// [3] is the number of interviews that received feedback that hasn't been delivered to associate
	// [4] is the number of interviews that received feedback that has been delivered to associate
	@GetMapping("reports/AssociateNeedFeedback/chart")
	public Integer[] getAssociateNeedFeedbackChart() {
		return interviewService.getAssociateNeedFeedbackChart();
	}
  }

