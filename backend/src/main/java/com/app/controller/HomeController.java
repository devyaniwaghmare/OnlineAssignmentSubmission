package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.repository.SubmittedAssignmentRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/submittedAssignment")
public class HomeController {

	
	
	@Autowired
	SubmittedAssignmentRepo repo;
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		String text = "this is private page";
		text+="you dont have access to it";
		return text;
	}
	@GetMapping("/{assignmentId}")
	public ResponseEntity<?> getAllSubmittedAssignmentDetailsByAssignmentId(@RequestParam int assignmentId)
	{
		
		try {
			return ResponseEntity.ok(repo.findByAssignmentId(assignmentId));
		} 
		catch (Exception e) {
			return ResponseEntity.ok("not Fond assignment with this id");
			
		}
		
		
	}
	
}

