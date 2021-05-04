package mia.pecelj.be.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(name = "student", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@NaturalIdCache
public class StudentEntity implements Serializable, MyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NaturalId
	@Column(name = "index_number", length = 4)
	private String indexNumber;
	@NaturalId
	@Column(name = "index_year")
	private int indexYear;
	private String firstname;
	private String lastname;
	private String email;
	private String address;
	@Column(name = "current_year_of_study")
	private int currentYearOfStudy;
	@ManyToOne
	@JoinColumn(name = "city_code")
	private CityEntity city;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ExamRegistrationEntity> exams = new ArrayList<ExamRegistrationEntity>();

	public StudentEntity() {
		// TODO Auto-generated constructor stub
	}

	public List<ExamRegistrationEntity> getExams() {
		return exams;
	}

	public void setExams(List<ExamRegistrationEntity> exams) {
		this.exams = exams;
	}

	public StudentEntity(Long id, String indexNumber, int indexYear, String firstname, String lastname, String email,
			String address, int currentYearOfStudy, CityEntity city) {
		super();
		this.id = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.currentYearOfStudy = currentYearOfStudy;
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public int getIndexYear() {
		return indexYear;
	}

	public void setIndexYear(int indexYear) {
		this.indexYear = indexYear;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String address) {
		this.address = address;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", address=" + address
				+ ", currentYearOfStudy=" + currentYearOfStudy + ", city=" + city + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		StudentEntity student = (StudentEntity) o;
		return Objects.equals(id, student.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public void addSubject(ExamEntity exam) {
		ExamRegistrationEntity examRegistration = new ExamRegistrationEntity(this, exam);
		exams.add(examRegistration);
		exam.getStudents().add(examRegistration);

	}

	public void removeSubject(ExamEntity exam) {
		for (Iterator<ExamRegistrationEntity> iterator = exams.iterator(); iterator.hasNext();) {
			ExamRegistrationEntity examRegistration = iterator.next();

			if (examRegistration.getStudent().equals(this) && examRegistration.getExam().equals(exam)) {
				iterator.remove();
				examRegistration.getExam().getStudents().remove(examRegistration);
				examRegistration.setStudent(null);
				examRegistration.setExam(null);
			}
		}

	}

}
