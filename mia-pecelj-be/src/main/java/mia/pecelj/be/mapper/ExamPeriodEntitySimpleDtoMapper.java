package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.dto.SimpleExamPeriodDto;
import mia.pecelj.be.entity.ExamPeriodEntity;

@Mapper(componentModel = "spring")
public interface ExamPeriodEntitySimpleDtoMapper {
	ExamPeriodEntity toEntity(SimpleExamPeriodDto dto);
	SimpleExamPeriodDto toDto(ExamPeriodEntity entity);
}
