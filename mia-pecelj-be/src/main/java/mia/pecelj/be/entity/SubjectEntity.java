package mia.pecelj.be.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subject")
public class SubjectEntity implements MyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	@Column(name = "no_of_espb")
	private int noOfEspb;
	@Column(name = "year_of_study")
	private int yearOfStudy;
	@Enumerated(EnumType.STRING)
	private Semester semester;
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProfessorSubjectEntity> professors = new ArrayList<ProfessorSubjectEntity>();

	public SubjectEntity() {
		// TODO Auto-generated constructor stub
	}

	public SubjectEntity(long id,
			@NotNull @Size(min = 3, message = "Name should have atleast 2 characters") String name,
			@Size(max = 200, message = "Description must have less then 200 characters") String description,
			@NotNull @Positive int noOfEspb, @NotNull @Max(4) @Min(1) int yearOfStudy, Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfEspb = noOfEspb;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
	}

	public List<ProfessorSubjectEntity> getProfessors() {
		return professors;
	}

	public void setProfessors(List<ProfessorSubjectEntity> professors) {
		this.professors = professors;
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
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		SubjectEntity subject = (SubjectEntity) o;
		return Objects.equals(id, subject.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "SubjectEntity [id=" + id + ", name=" + name + ", description=" + description + ", noOfEspb=" + noOfEspb
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + "]";
	}

}
