package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import mia.pecelj.be.dto.ExamDto;
import mia.pecelj.be.entity.ExamEntity;

@Mapper(componentModel = "spring", uses = {
		ProfessorEntityDtoMyMapper.class,SubjectEntityDtoMapper.class,MyExamPeriodEntityDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ExamEntityDtoMapper {
	ExamEntity toEntity(ExamDto dto);
	ExamDto toDto(ExamEntity entity);

}



