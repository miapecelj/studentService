package mia.pecelj.be.dto;

import java.time.LocalDate;

public class MyExamPeriodDto implements MyDto{
	private Long id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;
	public MyExamPeriodDto() {
		// TODO Auto-generated constructor stub
	}
	public MyExamPeriodDto(Long id, String name, LocalDate startDate, LocalDate endDate, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
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
		return "MyExamPeriodDto [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", active=" + active + "]";
	}
	
	

}
