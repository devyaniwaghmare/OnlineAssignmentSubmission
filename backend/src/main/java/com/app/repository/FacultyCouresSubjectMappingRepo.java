package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Courses;
import com.app.pojos.Faculty;
import com.app.pojos.FacultyCourseSubjectMapping;
import com.sun.el.stream.Optional;

public interface FacultyCouresSubjectMappingRepo extends JpaRepository<FacultyCourseSubjectMapping, Integer>{

	List<FacultyCourseSubjectMapping> findByCourses(Courses courses);

	int findByFaculty(Faculty faculty);

	
}
