package com.revature.controllers;

import java.io.Console;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Var;
import com.revature.dtos.NewInterviewData;
import com.revature.feign.IUserClient;
import com.revature.models.Interview;
import com.revature.models.User;
import com.revature.services.InterviewService;

@RestController
@RequestMapping("interview")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;
	
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
}
