package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.entity.StudentEntity;
import mia.pecelj.be.entity.SubjectEntity;

@Mapper(componentModel = "spring")
public interface SubjectEntityDtoMapper {
	SubjectDto toDto(SubjectEntity entity);
	SubjectEntity toEntity(SubjectDto dto);

}
