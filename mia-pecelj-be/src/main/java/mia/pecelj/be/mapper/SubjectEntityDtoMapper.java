package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.entity.SubjectEntity;

@Mapper(componentModel = "spring", uses = {
		ProfessorEntitySimpleDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SubjectEntityDtoMapper {
	SubjectDto toDto(SubjectEntity entity);

	SubjectEntity toEntity(SubjectDto dto);

}
