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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Subjects")

public class Subjects {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="subId")
	private Integer subjectId;
	@Column(name="subName",unique = true)
	@NotBlank
	private String subjectName;
	@NotBlank
	private String description;
    
	//Inverse of association
   
	
	//,cascade = {CascadeType.PERSIST,CascadeType.MERGE}
	@ManyToMany(mappedBy = "subjects",fetch = FetchType.EAGER)
	@JsonIgnoreProperties("subjects")
	
	private Set<Courses> courses = new HashSet<Courses>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "subject",orphanRemoval = true)
	private List<FacultyCourseSubjectMapping> mapping = new ArrayList<>();
	
	
	public Subjects() {
		System.out.println("In Subject constructor..");
	}

   


	public Integer getSubjectId() {
		return subjectId;
	}

	



	public Subjects(@NotBlank String subjectName, String description, Set<Courses> courses,
			List<FacultyCourseSubjectMapping> mapping) {
		super();
		this.subjectName = subjectName;
		this.description = description;
		this.courses = courses;
		this.mapping = mapping;
	}





	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public Set<Courses> getCourses() {
		return courses;
	}

	public void setCourses(Set<Courses> courses) {
		this.courses = courses;
	}

	

	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public List<FacultyCourseSubjectMapping> getMapping() {
		return mapping;
	}




	public void setMapping(List<FacultyCourseSubjectMapping> mapping) {
		this.mapping = mapping;
	}




	public void addCourses(Courses course)
	{
		System.out.println("in addCourses helper method");
		courses.add(course);
		course.addSubjects(this);
	}
	public void removeCourses(Courses course)
	{
		System.out.println("in removeCourses helper method");
		courses.remove(course);
		course.removeSubjects(this);
	}

   /*----------------------------------------------------------------------------------------*/
	public void addMapping(FacultyCourseSubjectMapping fmap)
	{
		System.out.println("in addMapping helper function");
		mapping.add(fmap);
		fmap.setSubject(this);
	}
	
	public void removeMapping(FacultyCourseSubjectMapping fmap)
	{
		System.out.println("in removeMapping helper function");
		mapping.remove(fmap);
		fmap.setFaculty(null);
	}
	@Override
	public String toString() {
		return "Subjects [subjectId=" + subjectId + ", subjectName=" + subjectName + "]";
	}



	
	
}
