package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.SimpleExamDto;
import mia.pecelj.be.entity.ExamEntity;

@Mapper(componentModel = "spring")
public interface ExamEntitySimpleDtoMapper {
	ExamEntity toEntity(SimpleExamDto dto);

	SimpleExamDto toDto(ExamEntity entity);

}
