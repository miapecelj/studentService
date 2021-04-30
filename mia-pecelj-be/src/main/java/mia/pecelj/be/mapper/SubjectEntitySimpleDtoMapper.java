package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.SimpleSubjectDto;
import mia.pecelj.be.entity.SubjectEntity;

@Mapper(componentModel = "spring") 
public interface SubjectEntitySimpleDtoMapper {
	SimpleSubjectDto toDto(SubjectEntity subject);
	SubjectEntity toEntity(SimpleSubjectDto dto);
	

}
