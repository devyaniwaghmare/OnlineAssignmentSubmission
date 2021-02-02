package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Courses;
import com.app.pojos.Students;

public interface StudentsRepo extends JpaRepository<Students, Integer>{

	List<Students> findByCourses(Courses c);

	Optional<Students> findByEmailId(String emailId);

	Optional<Students> findByEmailIdAndPassword(String emailId, String password);

}
