package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.helper.JWTUtil;
import com.app.pojos.Faculty;
import com.app.pojos.Login;
import com.app.pojos.LoginDetailsWithToken;
import com.app.pojos.Students;
import com.app.pojos.UserType;
import com.app.repository.FacultyRepo;
import com.app.repository.StudentsRepo;
import com.app.service.CustomUserDetailsService;

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManger;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private FacultyRepo repo;
	
	@Autowired
	private StudentsRepo srepo;
	
	@RequestMapping(value="/token",method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody Login userCredential) throws Exception
	{
		System.out.println(userCredential);
		try {
			
			this.authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getEmailId(),userCredential.getPassword()));
		}
		catch (Exception e) {
		    e.printStackTrace();
		    throw new Exception("bad credential");
		}
		
		
		UserDetails userDeatils = this.customUserDetailsService.loadUserByUsername(userCredential.getEmailId());
		String token = this.jwtUtil.generateToken(userDeatils);
	
		
		System.out.println("token"+token);
		
		Optional<Faculty> f = repo.findByEmailId(userCredential.getEmailId());
		Optional<Students> s = srepo.findByEmailId(userCredential.getEmailId());
		if(f.isPresent())
		{
			if(f.get().getEmailId().equals("naina@gmail.com"))
			{
				return ResponseEntity.ok(new LoginDetailsWithToken(f.get().getFid(), UserType.ADMIN, token));
			}
			return ResponseEntity.ok(new LoginDetailsWithToken(f.get().getFid(), UserType.FACULTY, token));
		}
		else
		{
			return ResponseEntity.ok(new LoginDetailsWithToken(s.get().getStudId(), UserType.STUDENT, token));
		}
		
		
	}
	
	
}
