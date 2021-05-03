package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mia.pecelj.be.dto.ExamDto;
import mia.pecelj.be.entity.ExamEntity;

@Mapper(componentModel = "spring", uses = {
		MyProfessorEntityDtoMapper.class,SubjectEntityDtoMapper.class,ExamPeriodEntitySimpleDtoMapper.class,StudentEntitySimpleDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ExamEntityDtoMapper {
	ExamEntity toEntity(ExamDto dto);
	ExamDto toDto(ExamEntity entity);

}



