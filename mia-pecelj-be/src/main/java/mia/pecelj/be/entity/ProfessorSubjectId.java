package mia.pecelj.be.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProfessorSubjectId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="professor_id")
	private Long professorId;
	@Column(name="subject_id")
	private Long subjectId;
	public ProfessorSubjectId() {
		// TODO Auto-generated constructor stub
	}
	public ProfessorSubjectId(Long professorId, Long subjectId) {
		super();
		this.professorId = professorId;
		this.subjectId = subjectId;
	}
	public Long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ProfessorSubjectId that = (ProfessorSubjectId) o;
        return Objects.equals(professorId, that.professorId) &&
               Objects.equals(subjectId, that.subjectId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(professorId, subjectId);
    }
	@Override
	public String toString() {
		return "ProfessorSubjectId [professorId=" + professorId + ", subjectId=" + subjectId + "]";
	}
    
	
	

}
