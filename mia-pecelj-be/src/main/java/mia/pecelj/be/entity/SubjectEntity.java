package mia.pecelj.be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "subject")
public class SubjectEntity implements MyEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	@Column(name="no_of_espb")
	private int noOfESP;
	@Column(name="year_of_study")
	private int yearOfStudy;
	@Enumerated(EnumType.STRING)
	private Semester semester;
	public SubjectEntity() {
		// TODO Auto-generated constructor stub
	}
	public SubjectEntity(long id, String name, String description, int noOfESP, int yearOfStudy, Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfESP = noOfESP;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNoOfESP() {
		return noOfESP;
	}
	public void setNoOfESP(int noOfESP) {
		this.noOfESP = noOfESP;
	}
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public Semester getSemester() {
		return semester;
	}
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", description=" + description + ", noOfESP=" + noOfESP
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SubjectEntity other = (SubjectEntity) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	

}
