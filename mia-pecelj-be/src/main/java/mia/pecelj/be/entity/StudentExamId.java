package mia.pecelj.be.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentExamId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "student_id")
	Long studentId;
	@Column(name = "exam_id")
	Long examId;

	public StudentExamId() {
		// TODO Auto-generated constructor stub
	}

	public StudentExamId(Long studentId, Long examId) {
		super();
		this.studentId = studentId;
		this.examId = examId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	@Override
	public String toString() {
		return "StudentExamId [studentId=" + studentId + ", examId=" + examId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examId == null) ? 0 : examId.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentExamId other = (StudentExamId) obj;
		if (examId == null) {
			if (other.examId != null)
				return false;
		} else if (!examId.equals(other.examId))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}

}
