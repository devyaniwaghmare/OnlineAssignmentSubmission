package com.app.pojos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="courses")

public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;
	
	@Column(name="course_name",unique=true)
	@NotBlank
	private String courseName;
	
	private String description;
	//owning side of association

	//cascade = {CascadeType.PERSIST,CascadeType.MERGE},
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="course_subjects_join_table",
	joinColumns = @JoinColumn(name="courseId"),
	inverseJoinColumns = @JoinColumn(name="subjectId")
	)
	
	@JsonIgnoreProperties("courses")
	private Set<Subjects> subjects = new HashSet<Subjects>();
	
	
	/*------------------------------students enrolled -------------------------------------*/
	@JsonIgnore
	@OneToMany(mappedBy = "courses",orphanRemoval = true)
	private List<Students> enrolledStudentsForCourse = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "courses",orphanRemoval = true)
	private List<FacultyCourseSubjectMapping> mapping = new ArrayList<>();
	
	public Courses() {
		System.out.println("in courses constructor");
	}
    
	


	public Integer getCourseId() {
		return courseId;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public Set<Subjects> getSubjects() {
		return subjects;
	}


	public void setSubjects(Set<Subjects> subjects) {
		this.subjects = subjects;
	}

	public List<Students> getEnrolledStudentsForCourse() {
		return enrolledStudentsForCourse;
	}


	public void setEnrolledStudentsForCourse(List<Students> enrolledStudentsForCourse) {
		this.enrolledStudentsForCourse = enrolledStudentsForCourse;
	}
 
	

	public void addSubjects(Subjects sub)
	{
		System.out.println("in addSubjects helper method");
		subjects.add(sub);
		sub.getCourses().add(this);
	}
	public void removeSubjects(Subjects sub)
	{
		System.out.println("in removeSubjects helper method");
		subjects.remove(sub);
		sub.getCourses().remove(this);
	}
    
	public List<FacultyCourseSubjectMapping> getMapping() {
		return mapping;
	}


	public void setMapping(List<FacultyCourseSubjectMapping> mapping) {
		this.mapping = mapping;
	}


	public void addStudents(Students student)
	{
		System.out.println("in addStudents helper method");
		enrolledStudentsForCourse.add(student);
		student.setCourses(this);
		
	}
	
	public void removeStudents(Students student)
	{
		System.out.println("in removeStudents helper method");
		enrolledStudentsForCourse.remove(student);
		student.setCourses(null);
	}

/*--------------------------------------------------------------------------------*/
	public void addMapping(FacultyCourseSubjectMapping fmap)
	{
		System.out.println("in addMapping helper function");
		mapping.add(fmap);
		fmap.setCourses(this);
	}
	
	public void removeMapping(FacultyCourseSubjectMapping fmap)
	{
		System.out.println("in removeMapping helper function");
		mapping.remove(fmap);
		fmap.setCourses(null);
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

	/*@Override
	public String toString() {
		return "Courses [courseId=" + courseId + ", courseName=" + courseName + ", subjects=" + subjects + "]";
	}*/
	
	

     
     
}
