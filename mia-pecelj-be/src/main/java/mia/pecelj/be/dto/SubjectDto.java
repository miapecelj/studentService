package mia.pecelj.be.dto;


import mia.pecelj.be.entity.Semester;


public class SubjectDto implements MyDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String description;
	private int noOfEspb;
	private int yearOfStudy;
	private Semester semester;
	public SubjectDto() {
		// TODO Auto-generated constructor stub
	}
	public SubjectDto(long id, String name, String description, int noOfEspb, int yearOfStudy, Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfEspb = noOfEspb;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
	}
	
	
	public SubjectDto(String name, String description, int noOfEspb, int yearOfStudy, Semester semester) {
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
	public int getnoOfEspb() {
		return noOfEspb;
	}
	public void setnoOfEspb(int noOfEspb) {
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
		SubjectDto other = (SubjectDto) obj;
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
