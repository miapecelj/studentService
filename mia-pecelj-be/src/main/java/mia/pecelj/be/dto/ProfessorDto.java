package mia.pecelj.be.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mia.pecelj.be.entity.ProfessorSubjectEntity;
import mia.pecelj.be.entity.SubjectEntity;

public class ProfessorDto implements MyDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String address;
	private CityDto city;
	private TitleDto title;
	private LocalDate reelectionDate;
	@JsonIgnore
	private List<ProfessorSubjectDto> subjects=new ArrayList<ProfessorSubjectDto>();
	public ProfessorDto() {
		// TODO Auto-generated constructor stub
	}
	public ProfessorDto(long id, String firstname, String lastname, String email, String phone, String address,
			CityDto city, TitleDto title, LocalDate reelectionDate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.title = title;
		this.reelectionDate = reelectionDate;
	}
	
	
	
	public List<ProfessorSubjectDto> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<ProfessorSubjectDto> subjects) {
		this.subjects = subjects;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public TitleDto getTitle() {
		return title;
	}
	public void setTitle(TitleDto title) {
		this.title = title;
	}
	public LocalDate getReelectionDate() {
		return reelectionDate;
	}
	public void setReelectionDate(LocalDate reelectionDate) {
		this.reelectionDate = reelectionDate;
	}
	public void addSubject(SubjectDto subject) {
		ProfessorSubjectDto professorSubject = new ProfessorSubjectDto(this,subject);
		subjects.add(professorSubject);
		subject.getProfessors().add(professorSubject);
	}
	public void removeSubject(SubjectDto subject) {
		for (Iterator<ProfessorSubjectDto> iterator = subjects.iterator();
	             iterator.hasNext(); ) {
	            ProfessorSubjectDto professorSubject = iterator.next();
	 
	            if (professorSubject.getProfessor().equals(this) &&
	                    professorSubject.getSubject().equals(subject)) {
	                iterator.remove();
	                professorSubject.getSubject().getProfessors().remove(professorSubject);
	                professorSubject.setProfessor(null);
	                professorSubject.setSubject(null);
	            }
	        }
	}
	@Override
	public String toString() {
		return "ProfessorDto [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", city=" + city + ", title=" + title
				+ ", reelectionDate=" + reelectionDate + ", subjects=" + subjects + "]";
	}
	
	
	
	
	

}
