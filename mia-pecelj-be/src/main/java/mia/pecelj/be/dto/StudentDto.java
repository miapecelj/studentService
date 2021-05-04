package mia.pecelj.be.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StudentDto implements MyDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	@NotNull
	@Size(min = 4)
	@Size(max = 4)
	private String indexNumber;
	@NotNull
	@Min(2000)
	@Max(2100)
	private int indexYear;
	@NotNull
	@Size(min = 3)
	private String firstname;
	@NotNull
	@Size(min = 3)
	private String lastname;
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
	private String email;
	@Size(min = 3)
	private String address;
	private CityDto city;
	@NotNull
	private int currentYearOfStudy;
	List<ExamRegistrationDto> exams = new ArrayList<ExamRegistrationDto>();

	public StudentDto() {
		// TODO Auto-generated constructor stub
	}

	public StudentDto(long id, String indexNumber, int indexYear, String firstname, String lastname, String email,
			String address, CityDto city, int currentYearOfStudy) {
		super();
		this.id = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.city = city;
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public List<ExamRegistrationDto> getExams() {
		return exams;
	}

	public void setExams(List<ExamRegistrationDto> exams) {
		this.exams = exams;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", address=" + address + ", city=" + city
				+ ", currentYearOfStudy=" + currentYearOfStudy + "]";
	}
//	public void addSubject(ExamDto exam) {
//		ExamRegistrationDto examRegistration = new ExamRegistrationDto(this, exam);
//		exams.add(examRegistration);
//		
//	}
//
//
//	public void removeSubject(ExamEntity exam) {
//		for (Iterator<ExamRegistrationDto> iterator = exams.iterator(); iterator.hasNext();) {
//			ExamRegistrationDto examRegistration = iterator.next();
//
//			if (examRegistration.getStudent().equals(this) && examRegistration.getExam().equals(exam)) {
//				iterator.remove();
//				examRegistration.setStudent(null);
//				examRegistration.setExam(null);
//			}
//		}
//		
//	}

}
