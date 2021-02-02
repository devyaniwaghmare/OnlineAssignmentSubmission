package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Faculty;

public interface FacultyRepo extends JpaRepository<Faculty, Integer>{

	Optional<Faculty> findByEmailId(String email);
	Optional<Faculty> findByEmailIdAndPassword(String emailId,String password);
}
