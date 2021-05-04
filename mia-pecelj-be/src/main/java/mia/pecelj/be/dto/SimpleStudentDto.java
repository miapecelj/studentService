package mia.pecelj.be.dto;

public class SimpleStudentDto implements MyDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long id;
	private String indexNumber;
	private int indexYear;
	private String firstname;
	private String lastname;
	private String email;
	private String address;
	private CityDto city;
	private int currentYearOfStudy;

	public SimpleStudentDto() {
		// TODO Auto-generated constructor stub
	}

	public SimpleStudentDto(Long id, String indexNumber, int indexYear, String firstname, String lastname, String email,
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
		return "SimpleStudentDto [indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", address=" + address + ", city=" + city
				+ ", currentYearOfStudy=" + currentYearOfStudy + "]";
	}

}
