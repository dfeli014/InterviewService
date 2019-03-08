package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AssociateInput;
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
	
	@PostMapping("/associateInput")
	public AssociateInput newAssociateInput(@Valid @RequestBody AssociateInput a) {
		System.out.println(a);
		return associateService.save(a);
	}

	@PostMapping("/saveInterview")
	public Interview newInterview(@Valid @RequestBody Interview i) {
		return interviewService.save(i);
	}
	
}
