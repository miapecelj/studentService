package mia.pecelj.be.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mia.pecelj.be.dto.ExamRegistrationDto;
import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.dto.ProfessorSubjectDto;
import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.entity.ExamRegistrationEntity;
import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.entity.ProfessorSubjectEntity;
import mia.pecelj.be.entity.StudentEntity;

@Component
public class MyStudentEntityDtoMapper {
	@Autowired
	CityEntityDtoMapper cityMapper;

	@Autowired
	StudentEntitySimpleDtoMapper studentMapper;
	@Autowired
	ExamEntityDtoMapper examMapper;
	@Autowired
	ExamEntitySimpleDtoMapper simpleExamMapper;
	public StudentDto toDto(StudentEntity entity) {
		StudentDto dto = new StudentDto();
		dto.setCurrentYearOfStudy(entity.getCurrentYearOfStudy());
		dto.setIndexNumber(entity.getIndexNumber());
		dto.setIndexYear(entity.getIndexYear());
		dto.setAddress(entity.getAddress());
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstname());
		dto.setId(entity.getId());
		dto.setLastname(entity.getLastname());
		dto.setCity(cityMapper.toDto(entity.getCity()));
		for (ExamRegistrationEntity examRegistration : entity.getExams()) {
			dto.getExams().add(new ExamRegistrationDto(studentMapper.toDto(entity),simpleExamMapper.toDto(examRegistration.getExam())));
		}
		return dto;
	}

	public StudentEntity toEntity(StudentDto dto) {
		StudentEntity entity = new StudentEntity();
		entity.setCurrentYearOfStudy(dto.getCurrentYearOfStudy());
		entity.setIndexNumber(dto.getIndexNumber());
		entity.setIndexYear(dto.getIndexYear());
		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setId(dto.getId());
		entity.setCity(cityMapper.toEntity(dto.getCity()));
		for (ExamRegistrationDto examRegistration : dto.getExams()) {
			entity.getExams().add(new ExamRegistrationEntity(entity,simpleExamMapper.toEntity(examRegistration.getExam())));
		}
		return entity;

	}

}
