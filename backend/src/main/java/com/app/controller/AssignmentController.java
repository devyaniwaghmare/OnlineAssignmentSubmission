package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Assignment;
import com.app.pojos.Courses;
import com.app.pojos.FacultyCourseSubjectMapping;
import com.app.repository.AssignmentRepo;
import com.app.repository.CourseRepo;
import com.app.repository.FacultyCouresSubjectMappingRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/assignment")
public class AssignmentController {

	@Autowired 
	private AssignmentRepo repo;
	
	@Autowired
	private CourseRepo crepo;
	
	@Autowired
	private FacultyCouresSubjectMappingRepo frepo;
	
	public AssignmentController() {
		System.out.println("in constructor of "+getClass().getName());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllAssignmentDetails()
	{
		
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{cid}")
	public ResponseEntity<?> findAssignmentDetailsByCourseId(@PathVariable int cid)
	{
		
		Optional<Courses> c = crepo.findById(cid);
		List<FacultyCourseSubjectMapping> list = frepo.findByCourses(c.get());
		List<Assignment> assignment = new ArrayList<>();
		for(FacultyCourseSubjectMapping f:list)
		{
			assignment.add(repo.findByMap(f));
		}
		return new ResponseEntity<>(assignment, HttpStatus.OK);
		
	}
	 
}
