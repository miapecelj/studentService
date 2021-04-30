package mia.pecelj.be.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExamPeriodDto implements MyDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;
	private List<ExamDto> exams = new ArrayList<ExamDto>();
	public ExamPeriodDto() {
		// TODO Auto-generated constructor stub
	}
	public ExamPeriodDto(Long id, String name, LocalDate startDate, LocalDate endDate, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
	}
	
	public void addExam(ExamDto dto){
		exams.add(dto);
	}
	public void removeExam(ExamDto dto) {
		exams.remove(dto);
	}
	public List<ExamDto> getExams() {
		return exams;
	}
	public void setExams(List<ExamDto> exams) {
		this.exams = exams;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	@Override
	public String toString() {
		return "ExamPeriodDto [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", active=" + active + ", exams=" + exams + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		ExamPeriodDto other = (ExamPeriodDto) obj;
		if (active != other.active)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
