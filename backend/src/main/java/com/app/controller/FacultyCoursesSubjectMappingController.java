package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.app.pojos.Faculty;
import com.app.pojos.FacultyCourseSubjectMapping;
import com.app.service.FacultyCoursesSubjectMappingService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/mapping")
public class FacultyCoursesSubjectMappingController {

	
	@Autowired
	private FacultyCoursesSubjectMappingService service;
	
	public FacultyCoursesSubjectMappingController() {
		System.out.println("in constructor "+getClass().getName());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllFacultyCoursesSubjectMappingDetails()
	{
		System.out.println("in gate");
		return new ResponseEntity<>(service.getAllFacultyCourseSubjectMapping(), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<?> addFacultyCoursesSubjectMappingDetails(@RequestBody  FacultyCourseSubjectMapping f)
	{
		System.out.println("in add faculty method");
		return ResponseEntity.ok(service.addFacultyCourseSubjectMapping(f));
	}
	
	@GetMapping("/{mid}")
	public ResponseEntity<?> getFacultyCoursesSubjectMappingDetailsById(@PathVariable int mid)
	{
		FacultyCourseSubjectMapping f;
		try {
			
			f = service.getFacultyCourseSubjectMappingByMid(mid);
			
		} catch (RuntimeException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(f);
	}
	
	@DeleteMapping("/{mid}")
	public ResponseEntity<?> FacultyCoursesSubjectMappingDetailsById(@PathVariable int mid)
	{
		try {
			
			service.DeleteFacultyCourseSubjectMappingById(mid);
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		catch (RuntimeException e) {
		   return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
}
