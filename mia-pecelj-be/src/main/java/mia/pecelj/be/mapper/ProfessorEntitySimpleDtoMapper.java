package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.SimpleProfessorDto;
import mia.pecelj.be.entity.ProfessorEntity;

@Mapper(componentModel = "spring")
public interface ProfessorEntitySimpleDtoMapper {
	SimpleProfessorDto toDto(ProfessorEntity entity);

	ProfessorEntity toEntity(SimpleProfessorDto dto);

}
