package mia.pecelj.be.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import mia.pecelj.be.dto.ExamRegistrationDto;
import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.dto.ProfessorSubjectDto;
import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.entity.ExamRegistrationEntity;
import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.entity.ProfessorSubjectEntity;
import mia.pecelj.be.entity.StudentEntity;

public class MyStudentEntityDtoMapper {
	CityEntityDtoMapper cityMapper;
	StudentEntitySimpleDtoMapper simpleStudentMapper;
	ExamEntitySimpleDtoMapper simpleExamMapper;
	@Autowired
	public MyStudentEntityDtoMapper(CityEntityDtoMapper cityMapper,StudentEntitySimpleDtoMapper simpleStudentMapper,ExamEntitySimpleDtoMapper simpleExamMapper) {
		this.cityMapper = cityMapper;
		this.simpleStudentMapper=simpleStudentMapper;
		this.simpleExamMapper =simpleExamMapper;

	}
	
	public StudentDto toDto(StudentEntity entity) {
		StudentDto dto = new StudentDto();
		dto.setAddress(entity.getAddress());
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstname());
		dto.setId(entity.getId());
		dto.setLastname(entity.getLastname());
		dto.setCurrentYearOfStudy(entity.getCurrentYearOfStudy());
		dto.setIndexNumber(entity.getIndexNumber());
		dto.setIndexYear(entity.getIndexYear());
		dto.setCity(cityMapper.toDto(entity.getCity()));
		for (ExamRegistrationEntity examRegistration : entity.getExams()) {
			dto.getExams().add(new ExamRegistrationDto(simpleStudentMapper.toDto(entity),
					simpleExamMapper.toDto(examRegistration.getExam())));
		}
		return dto;
	}

	public StudentEntity toEntity(StudentDto dto) {
		StudentEntity entity = new StudentEntity();
		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setId(dto.getId());
		entity.setCity(cityMapper.toEntity(dto.getCity()));
		entity.setIndexNumber(dto.getIndexNumber());
		entity.setIndexYear(dto.getIndexYear());
		entity.setCurrentYearOfStudy(dto.getCurrentYearOfStudy());
		for (ExamRegistrationDto examRegistration : dto.getExams()) {
			entity.getExams()
					.add(new ExamRegistrationEntity(entity, simpleExamMapper.toEntity(examRegistration.getExam())));
		}
		return entity;

	}

}
