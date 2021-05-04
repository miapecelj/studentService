package mia.pecelj.be.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "exam_registration")
public class ExamRegistrationEntity {

	@EmbeddedId
	StudentExamId id;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("studentId")
	StudentEntity student;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("examId")
	ExamEntity exam;
	@Column(name = "registration_date")
	@CreatedDate
	LocalDate registrationDate = LocalDate.now();

	public ExamRegistrationEntity() {
		// TODO Auto-generated constructor stub
	}

	public ExamRegistrationEntity(StudentEntity student, ExamEntity exam) {
		super();
		this.student = student;
		this.exam = exam;
		this.id = new StudentExamId(student.getId(), exam.getId());
	}

	public StudentExamId getId() {
		return id;
	}

	public void setId(StudentExamId id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public ExamEntity getExam() {
		return exam;
	}

	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "ExamRegistrationEntity [id=" + id + ", student=" + student + ", exam=" + exam + ", registrationDate="
				+ registrationDate + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ExamRegistrationEntity that = (ExamRegistrationEntity) o;
		return Objects.equals(student, that.student) && Objects.equals(exam, that.exam);
	}

	@Override
	public int hashCode() {
		return Objects.hash(student, exam);
	}

}
