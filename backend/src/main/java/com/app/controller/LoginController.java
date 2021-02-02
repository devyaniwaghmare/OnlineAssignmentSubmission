package com.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Faculty;
import com.app.pojos.Login;
import com.app.pojos.LoginDetailsDto;
import com.app.pojos.Students;
import com.app.pojos.UserType;
import com.app.repository.FacultyRepo;
import com.app.repository.StudentsRepo;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	FacultyRepo fdao;
	@Autowired
	StudentsRepo sdao;
	
	public LoginController() {
		// TODO Auto-generated constructor stub
		System.out.println("in constructor of "+getClass().getName());
	}
	
	@PostMapping
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid Login userCredential)
	{
		LoginDetailsDto loginDetails;
		Optional<Faculty> faculty = fdao.findByEmailId(userCredential.getEmailId());
		Optional<Students> student = sdao.findByEmailId(userCredential.getEmailId());
		if(faculty.isPresent())
		{
			loginDetails = new LoginDetailsDto(faculty.get().getFid(),UserType.FACULTY);
			return new ResponseEntity<>(loginDetails, HttpStatus.OK);
		}
		else if(student.isPresent())
		{
			loginDetails = new LoginDetailsDto(student.get().getStudId(),UserType.STUDENT);
			return new ResponseEntity<>(loginDetails, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Failed to login", HttpStatus.NOT_FOUND);
		}
		
		
	}
}
