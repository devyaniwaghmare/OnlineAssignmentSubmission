package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.FacultyCourseSubjectMapping;
import com.app.repository.FacultyCouresSubjectMappingRepo;

@Service
public class FacultyCoursesSubjectMappingService {

	@Autowired
	FacultyCouresSubjectMappingRepo repo;
	
	public FacultyCoursesSubjectMappingService() {
		
		System.out.println("in constructor of"+getClass().getName());
	}
	
	public List<FacultyCourseSubjectMapping> getAllFacultyCourseSubjectMapping()
	{
		return repo.findAll();
	}
	
	public FacultyCourseSubjectMapping addFacultyCourseSubjectMapping(FacultyCourseSubjectMapping f)
	{
		System.out.println(f);
		return repo.save(f);
	}
	
	public FacultyCourseSubjectMapping getFacultyCourseSubjectMappingByMid(int mid) throws RuntimeException
	{
		Optional<FacultyCourseSubjectMapping> f;
		System.out.println("in  getAllFacultyCourseSubjectMappingByMid");
		try {
			 f = repo.findById(mid);
		}
		catch (RuntimeException e) {
			throw e;
		}
		return f.get();
	
	}
	
	public void DeleteFacultyCourseSubjectMappingById(int mid)
	{
		Optional<FacultyCourseSubjectMapping> f;
		System.out.println("in  getAllFacultyCourseSubjectMappingByMid");
		try {
			 f = repo.findById(mid);
			 repo.delete(f.get());
		}
		catch (RuntimeException e) {
			throw e;
		}
	
	}
}
