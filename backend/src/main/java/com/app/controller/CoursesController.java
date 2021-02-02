package com.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.app.pojos.Courses;
import com.app.pojos.Subjects;
import com.app.repository.CourseRepo;
import com.app.repository.SubjectRepo;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/courses")
public class CoursesController {

	@Autowired
	CourseRepo cdao;
	
	@Autowired
	SubjectRepo sdao;
	
	public CoursesController() {
		System.out.println("in CoursesController");
	}
	@PostMapping
	public ResponseEntity<?> addCourse(@RequestBody @Valid Courses course)
	{
		
		return new ResponseEntity<>(cdao.save(course), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCourses()
	{
	    List<Courses> course_list = cdao.findAll();
	    System.out.println(course_list);
		return new ResponseEntity<>(course_list, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET,path="/{cid}")
	public ResponseEntity<?> getSubjectPerCourses(@PathVariable int cid)
	{
		
		Optional<Courses> c = cdao.findById(cid);
		if(c.isPresent())
		{
			
			
			return  ResponseEntity.ok(c.get());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT,path="/{cid}")
	public ResponseEntity<?> updateCourseDetails(@PathVariable int cid,@RequestBody @Valid Courses updatedCourse)
	{
		//updatedCourse.getCourseId()
		Optional<Courses> c1 = cdao.findById(cid);
		if(c1.isPresent())
		{
			Courses course = c1.get();
			course.setCourseName(updatedCourse.getCourseName());
			course.setSubjects(updatedCourse.getSubjects());
			cdao.save(course);
			return new ResponseEntity<>(course, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteCourseById(@PathVariable int cid)
	{
		
		Optional<Courses> c = cdao.findById(cid);
		if(c.isPresent())
		{
			
		 	Courses course = c.get();
		 	course.getSubjects().forEach(u->u.removeCourses(course));
			cdao.delete(c.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{cid}/{sid}")
	public ResponseEntity<?> removeSubjectFromCourse(@PathVariable int cid,@PathVariable int sid)
	{
		
		try {
			System.out.println(cid+" "+sid);
			Optional<Courses> c = cdao.findById(cid);
			Optional<Subjects> s = sdao.findById(sid);
			Courses course = c.get();
			Subjects sub = s.get();
			course.getSubjects().remove(s.get());
			sub.getCourses().remove(c.get());
			cdao.save(course);
			sdao.save(sub);
			return ResponseEntity.ok("Deleted Successfully");
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
}
