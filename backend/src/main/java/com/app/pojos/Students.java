package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "STUDENTS_DETAILS")
public class Students {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studId;
	@Column(name="FIRST_NAME",length = 20)
	private String firstName;
	@Column(name="LAST_NAME",length = 20)
	private String lastName;
	@Column(unique = true)
	private String phone;
	private String gender;
	@Column(name="EMAIL",unique = true)
	@NotBlank
	private String emailId;
	@NotBlank
	private String password;
	
	//@OneToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name="courseId")
	//private Courses courses;
	
	
	@ManyToOne
	@JoinColumn(name="courseId",nullable = false)
	private Courses courses;
	
	public Students() {
		super();
		System.out.println("in constructor "+getClass().getName());
		
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

	

	public Integer getStudId() {
		return studId;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	/*@Override
	public String toString() {
		return "Students [studId=" + studId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", gender=" + gender + ", emailId=" + emailId + ", password=" + password + ", courses=" + courses
				+ "]";
	}*/


	
	
}
