package mia.pecelj.be.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="exam") 
public class ExamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private ExamPeriodEntity examPeriod;
	@ManyToOne
	@JoinColumn(name="subject_id")
	private SubjectEntity subject;
	@ManyToOne
	@JoinColumn(name="professor_id")
	private ProfessorEntity professor;
	@Column(name="date_of_exam")
	private LocalDate dateOfExam;
	public ExamEntity() {
		// TODO Auto-generated constructor stub
	}
	public ExamEntity(Long id, ExamPeriodEntity examPeriod, SubjectEntity subject, ProfessorEntity professor,
			LocalDate dateOfExam) {
		super();
		this.id = id;
		this.examPeriod = examPeriod;
		this.subject = subject;
		this.professor = professor;
		this.dateOfExam = dateOfExam;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ExamPeriodEntity getExamPeriod() {
		return examPeriod;
	}
	public void setExamPeriod(ExamPeriodEntity examPeriod) {
		this.examPeriod = examPeriod;
	}
	public SubjectEntity getSubject() {
		return subject;
	}
	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}
	public ProfessorEntity getProfessor() {
		return professor;
	}
	public void setProfessor(ProfessorEntity professor) {
		this.professor = professor;
	}
	public LocalDate getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(LocalDate dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	@Override
	public String toString() {
		return "ExamEntity [id=" + id + ", examPeriod=" + examPeriod + ", subject=" + subject + ", professor="
				+ professor + ", dateOfExam=" + dateOfExam + "]";
	}
	
}
