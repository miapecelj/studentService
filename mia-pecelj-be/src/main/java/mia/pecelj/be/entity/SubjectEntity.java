package mia.pecelj.be.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Entity
@Table(name = "subject")
public class SubjectEntity implements MyEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min=3, message="Name should have atleast 2 characters")
	private String name;
	@Size(max=200,message="Description must have less then 200 characters")
	private String description;
	@NotNull
	@Positive
	@Column(name="no_of_espb")
	private int noOfEspb;
	@NotNull
	@Max(4)
	@Min(1)
	@Column(name="year_of_study")
	private int yearOfStudy;
	@Enumerated(EnumType.STRING)
	private Semester semester;
	public SubjectEntity() {
		// TODO Auto-generated constructor stub
	}
	public SubjectEntity(long id, String name, String description, int noOfEspb, int yearOfStudy, Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfEspb = noOfEspb;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
	}
	
	
	public SubjectEntity(String name, String description, int noOfEspb, int yearOfStudy, Semester semester) {
		super();
		this.name = name;
		this.description = description;
		this.noOfEspb = noOfEspb;
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
	public int getNoOfEspb() {
		return noOfEspb;
	}
	public void setNoOfEspb(int noOfEspb) {
		this.noOfEspb = noOfEspb;
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
		return "Subject [id=" + id + ", name=" + name + ", description=" + description + ", noOfESPB=" + noOfEspb
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
