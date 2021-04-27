package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.entity.ProfessorEntity;

@Mapper(componentModel="spring",uses= {CityEntityDtoMapper.class,TitleEntityDtoMapper.class,ProfessorSubjectEntityDtoMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfessorEntityDtoMapper {
	ProfessorEntity toEntity(ProfessorDto dto);
	ProfessorDto toDto(ProfessorEntity entity);

}
