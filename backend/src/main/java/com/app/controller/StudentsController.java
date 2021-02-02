package com.app.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.helper.JWTUtil;
import com.app.pojos.Courses;
import com.app.pojos.Faculty;
import com.app.pojos.LoginDetailsWithToken;
import com.app.pojos.Students;
import com.app.pojos.UserType;
import com.app.repository.CourseRepo;
import com.app.repository.StudentsRepo;
import com.app.service.CustomUserDetailsService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/students")
public class StudentsController {

	
	
	public StudentsController() {
		// TODO Auto-generated constructor stub
		System.out.println("in constructor of "+getClass().getName());
	}
	
	@Autowired
	StudentsRepo dao;
	
	@Autowired
	CourseRepo cdao;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	@GetMapping
	
	public ResponseEntity<?> getAllStudents()
	{
	
		List<Students> studentsList = dao.findAll();
		return new ResponseEntity<>(studentsList, HttpStatus.OK);
	}
	
	@GetMapping("/{sid}")
	public ResponseEntity<?> getStudentDetailsById(@PathVariable int sid)
	{
		Optional<Students> student = dao.findById(sid);
		if(student.isPresent())
		{
			Students s = student.get();
			Optional<Courses> c = cdao.findById(s.getCourses().getCourseId());
			System.out.println(c.get().getSubjects());
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	//@PostMapping
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<?> addStudentDetails(@RequestBody @Valid Students newStudent)
	{

		Students addStudent;
		UserDetails userDeatils;
		String token;
		try {
	
				 addStudent = dao.save(newStudent);
				 this.authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(addStudent.getEmailId(),addStudent.getPassword()));
			     userDeatils = this.customUserDetailsService.loadUserByUsername(addStudent.getEmailId());
				 token = this.jwtUtil.generateToken(userDeatils);
		  } 
		catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(new LoginDetailsWithToken(addStudent.getStudId(), UserType.STUDENT, token));
	}
	
	@PutMapping("/{sid}")
	public ResponseEntity<?> updateExstingStudentDetails(@PathVariable int sid,@RequestBody Students studentDetails)
	{
		Optional<Students> student = dao.findById(sid);
		if(student.isPresent())
		{
			Students updatedStudent = student.get();
			updatedStudent.setFirstName(studentDetails.getFirstName());
			updatedStudent.setLastName(studentDetails.getLastName());
			updatedStudent.setEmailId(studentDetails.getEmailId());
			updatedStudent.setPassword(studentDetails.getPassword());
			updatedStudent.setCourses(studentDetails.getCourses());
			return new ResponseEntity<>(dao.save(updatedStudent),HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	@DeleteMapping("/{sid}")
	public ResponseEntity<?> deletetudentDetailsById(@PathVariable int sid)
	{
		Optional<Students> student = dao.findById(sid);
		if(student.isPresent())
		{
			dao.delete(student.get());
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	   /*@GetMapping("/{emailId}/{password}")
	   public ResponseEntity<?> authenticateStudent(@PathVariable String emailId,@PathVariable String password)
	   {
		  
		 Optional<Students> f = dao.findByEmailIdAndPassword(emailId, password);
		 if(f.isPresent())
		 {
			return new ResponseEntity<>(f.get(), HttpStatus.OK);
		 }
		 return new ResponseEntity<>("failed to authenticate", HttpStatus.OK);
		   
	   }*/

}
