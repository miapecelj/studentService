package mia.pecelj.be.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;


public class ExamDto implements MyDto{
	private Long id;
	private ProfessorDto professor;
	private SubjectDto subject;
	@JsonBackReference
	private MyExamPeriodDto examPeriod;
	private LocalDate dateOfExam;
	public ExamDto() {
		// TODO Auto-generated constructor stub
	}
	public ExamDto(Long id, ProfessorDto professor, SubjectDto subject, MyExamPeriodDto examPeriod,
			LocalDate dateOfExam) {
		super();
		this.id = id;
		this.professor = professor;
		this.subject = subject;
		this.examPeriod = examPeriod;
		this.dateOfExam = dateOfExam;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProfessorDto getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorDto professor) {
		this.professor = professor;
	}
	public SubjectDto getSubject() {
		return subject;
	}
	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}
	public MyExamPeriodDto getExamPeriod() {
		return examPeriod;
	}
	public void setExamPeriod(MyExamPeriodDto examPeriod) {
		this.examPeriod = examPeriod;
	}
	public LocalDate getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(LocalDate dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	@Override
	public String toString() {
		return "ExamDto [id=" + id + ", professor=" + professor + ", subject=" + subject + ", examPeriod=" + examPeriod
				+ ", dateOfExam=" + dateOfExam + "]";
	}
	
	
	

}