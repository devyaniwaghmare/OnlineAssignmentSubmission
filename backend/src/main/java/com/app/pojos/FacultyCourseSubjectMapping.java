package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="faculty_course_subject_mapping")
public class FacultyCourseSubjectMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mid;
	
	@ManyToOne
    @JsonIgnoreProperties("subjects")
	@JoinColumn(name="courseId",nullable = false)
	private Courses courses;
	
	@ManyToOne
	@JoinColumn(name="fid",nullable = false)
	private Faculty faculty;

	@ManyToOne
	@JsonIgnoreProperties("courses")
	@JoinColumn(name="subjectId")
	private Subjects subject;
	
	@Column(columnDefinition = "boolean default false")
	private boolean status;
	
	public FacultyCourseSubjectMapping() {
		super();
	}

	

	
	public FacultyCourseSubjectMapping(Courses courses, Faculty faculty, Subjects subject, boolean status) {
		super();
		this.courses = courses;
		this.faculty = faculty;
		this.subject = subject;
		this.status = status;
	}




	public Integer getMid() {
		return mid;
	}

	

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}




	public boolean isStatus() {
		return status;
	}




	public void setStatus(boolean status) {
		this.status = status;
	}




	public void setMid(Integer mid) {
		this.mid = mid;
	}

	
	/*@Override
	public String toString() {
		return "FacultyCourseSubjectMapping [mid=" + mid + ", courses=" + courses + ", faculty=" + faculty
				+ ", subject=" + subject + "]";
	}*/

	

	

	
	
	
}
