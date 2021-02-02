package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Courses;

public interface CourseRepo extends JpaRepository<Courses, Integer>{


	Optional<Courses> findByCourseName(String subName);

}
