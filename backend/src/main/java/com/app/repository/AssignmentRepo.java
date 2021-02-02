package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Assignment;
import com.app.pojos.FacultyCourseSubjectMapping;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer>{

	Assignment findByMap(FacultyCourseSubjectMapping f);

	

}
