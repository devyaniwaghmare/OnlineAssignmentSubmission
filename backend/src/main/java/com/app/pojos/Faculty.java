package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="FACULTY_TABLES")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer fid;
	@Column(name="FIRST_NAME",length = 20)
	private String firstName;
	@Column(name="LAST_NAME",length = 20)
	private String lastName;
	@Column(name="user_phone_number",unique = true)
	private String phone;
	private String gender;
	@Column(name="EMAIL",unique = true)
	@NotBlank
	private String emailId; 
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate DOB;
	
	@NotBlank
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "faculty",orphanRemoval = true)
	private List<FacultyCourseSubjectMapping> mapping = new ArrayList<>();
	
	public Faculty() {
		super();
		System.out.println("in constructor "+getClass().getName());
	}





	public Faculty(String firstName, String lastName, String phone, String gender, @NotBlank String emailId,
			LocalDate dOB, @NotBlank String password, List<FacultyCourseSubjectMapping> mapping) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.gender = gender;
		this.emailId = emailId;
		DOB = dOB;
		this.password = password;
		this.mapping = mapping;
	}


	public Integer getFid() {
		return fid;
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}

	
	/*public void addStudent(Student s)
	{
		//p ---> c
		students.add(s);
		//c ---> p
		s.setSelectedCourse(this);
	}
	public void removeStudent(Student s)
	{
		students.remove(s);
		s.setSelectedCourse(null);
	}*/



    

	public List<FacultyCourseSubjectMapping> getMapping() {
		return mapping;
	}





	public LocalDate getDOB() {
		return DOB;
	}





	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}





	public void setMapping(List<FacultyCourseSubjectMapping> mapping) {
		this.mapping = mapping;
	}



   

	public void addMapping(FacultyCourseSubjectMapping fmap)
	{
		System.out.println("in addMapping helper function");
		mapping.add(fmap);
		fmap.setFaculty(this);
	}
	
	public void removeMapping(FacultyCourseSubjectMapping fmap)
	{
		System.out.println("in removeMapping helper function");
		mapping.remove(fmap);
		fmap.setFaculty(null);
	}





	/*@Override
	public String toString() {
		return "Faculty [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", gender=" + gender
				+  ", emailId=" + emailId + ", password=" + password + ", mapping=" + mapping + "]";
	}*/

	
	
	
	
}
