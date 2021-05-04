package mia.pecelj.be.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExamDto implements MyDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private ProfessorDto professor;
	private SubjectDto subject;
	private SimpleExamPeriodDto examPeriod;
	private LocalDate dateOfExam;
	private List<ExamRegistrationDto> students = new ArrayList<ExamRegistrationDto>();

	public ExamDto() {
		// TODO Auto-generated constructor stub
	}

	public ExamDto(Long id, ProfessorDto professor, SubjectDto subject, SimpleExamPeriodDto examPeriod,
			LocalDate dateOfExam) {
		super();
		this.id = id;
		this.professor = professor;
		this.subject = subject;
		this.examPeriod = examPeriod;
		this.dateOfExam = dateOfExam;
	}

	public List<ExamRegistrationDto> getStudents() {
		return students;
	}

	public void setStudents(List<ExamRegistrationDto> students) {
		this.students = students;
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

	public SimpleExamPeriodDto getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(SimpleExamPeriodDto examPeriod) {
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
