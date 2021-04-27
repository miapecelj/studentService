package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.TitleDto;
import mia.pecelj.be.entity.TitleEntity;

@Mapper(componentModel ="spring")
public interface TitleEntityDtoMapper {
	
	TitleDto toDto(TitleEntity entity);
	TitleEntity toEntity(TitleEntity entity);

}
