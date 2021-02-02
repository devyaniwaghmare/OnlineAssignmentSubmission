package com.app.controller;

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

import com.app.pojos.Courses;
import com.app.pojos.Students;
import com.app.repository.CourseRepo;
import com.app.repository.StudentsRepo;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/studcourses")
public class StudentCourseController {

	public StudentCourseController() {
		// TODO Auto-generated constructor stub
		System.out.println("in constructor of "+getClass().getName());
	}
	
	@Autowired
	StudentsRepo sado;
	@Autowired
	CourseRepo cdao;
	
	@GetMapping("/{cid}")
	public ResponseEntity<?> getAllStudentsByCourse(@PathVariable int cid)
	{
		Optional<Courses> c = cdao.findById(cid);
		if(c.isPresent())
		{
			List<Students> studenstInOneCourse = sado.findByCourses(c.get());
			return new ResponseEntity<>(studenstInOneCourse,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
}
