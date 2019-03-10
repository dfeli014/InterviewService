package com.revature.controllers;

import java.io.Console;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.ribbon.proxy.annotation.Var;
import com.revature.dtos.NewInterviewData;
import com.revature.feign.IUserClient;
import com.revature.models.Interview;
import com.revature.models.User;
import com.revature.models.AssociateInput;
import com.revature.dtos.AssociateInterview;
import com.revature.models.Interview;
import com.revature.services.AssociateInputService;
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

	@PostMapping("/saveinterview")
	public Interview newInterview(@Valid @RequestBody Interview i) {
		return interviewService.save(i);
	}
	
	@PostMapping("/newinterview")
	public ResponseEntity<Interview> addNewInterview(@Valid @RequestBody NewInterviewData i) {
		return ResponseEntity.ok(interviewService.addNewInterview(i));
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
	
	@GetMapping("reports/InterviewsPerAssociate")
	public List<AssociateInterview> getInterview() {
        return interviewService.findInterviewsPerAssociate();
    }
	
	@GetMapping("reports/InterviewsPerAssociate/page")
	public Page<AssociateInterview> getInterviewsPerAssociate(
            @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue="5") Integer pageSize) {
		// Example url call: ~:8091/interview/page?pageNumber=0&pageSize=3
		// The above url will return the 0th page of size 3.
        Pageable pageParameters = PageRequest.of(pageNumber, pageSize);
        
        return interviewService.findInterviewsPerAssociate(pageParameters);
    }
}
