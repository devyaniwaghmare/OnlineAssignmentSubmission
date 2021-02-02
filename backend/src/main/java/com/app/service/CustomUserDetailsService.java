package com.app.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.pojos.Faculty;
import com.app.pojos.Students;
import com.app.repository.FacultyRepo;
import com.app.repository.StudentsRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	FacultyRepo fdao;
	@Autowired
	StudentsRepo sdao; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	
		Optional<Faculty> faculty = fdao.findByEmailId(username);
		Optional<Students> student = sdao.findByEmailId(username);
			
			if(faculty.isPresent())
			{
				return new User(faculty.get().getEmailId(),faculty.get().getPassword(),new ArrayList<>());
			}
			else if(student.isPresent())
			{
				return new User(student.get().getEmailId(),student.get().getPassword(),new ArrayList<>());
			}
			else {
				 throw new UsernameNotFoundException("User not found");
			}
	     
		
		
	}

}
