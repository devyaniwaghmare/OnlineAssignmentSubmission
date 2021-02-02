package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Assignments")
public class Assignment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer assignmentId;
	
	@OneToOne
	@JoinColumn(name = "mid",unique = true)
	private FacultyCourseSubjectMapping map;
	
	@Column(name = "assignment_name",nullable = false,unique = true)
	private String assignmentDocument;
	
	private String instruction;
	
	@Column(name="Assign_date")
	private LocalDate assignDate ;
	
	
	@Column(name="Due_Date")
	private String dueDate ;
	
	private int marks;
	
	public Assignment() {
		System.out.println("in constructor of "+getClass().getName());
	}

	

	public Assignment(FacultyCourseSubjectMapping map, String assignmentDocument, String instruction, LocalDate assignDate,
			String dueDate, int marks) {
		super();
		this.map = map;
		this.assignmentDocument = assignmentDocument;
		this.instruction = instruction;
		this.assignDate = assignDate;
		this.dueDate = dueDate;
		this.marks = marks;
	}



	public Integer getAssignmentId() {
		return assignmentId;
	}

	public FacultyCourseSubjectMapping getMap() {
		return map;
	}

	public void setMap(FacultyCourseSubjectMapping map) {
		this.map = map;
	}

	public String getAssignmentDocument() {
		return assignmentDocument;
	}

	public void setAssignmentDocument(String assignmentDocument) {
		this.assignmentDocument = assignmentDocument;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}


   

	public LocalDate getAssignDate() {
		return assignDate;
	}



	public void setAssignDate(LocalDate assignDate) {
		this.assignDate =  LocalDate.now();
	}



	public String getDueDate() {
		return dueDate;
	}



	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}



	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	

}
