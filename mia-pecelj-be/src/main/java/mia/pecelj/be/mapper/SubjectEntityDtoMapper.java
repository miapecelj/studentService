package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.entity.SubjectEntity;

@Mapper(componentModel = "spring",uses = {
		MyProfessorEntityDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SubjectEntityDtoMapper {
	//@Mapping(target = "professors", ignore = true)
	SubjectDto toDto(SubjectEntity entity);

	//@Mapping(target = "professors", ignore = true)
	SubjectEntity toEntity(SubjectDto dto);

}
