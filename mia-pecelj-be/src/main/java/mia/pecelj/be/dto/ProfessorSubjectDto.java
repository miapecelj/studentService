package mia.pecelj.be.dto;

import java.time.LocalDate;

import mia.pecelj.be.entity.ProfessorSubjectId;

public class ProfessorSubjectDto implements MyDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProfessorSubjectId id;
	private SimpleProfessorDto professor;
	private SimpleSubjectDto subject;
	private LocalDate assignDate = LocalDate.now();

	public ProfessorSubjectDto() {
		// TODO Auto-generated constructor stub
	}

	

	public ProfessorSubjectId getId() {
		return id;
	}

	public ProfessorSubjectDto(SimpleProfessorDto professor, SimpleSubjectDto subject, LocalDate assignDate) {
	super();
	this.professor = professor;
	this.subject = subject;
	this.assignDate = assignDate;
	this.id = new ProfessorSubjectId(professor.getId(),subject.getId());
}

	public void setId(ProfessorSubjectId id) {
		this.id = id;
	}

	

	public LocalDate getAssignDate() {
		return assignDate;
	}

	public SimpleProfessorDto getProfessor() {
		return professor;
	}

	public void setProfessor(SimpleProfessorDto professor) {
		this.professor = professor;
	}

	public SimpleSubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SimpleSubjectDto subject) {
		this.subject = subject;
	}

	public void setAssignDate(LocalDate assignDate) {
		this.assignDate = assignDate;
	}

	@Override
	public String toString() {
		return "ProfessorSubjectDto [id=" + id + ", subject=" + subject + ", assignDate=" + assignDate + "]";
	}

}
