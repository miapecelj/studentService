package mia.pecelj.be.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
@Entity
@Table(name="student")
@NaturalIdCache
public class StudentEntity implements Serializable,MyEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NaturalId
	private int indexNumber;
	@NaturalId
	private int indexYear;
	private String firstname;
	private String lastname;
	private String email;
	private String address;
	@ManyToOne
	@JoinColumn(name = "city_code")
	private CityEntity city;
	public StudentEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public StudentEntity(long id, int indexNumber, int indexYear, String firstname, String lastname, String email,
			String address, CityEntity city) {
		super();
		this.id = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.city = city;
	}

	public StudentEntity(int indexNumber, int indexYear, String firstname, String lastname, String email, String address,
			CityEntity city) {
		super();
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.city = city;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(int indexNumber) {
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
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", adress=" + address + ", city=" + city
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + indexNumber;
		result = prime * result + indexYear;
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
		StudentEntity other = (StudentEntity) obj;
		if (id != other.id)
			return false;
		if (indexNumber != other.indexNumber)
			return false;
		if (indexYear != other.indexYear)
			return false;
		return true;
	}
	

}
