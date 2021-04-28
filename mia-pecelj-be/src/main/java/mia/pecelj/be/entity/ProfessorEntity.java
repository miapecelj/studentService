package mia.pecelj.be.entity;

import java.time.LocalDate;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="professor")
public class ProfessorEntity implements MyEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min=3)
	private String firstname;
	@NotNull
	@Size(min=3)
	private String lastname;
	@Pattern(regexp="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
	private String email;
	@Size(min=3)
	private String address;
	@ManyToOne
	@JoinColumn(name = "city_code")
	private CityEntity city;
	@Size(min=9)
	private String phone;
	@Column(name="reelection_date")
	private LocalDate reelectionDate;
	@ManyToOne
	@JoinColumn(name = "title")
	private TitleEntity title;
	@OneToMany(
	        mappedBy = "professor",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<ProfessorSubjectEntity> subjects=new ArrayList<ProfessorSubjectEntity>();
	public ProfessorEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public ProfessorEntity(long id, @NotNull @Size(min = 3) String firstName, @NotNull @Size(min = 3) String lastName,
			@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$") String email, @Size(min = 3) String address,
			CityEntity city, @Size(min = 9) String phone, LocalDate reelectionDate, TitleEntity title) {
		super();
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.reelectionDate = reelectionDate;
		this.title = title;
	}
	
	

	
	public void addSubject(SubjectEntity subject) {
		ProfessorSubjectEntity professorSubject = new ProfessorSubjectEntity(this,subject);
		subjects.add(professorSubject);
		subject.getProfessors().add(professorSubject);
	}
	public void removeSubject(SubjectEntity subject) {
		for (Iterator<ProfessorSubjectEntity> iterator = subjects.iterator();
	             iterator.hasNext(); ) {
	            ProfessorSubjectEntity professorSubject = iterator.next();
	 
	            if (professorSubject.getProfessor().equals(this) &&
	                    professorSubject.getSubject().equals(subject)) {
	                iterator.remove();
	                professorSubject.getSubject().getProfessors().remove(professorSubject);
	                professorSubject.setProfessor(null);
	                professorSubject.setSubject(null);
	            }
	        }
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
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

	public List<ProfessorSubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<ProfessorSubjectEntity> subjects) {
		this.subjects = subjects;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getReelectionDate() {
		return reelectionDate;
	}
	public void setReelectionDate(LocalDate reelectionDate) {
		this.reelectionDate = reelectionDate;
	}
	public TitleEntity getTitle() {
		return title;
	}
	public void setTitle(TitleEntity title) {
		this.title = title;
	}

	

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorEntity professor = (ProfessorEntity) o;
        return Objects.equals(id, professor.id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	@Override
	public String toString() {
		return "ProfessorEntity [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", address=" + address + ", city=" + city + ", phone=" + phone + ", reelectionDate=" + reelectionDate
				+ ", title=" + title + ", subjects=" + subjects + "]";
	}
    
	
	

}
