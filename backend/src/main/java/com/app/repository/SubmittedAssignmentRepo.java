package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.SubmittedAssignment;

public interface SubmittedAssignmentRepo extends JpaRepository<SubmittedAssignment, Integer>{

	Object findByAssignmentId(int assignmentId);

}
