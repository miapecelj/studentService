package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.dto.MyExamPeriodDto;
import mia.pecelj.be.entity.ExamPeriodEntity;

@Mapper(componentModel = "spring")
public interface MyExamPeriodEntityDtoMapper {
	ExamPeriodEntity toEntity(MyExamPeriodDto dto);
	MyExamPeriodDto toDto(ExamPeriodEntity entity);
}
