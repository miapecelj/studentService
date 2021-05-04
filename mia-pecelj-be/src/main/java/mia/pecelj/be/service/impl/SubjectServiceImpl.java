package mia.pecelj.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mia.pecelj.be.dto.SimpleSubjectDto;
import mia.pecelj.be.dto.ProfessorSubjectDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.entity.ProfessorSubjectEntity;
import mia.pecelj.be.entity.SubjectEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.mapper.ProfessorEntitySimpleDtoMapper;
import mia.pecelj.be.mapper.SubjectEntityDtoMapper;
import mia.pecelj.be.repository.SubjectRepository;
import mia.pecelj.be.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	private SubjectRepository subjectRepository;
	private SubjectEntityDtoMapper subjectMapper;
	private ProfessorEntitySimpleDtoMapper professorMapper;

	@Autowired
	public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectEntityDtoMapper subjectMapper,
			ProfessorEntitySimpleDtoMapper professorMapper) {
		this.subjectMapper = subjectMapper;
		this.subjectRepository = subjectRepository;
		this.professorMapper = professorMapper;
	}

	@Override
	public Optional<SubjectDto> findById(Long id) {
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
		System.out.println(subjectEntity.get().getProfessors());
		if (subjectEntity.isPresent()) {
			System.out.println(subjectMapper.toDto(subjectEntity.get()).getProfessors());
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
			throw new MyEntityExistException("Subject already exists!", dto);
		}

		SubjectEntity subject = subjectRepository.save(subjectMapper.toEntity(dto));
		return subjectMapper.toDto(subject);
	}

	@Override
	public Optional<SubjectDto> update(SubjectDto dto) {
		Optional<SubjectEntity> entity = subjectRepository.findById(dto.getId());
		if (entity.isPresent()) {
			List<ProfessorSubjectEntity> professors = entity.get().getProfessors();
			for (ProfessorSubjectEntity professorSubjectEntity : professors) {
				dto.getProfessors()
						.add(new ProfessorSubjectDto(professorMapper.toDto(professorSubjectEntity.getProfessor()),
								new SimpleSubjectDto(dto.getId(), dto.getName(), dto.getDescription(),
										dto.getNoOfEspb(), dto.getYearOfStudy(), null),
								professorSubjectEntity.getAssignDate()));
			}
			SubjectEntity subjectEntity = subjectRepository.save(subjectMapper.toEntity(dto));
			return Optional.of(subjectMapper.toDto(subjectEntity));
		}
		return Optional.empty();
	}

	@Override
	public SubjectDto delete(Long id) throws MyEntityNotPresentedException {
		Optional<SubjectEntity> entity = subjectRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Subject with id " + id + " does not exist!");

		}
		subjectRepository.delete(entity.get());
		return subjectMapper.toDto(entity.get());

	}

	@Override
	public Page<SubjectDto> getAll(Pageable pageable) {
		Page<SubjectDto> entites = subjectRepository.findAll(pageable).map(subjectMapper::toDto);
		return entites;

	}

}
