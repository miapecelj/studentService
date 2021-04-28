package mia.pecelj.be.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.TitleDto;
import mia.pecelj.be.entity.TitleEntity;
import mia.pecelj.be.mapper.TitleEntityDtoMapper;
import mia.pecelj.be.repository.TitleRepository;
import mia.pecelj.be.service.TitleService;

@Transactional
@Service
public class TitleServiceImpl implements TitleService {
	TitleEntityDtoMapper titleMapper;
	TitleRepository titleRepository;

	@Autowired
	public TitleServiceImpl(TitleEntityDtoMapper titleMapper, TitleRepository titleRepository) {
		this.titleMapper = titleMapper;
		this.titleRepository = titleRepository;
	}

	@Override
	public List<TitleDto> getAll() {
		List<TitleEntity> entities = titleRepository.findAll();
		return entities.stream().map(entity -> {
			return titleMapper.toDto(entity);
		}).collect(Collectors.toList());
	}
}
