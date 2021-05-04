package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.SimpleStudentDto;
import mia.pecelj.be.entity.StudentEntity;

@Mapper(componentModel = "spring")
public interface StudentEntitySimpleDtoMapper {
	SimpleStudentDto toDto(StudentEntity entity);

	StudentEntity toEntity(SimpleStudentDto dto);

}
