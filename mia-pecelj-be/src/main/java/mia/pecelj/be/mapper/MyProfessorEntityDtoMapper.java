package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.MyProfessorDto;
import mia.pecelj.be.entity.ProfessorEntity;

@Mapper(componentModel = "spring")
public interface MyProfessorEntityDtoMapper {
	MyProfessorDto toDto(ProfessorEntity entity);
	ProfessorEntity toEntity(MyProfessorDto dto);

}
