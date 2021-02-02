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
@Table(name = "submited_assignment")
public class SubmittedAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer submittedAssignment;
	
	
	
	@Column(name = "studentEmailId",nullable = false)
	private String emailId;
	

	@Column(name = "solved_assignment",nullable = false,unique = true)
	private String solvedAssignmentDoct;
	
	@Column(name="Submission_date")
	private String submissionDate ;
	
    @Column(columnDefinition = "boolean default false")
	private boolean status;
	
	private int obtaniedMarks;

	@Column(name = "assignmentId",nullable = false)
	private int assignmentId;
	
	public SubmittedAssignment() {
		super();
	}

	



	

	public SubmittedAssignment(String emailId, String solvedAssignmentDoct, String submissionDate, boolean status,
			int obtaniedMarks, int assignmentId) {
		super();
		this.emailId = emailId;
		this.solvedAssignmentDoct = solvedAssignmentDoct;
		this.submissionDate = submissionDate;
		this.status = status;
		this.obtaniedMarks = obtaniedMarks;
		this.assignmentId = assignmentId;
	}



	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getSubmittedAssignment() {
		return submittedAssignment;
	}


	public void setSubmittedAssignment(Integer submittedAssignment) {
		this.submittedAssignment = submittedAssignment;
	}


	public String getSolvedAssignmentDoct() {
		return solvedAssignmentDoct;
	}

	public void setSolvedAssignmentDoct(String solvedAssignmentDoct) {
		this.solvedAssignmentDoct = solvedAssignmentDoct;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = LocalDate.now().toString();
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getObtaniedMarks() {
		return obtaniedMarks;
	}

	public void setObtaniedMarks(int obtaniedMarks) {
		this.obtaniedMarks = obtaniedMarks;
	}


	public int getAssignmentId() {
		return assignmentId;
	}



	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	
	
	
}
