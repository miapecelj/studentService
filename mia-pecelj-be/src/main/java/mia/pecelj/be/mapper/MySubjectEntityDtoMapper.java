package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.MySubjectDto;
import mia.pecelj.be.entity.SubjectEntity;

@Mapper(componentModel = "spring") 
public interface MySubjectEntityDtoMapper {
	MySubjectDto toDto(SubjectEntity subject);
	SubjectEntity toEntity(MySubjectDto dto);
	

}
