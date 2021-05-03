package mia.pecelj.be.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import mia.pecelj.be.entity.StudentExamId;

public class ExamRegistrationDto implements MyDto{
	StudentExamId id;
	SimpleStudentDto student;
	SimpleExamDto exam;
	LocalDate registrationDate;
	public ExamRegistrationDto() {
		// TODO Auto-generated constructor stub
	}
	public ExamRegistrationDto(SimpleStudentDto student, SimpleExamDto exam) {
		super();
		this.student = student;
		this.exam = exam;
		this.id = new StudentExamId(student.getId(),exam.getId());
	}
	public StudentExamId getId() {
		return id;
	}
	public void setId(StudentExamId id) {
		this.id = id;
	}
	public SimpleStudentDto getStudent() {
		return student;
	}
	public void setStudent(SimpleStudentDto student) {
		this.student = student;
	}
	public SimpleExamDto getExam() {
		return exam;
	}
	public void setExam(SimpleExamDto exam) {
		this.exam = exam;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Override
	public String toString() {
		return "ExamRegistrationDto [id=" + id + ", student=" + student + ", exam=" + exam + ", registrationDate="
				+ registrationDate + "]";
	} 
	
	
	
	

}
