package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.entity.ExamPeriodEntity;

@Mapper(componentModel = "spring", uses = {
		ExamEntityDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ExamPeriodEntityDtoMapper {
	ExamPeriodEntity toEntity(ExamPeriodDto dto);

	ExamPeriodDto toDto(ExamPeriodEntity entity);

}
