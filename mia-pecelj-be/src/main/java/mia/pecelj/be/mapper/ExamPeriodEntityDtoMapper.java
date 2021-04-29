package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.entity.ExamPeriodEntity;

@Mapper(componentModel = "spring")
public interface ExamPeriodEntityDtoMapper {
	ExamPeriodEntity toEntity(ExamPeriodDto dto);
	ExamPeriodDto toDto(ExamPeriodEntity entity);

}
