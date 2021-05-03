package mia.pecelj.be.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="exam") 
public class ExamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExamRegistrationEntity> students = new ArrayList<ExamRegistrationEntity>();

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
	
	public List<ExamRegistrationEntity> getStudents() {
		return students;
	}
	public void setStudents(List<ExamRegistrationEntity> students) {
		this.students = students;
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
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ExamEntity exam = (ExamEntity) o;
		return Objects.equals(id, exam.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public String toString() {
		return "ExamEntity [id=" + id + ", examPeriod=" + examPeriod + ", subject=" + subject + ", professor="
				+ professor + ", dateOfExam=" + dateOfExam + "]";
	}
	
}
