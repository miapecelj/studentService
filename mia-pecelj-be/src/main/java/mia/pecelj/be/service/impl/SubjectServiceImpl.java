package mia.pecelj.be.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mia.pecelj.be.dto.MyDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.entity.SubjectEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.mapper.SubjectEntityDtoMapper;
import mia.pecelj.be.repository.SubjectRepository;
import mia.pecelj.be.service.SubjectService;


@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{
	private SubjectRepository subjectRepository;
	private SubjectEntityDtoMapper subjectMapper;
	
	@Autowired
	public SubjectServiceImpl(SubjectRepository subjectRepository,SubjectEntityDtoMapper subjectMapper) {
		this.subjectMapper=subjectMapper;
		this.subjectRepository=subjectRepository;
	}

	@Override
	public Optional<SubjectDto> findById(Long id) {
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
		if (subjectEntity.isPresent()) {
			return Optional.of(subjectMapper.toDto(subjectEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<SubjectDto> getAll() {
		List<SubjectEntity> entities = subjectRepository.findAll();
		return entities.stream().map(entity -> {
			return subjectMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public SubjectDto save(SubjectDto dto) throws MyEntityExistException {
		Optional<SubjectEntity> entity = subjectRepository.findById(dto.getId());
		if (entity.isPresent()) {
			throw new MyEntityExistException("Subject already exists!",dto);
		}

		SubjectEntity subject = subjectRepository.save(subjectMapper.toEntity(dto));
		return subjectMapper.toDto(subject);
	}

	@Override
	public Optional<SubjectDto> update(SubjectDto dto) {
		Optional<SubjectEntity> entity = subjectRepository.findById(dto.getId());
		if (entity.isPresent()) {
			SubjectEntity subjectEntity = subjectRepository.save(subjectMapper.toEntity(dto));
			return Optional.of(subjectMapper.toDto(subjectEntity));
		}
		return Optional.empty();
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<SubjectEntity> entity = subjectRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code " + id + " does not exist!");
		}
		subjectRepository.delete(entity.get());
		
	}

	@Override
	public List<SubjectDto> getAll(int pageNo, int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));

		Page<SubjectEntity> pageResult = subjectRepository.findAll(paging);
		if (pageResult.hasContent()) {
			List<SubjectEntity> subjects = pageResult.getContent();
			return subjects.stream().map(entity -> {
				return subjectMapper.toDto(entity);
			}).collect(Collectors.toList());
		}
		return new ArrayList<SubjectDto>();
	}

	

}
