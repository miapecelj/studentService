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

@Entity
@Table(name = "professor_subject")
public class ProfessorSubjectEntity implements MyEntity {

	@EmbeddedId
	private ProfessorSubjectId id;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("professorId")
	private ProfessorEntity professor;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("subjectId")
	private SubjectEntity subject;
	@Column(name = "assign_date")
	private LocalDate assignDate = LocalDate.now();

	public ProfessorSubjectEntity() {
		// TODO Auto-generated constructor stub
	}

	public ProfessorSubjectEntity(ProfessorEntity professor, SubjectEntity subject) {
		super();
		this.professor = professor;
		this.subject = subject;
		this.id = new ProfessorSubjectId(professor.getId(), subject.getId());
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
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ProfessorSubjectEntity that = (ProfessorSubjectEntity) o;
		return Objects.equals(professor, that.professor) && Objects.equals(subject, that.subject);
	}

	@Override
	public int hashCode() {
		return Objects.hash(professor, subject);
	}

	@Override
	public String toString() {
		return "ProfessorSubjectEntity [id=" + id + ", subject=" + subject + ", assignDate=" + assignDate + "]";
	}

}
