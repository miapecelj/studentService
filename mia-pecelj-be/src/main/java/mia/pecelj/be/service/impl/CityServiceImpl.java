package mia.pecelj.be.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.CityDto;
import mia.pecelj.be.entity.CityEntity;
import mia.pecelj.be.mapper.CityEntityDtoMapper;
import mia.pecelj.be.repository.CityRepository;
import mia.pecelj.be.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {
	CityRepository cityRepository;
	CityEntityDtoMapper cityMapper;

	@Autowired
	public CityServiceImpl(CityRepository cityRepository, CityEntityDtoMapper cityMapper) {
		this.cityRepository = cityRepository;
		this.cityMapper = cityMapper;
	}

	@Override
	public List<CityDto> getAll() {
		List<CityEntity> entities = cityRepository.findAll();
		return entities.stream().map(entity -> {
			return cityMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

}
