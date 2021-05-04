package mia.pecelj.be.dto;

import java.time.LocalDate;

public class SimpleProfessorDto implements MyDto {

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

	public SimpleProfessorDto() {
		// TODO Auto-generated constructor stub
	}

	public SimpleProfessorDto(long id, String firstname, String lastname, String email, String phone, String address,
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

	@Override
	public String toString() {
		return "MyProfessorDto [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", city=" + city + ", title=" + title
				+ ", reelectionDate=" + reelectionDate + "]";
	}

}
