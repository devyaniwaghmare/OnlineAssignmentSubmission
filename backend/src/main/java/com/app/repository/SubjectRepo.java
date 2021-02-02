package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Subjects;

public interface SubjectRepo extends JpaRepository<Subjects, Integer>{

}
