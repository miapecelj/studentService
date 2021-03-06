package mia.pecelj.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.dto.ProfessorSubjectDto;
import mia.pecelj.be.dto.SimpleSubjectDto;
import mia.pecelj.be.entity.ExamEntity;
import mia.pecelj.be.entity.ExamPeriodEntity;
import mia.pecelj.be.entity.ProfessorSubjectEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.exception.MyValidationException;
import mia.pecelj.be.mapper.ExamEntityDtoMapper;
import mia.pecelj.be.mapper.ExamEntitySimpleDtoMapper;
import mia.pecelj.be.mapper.ExamPeriodEntityDtoMapper;
import mia.pecelj.be.repository.ExamPeriodRepository;
import mia.pecelj.be.service.ExamPeriodService;

@Service
@Transactional
public class ExamPeriodServiceImpl implements ExamPeriodService {
	ExamPeriodRepository examPeriodRepository;
	ExamPeriodEntityDtoMapper examPeriodMapper;
	ExamEntityDtoMapper examMapper;

	@Autowired
	public ExamPeriodServiceImpl(ExamEntityDtoMapper examMapper,ExamPeriodRepository examPeriodRepository,
			ExamPeriodEntityDtoMapper examPeriodMapper) {
		this.examPeriodRepository = examPeriodRepository;
		this.examPeriodMapper = examPeriodMapper;
		this.examMapper = examMapper;
	}

	@Override
	public Optional<ExamPeriodDto> findById(Long id) {
		Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(id);
		if (examPeriodEntity.isPresent()) {
			return Optional.of(examPeriodMapper.toDto(examPeriodEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExamPeriodDto> getAll() {
		List<ExamPeriodEntity> entities = examPeriodRepository.findAll();
		return entities.stream().map(entity -> {
			return examPeriodMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamPeriodDto save(ExamPeriodDto dto) throws MyEntityExistException, MyValidationException {
		try {
			isInterfering(dto);
		} catch (MyValidationException e) {
			throw e;
		}
		Optional<ExamPeriodEntity> entity = examPeriodRepository.findById(dto.getId());
		if (entity.isPresent()) {
			throw new MyEntityExistException("ExamPeriod already exists!", dto);
		}
		ExamPeriodEntity examPeriod = examPeriodRepository.save(examPeriodMapper.toEntity(dto));
		return examPeriodMapper.toDto(examPeriod);
	}

	@Override
	public Optional<ExamPeriodDto> update(ExamPeriodDto dto) throws MyEntityExistException, MyValidationException {
		try {
			isInterfering(dto);
		} catch (MyValidationException e) {
			throw e;
		}
		Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(dto.getId());
		if (!examPeriodEntity.isPresent()) {
			return Optional.empty();
		}
		List<ExamEntity> exams = examPeriodEntity.get().getExams();
		for (ExamEntity examEntity : exams) {
			dto.getExams()
					.add(examMapper.toDto(examEntity));
		}
		ExamPeriodEntity examPeriod = examPeriodRepository.save(examPeriodMapper.toEntity(dto));
		return Optional.of(examPeriodMapper.toDto(examPeriod));
	}

	@Override
	public ExamPeriodDto delete(Long id) throws MyEntityNotPresentedException {
		Optional<ExamPeriodEntity> entity = examPeriodRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("ExamPeriod with id " + id + " does not exist!");
		}
		examPeriodRepository.delete(entity.get());
		return examPeriodMapper.toDto(entity.get());

	}

	@Override
	public Page<ExamPeriodDto> getAll(Pageable pageable) {
		Page<ExamPeriodDto> entites = examPeriodRepository.findAll(pageable).map(examPeriodMapper::toDto);
		return entites;
	}

	public boolean isInterfering(ExamPeriodDto dto) throws MyValidationException {
		List<ExamPeriodEntity> entities = examPeriodRepository.findAll();

		if(examPeriodRepository.findAllByActive(true).size()!=0 && dto.isActive()) {
			throw new MyValidationException("Active exam period already exist");
		}
		if (entities.stream()
				.anyMatch(entity -> ((entity.getStartDate().isBefore(dto.getStartDate())
						&& entity.getEndDate().isAfter(dto.getStartDate()))
						|| (entity.getStartDate().isBefore(dto.getEndDate())
								&& entity.getEndDate().isAfter(dto.getEndDate()))))) {
			throw new MyValidationException("Date ovrelap");
		}
		if (dto.getEndDate().isBefore(dto.getStartDate())) {
			throw new MyValidationException("End date is before start date");
		}
		return true;
	}

}
