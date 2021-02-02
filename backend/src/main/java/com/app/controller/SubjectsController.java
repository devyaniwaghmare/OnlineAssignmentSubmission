package com.app.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Subjects;
import com.app.repository.SubjectRepo;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/subjects")
public class SubjectsController {

	@Autowired
	SubjectRepo sdao;
	
	@GetMapping
	public ResponseEntity<?> getAllSubjects()
	{
		List<Subjects> subjets = sdao.findAll();
		return new ResponseEntity<>(subjets, HttpStatus.OK);
	}
	
	@GetMapping("/{sid}")
	public ResponseEntity<?> getIdBySubject(@PathVariable int sid)
	{
		Optional<Subjects> s = sdao.findById(sid);
		if(s.isPresent())
		{
			return new ResponseEntity<>(s.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> addSubject(@RequestBody Subjects newSub)
	{
		
		try {
			return new  ResponseEntity<>(sdao.save(newSub),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("duplicate entry", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{sid}")
	public ResponseEntity<?> updateSubject(@PathVariable int sid,@RequestBody Subjects sub)
	{
		Optional<Subjects> s = sdao.findById(sub.getSubjectId());
		if(s.isPresent())
		{
			Subjects subject = s.get();
			subject.setSubjectName(sub.getSubjectName());
			subject.setCourses(sub.getCourses());
			sdao.save(subject);
			return new ResponseEntity<>(subject, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{sid}")
	public ResponseEntity<?> deleteSubjectById(@PathVariable int sid)
	{
		Optional<Subjects> sub = sdao.findById(sid);
		if(sub.isPresent())
		{    
		   sub.get().getCourses().forEach(u->u.removeSubjects(sub.get()));
		   sdao.delete(sub.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
