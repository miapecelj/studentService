package mia.pecelj.be.dto;



import mia.pecelj.be.entity.Semester;

public class SimpleSubjectDto {
	private long id;
	private String name;
	private String description;
	private int noOfEspb;
	private int yearOfStudy;
	private Semester semester;
	public SimpleSubjectDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SimpleSubjectDto(long id, String name, String description, int noOfEspb, int yearOfStudy, Semester semester) {
		super();
		this.id = id;
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
		return "MySubjectDto [id=" + id + ", name=" + name + ", description=" + description + ", noOfEspb=" + noOfEspb
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + "]";
	}
	

}
