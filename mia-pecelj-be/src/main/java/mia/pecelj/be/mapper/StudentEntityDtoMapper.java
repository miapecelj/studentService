package mia.pecelj.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.entity.StudentEntity;

@Mapper(componentModel = "spring", uses = {
		CityEntityDtoMapper.class, StudentEntitySimpleDtoMapper.class,ExamEntitySimpleDtoMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentEntityDtoMapper {
	StudentDto toDto(StudentEntity entity);

	StudentEntity toEntity(StudentDto dto);
}
