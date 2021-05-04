package mia.pecelj.be.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.dto.ProfessorSubjectDto;
import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.entity.ProfessorSubjectEntity;

@Component
public class MyProfessorEntityDtoMapper {
	CityEntityDtoMapper cityMapper;
	TitleEntityDtoMapper titleMapper;
	SubjectEntityDtoMapper subjectMapper;
	SubjectEntitySimpleDtoMapper mySubjectMapper;
	ProfessorEntitySimpleDtoMapper myProfessorMapper;

	@Autowired
	public MyProfessorEntityDtoMapper(CityEntityDtoMapper cityMapper, TitleEntityDtoMapper titleMapper,
			SubjectEntityDtoMapper subjectMapper, SubjectEntitySimpleDtoMapper mySubjectMapper,
			ProfessorEntitySimpleDtoMapper myProfessorMapper) {
		this.cityMapper = cityMapper;
		this.titleMapper = titleMapper;
		this.subjectMapper = subjectMapper;
		this.mySubjectMapper = mySubjectMapper;
		this.myProfessorMapper = myProfessorMapper;
	}

	public ProfessorDto toDto(ProfessorEntity entity) {
		ProfessorDto dto = new ProfessorDto();
		dto.setAddress(entity.getAddress());
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstname());
		dto.setId(entity.getId());
		dto.setLastname(entity.getLastname());
		dto.setPhone(entity.getPhone());
		dto.setReelectionDate(entity.getReelectionDate());
		dto.setCity(cityMapper.toDto(entity.getCity()));
		dto.setTitle(titleMapper.toDto(entity.getTitle()));
		for (ProfessorSubjectEntity professorSubject : entity.getSubjects()) {
			dto.getSubjects().add(new ProfessorSubjectDto(myProfessorMapper.toDto(entity),
					mySubjectMapper.toDto(professorSubject.getSubject()), professorSubject.getAssignDate()));
		}
		return dto;
	}

	public ProfessorEntity toEntity(ProfessorDto dto) {
		ProfessorEntity entity = new ProfessorEntity();
		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setId(dto.getId());
		entity.setPhone(dto.getPhone());
		entity.setReelectionDate(dto.getReelectionDate());
		entity.setCity(cityMapper.toEntity(dto.getCity()));
		entity.setTitle(titleMapper.toEntity(dto.getTitle()));
		for (ProfessorSubjectDto professorSubject : dto.getSubjects()) {
			entity.getSubjects()
					.add(new ProfessorSubjectEntity(entity, mySubjectMapper.toEntity(professorSubject.getSubject())));
		}
		return entity;

	}

}
