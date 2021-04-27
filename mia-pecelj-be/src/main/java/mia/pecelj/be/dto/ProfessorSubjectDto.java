package mia.pecelj.be.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.entity.ProfessorSubjectId;
import mia.pecelj.be.entity.SubjectEntity;

public class ProfessorSubjectDto implements MyDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfessorSubjectId id;
	@JsonBackReference
	private ProfessorEntity professor;
	private SubjectEntity subject;
	private LocalDate assignDate = LocalDate.now();
	public ProfessorSubjectDto() {
		// TODO Auto-generated constructor stub
	}
	public ProfessorSubjectDto(ProfessorEntity professor, SubjectEntity subject) {
		super();
		this.professor = professor;
		this.subject = subject;
		this.id=new ProfessorSubjectId(professor.getId(),subject.getId());
	}
	public ProfessorSubjectId getId() {
		return id;
	}
	public void setId(ProfessorSubjectId id) {
		this.id = id;
	}
	public ProfessorEntity getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorEntity professor) {
		this.professor = professor;
	}
	public SubjectEntity getSubject() {
		return subject;
	}
	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}
	public LocalDate getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(LocalDate assignDate) {
		this.assignDate = assignDate;
	}
	@Override
	public String toString() {
		return  "subject=" + subject + ", assignDate=" + assignDate;
	}
	
	
	

}
