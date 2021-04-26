package mia.pecelj.be.mapper;

import org.mapstruct.Mapper;

import mia.pecelj.be.dto.CityDto;
import mia.pecelj.be.entity.CityEntity;

@Mapper(componentModel = "spring")
public interface CityEntityDtoMapper {
	CityDto toDto(CityEntity city);
	CityEntity toEntity(CityDto city);

}
