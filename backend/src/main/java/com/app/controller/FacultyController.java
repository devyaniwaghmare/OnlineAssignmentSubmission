package com.app.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.helper.JWTUtil;
import com.app.pojos.Assignment;
import com.app.pojos.Faculty;
import com.app.pojos.LoginDetailsWithToken;
import com.app.pojos.UserType;
import com.app.repository.AssignmentRepo;
import com.app.repository.FacultyCouresSubjectMappingRepo;
import com.app.repository.FacultyRepo;
import com.app.service.CustomUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RequestMapping("/faculty")
public class FacultyController {

	
	public FacultyController() {
		// TODO Auto-generated constructor stub
		System.out.println("in controller of "+getClass().getName());
	}
	@Autowired
	FacultyRepo fdao;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private AssignmentRepo assignmentService;
	
	@Autowired
	private FacultyCouresSubjectMappingRepo facultyMappingService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping
	public ResponseEntity<?> getAllFaculties()
	{
		List<Faculty> faculties = fdao.findAll();
		return new ResponseEntity<>(faculties, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET,path="/{fid}")
	public ResponseEntity<?> getFacultyById(@PathVariable int fid)
	{
		
		System.out.println(" getFacultyById");
		Optional<Faculty> faculty = fdao.findById(fid);
		if(faculty.isPresent())
		{
			System.out.println(faculty.get());
			return new ResponseEntity<>(faculty.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<?> addFacultyDetails(@RequestBody Faculty newFaculty)
	{
		
		System.out.println("in addFacultyDetails");
		Faculty addedFaculty;
		UserDetails userDeatils;
		String token;
		try
		{
			 addedFaculty=fdao.save(newFaculty);
			 this.authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(addedFaculty.getEmailId(),addedFaculty.getPassword()));
			 userDeatils = this.customUserDetailsService.loadUserByUsername(addedFaculty.getEmailId());
		     token = this.jwtUtil.generateToken(userDeatils);
		}
		catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	
		return ResponseEntity.ok(new LoginDetailsWithToken(addedFaculty.getFid(), UserType.FACULTY, token));
	}
	
   @RequestMapping(method = RequestMethod.PUT,path ="/{fid}")
    public ResponseEntity<?> updateFacultyDetails(@PathVariable int fid,@RequestBody @Valid Faculty updateFaculty)
    {
       
	   try {
		   
		   System.out.println(updateFaculty);
		   Optional<Faculty> faculty = fdao.findById(fid);
		   if(faculty.isPresent())
		   {
			   faculty.get().setFirstName(updateFaculty.getFirstName());
			   faculty.get().setLastName(updateFaculty.getLastName());
			   faculty.get().setGender(updateFaculty.getGender());
			   faculty.get().setEmailId(updateFaculty.getEmailId());
			   faculty.get().setPassword(updateFaculty.getPassword());
			   faculty.get().setDOB(updateFaculty.getDOB());
			   Faculty f = fdao.save(faculty.get());
			   return new ResponseEntity<>(f, HttpStatus.OK);
		   }
		   
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	   }
	   catch (Exception e) {
		   return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
           	}
	   
	   
    	
    }
    
    @DeleteMapping("/{fid}")
    public ResponseEntity<?> deleteFacultyDetails(@PathVariable int fid)
    {
    	Optional<Faculty> facultyToDeleted = fdao.findById(fid);
    	if(facultyToDeleted.isPresent())
    	{
    		
    		fdao.delete(facultyToDeleted.get());
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
   /*@GetMapping("/{emailId}/{password}")
   public ResponseEntity<?> authenticateStudent(@PathVariable String emailId,@PathVariable String password)
   {
	  
	 Optional<Faculty> f = fdao.findByEmailIdAndPassword(emailId, password);
	 if(f.isPresent())
	 {
		return new ResponseEntity<>(f.get(), HttpStatus.OK);
	 }
	 return new ResponseEntity<>("failed to authenticate", HttpStatus.OK);
	   
   }*/
}
