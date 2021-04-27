package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mia.pecelj.be.dto.ProfessorSubjectDto;
import mia.pecelj.be.entity.ProfessorSubjectEntity;

@Mapper(componentModel="spring",uses= {ProfessorEntityDtoMapper.class,SubjectEntityDtoMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfessorSubjectEntityDtoMapper {
	ProfessorSubjectEntity toEntity(ProfessorSubjectDto dto);
	@Mapping(target="professor",ignore=true)
	ProfessorSubjectDto toDto(ProfessorSubjectEntity entity);

}
