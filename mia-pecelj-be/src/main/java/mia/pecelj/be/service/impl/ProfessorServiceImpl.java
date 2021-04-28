package mia.pecelj.be.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.dto.ProfessorSubjectDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.entity.CityEntity;
import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.entity.SubjectEntity;
import mia.pecelj.be.entity.TitleEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.mapper.ProfessorEntityDtoMyMapper;
import mia.pecelj.be.mapper.SubjectEntityDtoMapper;
import mia.pecelj.be.repository.CityRepository;
import mia.pecelj.be.repository.ProfessorRepository;
import mia.pecelj.be.repository.SubjectRepository;
import mia.pecelj.be.repository.TitleRepository;
import mia.pecelj.be.service.ProfessorService;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {
	ProfessorRepository professorRepository;
	ProfessorEntityDtoMyMapper professorMapper;
	CityRepository cityRepository;
	TitleRepository titleRepository;
	SubjectEntityDtoMapper subjectMapper;
	SubjectRepository subjectRepository;

	@Autowired
	public ProfessorServiceImpl(ProfessorEntityDtoMyMapper professorMapper, ProfessorRepository professorRepository,
			CityRepository cityRepository, TitleRepository titleRepository, SubjectEntityDtoMapper subjectMapper,
			SubjectRepository subjectRepository) {
		this.professorMapper = professorMapper;
		this.professorRepository = professorRepository;
		this.cityRepository = cityRepository;
		this.titleRepository = titleRepository;
		this.subjectMapper = subjectMapper;
		this.subjectRepository = subjectRepository;
	}

	@Override
	public Optional<ProfessorDto> findById(Long id) {
		Optional<ProfessorEntity> professor = professorRepository.findById(id);
		if (professor.isPresent()) {
			return Optional.of(professorMapper.toDto(professor.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ProfessorDto> getAll() {
		List<ProfessorEntity> entities = professorRepository.findAll();
		return entities.stream().map(entity -> {
			return professorMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ProfessorDto save(ProfessorDto dto) throws MyEntityExistException, MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity = cityRepository.findById(dto.getCity().getPostalCode());
		if (!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("city does not exist");
		}
		Optional<TitleEntity> titleEntity = titleRepository.findById(dto.getTitle().getId());
		if (!titleEntity.isPresent()) {
			throw new MyEntityNotPresentedException("title does not exist");
		}
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(dto.getId());
		if (professorEntity.isPresent()) {
			throw new MyEntityExistException("professor already exist", professorMapper.toDto(professorEntity.get()));
		}
		List<ProfessorSubjectDto> subjects = dto.getSubjects();
		dto.setSubjects(new ArrayList<ProfessorSubjectDto>());
		ProfessorEntity professor = professorRepository.save(professorMapper.toEntity(dto));
		dto.setId(professor.getId());
		for (ProfessorSubjectDto professorSubject : subjects) {
			dto.addSubject(professorSubject.getSubject());

		}
		professor = professorRepository.save(professorMapper.toEntity(dto));
		return professorMapper.toDto(professor);
	}

	@Override
	public Optional<ProfessorDto> update(ProfessorDto dto) throws MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity = cityRepository.findById(dto.getCity().getPostalCode());
		if (!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException(
					"City with code " + dto.getCity().getPostalCode() + " does not exist!");
		}
		Optional<TitleEntity> titleEntity = titleRepository.findById(dto.getTitle().getId());
		if (!titleEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Title with code " + dto.getTitle().getId() + " does not exist!");
		}
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(dto.getId());
		if (!professorEntity.isPresent()) {
			return Optional.empty();
		}
		ProfessorEntity professor = professorRepository.save(professorMapper.toEntity(dto));
		return Optional.of(professorMapper.toDto(professor));
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(id);
		if (professorEntity.isPresent()) {
			professorRepository.delete(professorEntity.get());
		} else {
			throw new MyEntityNotPresentedException("Professor with id " + id + " does not exist");
		}

	}

	@Override
	public Page<ProfessorDto> getAll(Pageable pageable) {
		Page<ProfessorDto> entites = professorRepository.findAll(pageable).map(professorMapper::toDto);
		return entites;
	}

	@Override
	public ProfessorDto addSubject(SubjectDto subject, Long id) throws MyEntityNotPresentedException {
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(id);
		if (!professorEntity.isPresent()) {
			throw new MyEntityNotPresentedException("professor does not exist");
		}
		ProfessorEntity professor = professorEntity.get();
		professor.addSubject(subjectMapper.toEntity(subject));
		professorRepository.save(professor);
		return professorMapper.toDto(professor);
	}

	@Override
	public void removeSubject(Long professorId, Long subjectId) throws MyEntityNotPresentedException {
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
		if (!professorEntity.isPresent()) {
			throw new MyEntityNotPresentedException("professor does not exist");
		}
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
		if (!subjectEntity.isPresent()) {
			throw new MyEntityNotPresentedException("subject does not exist");
		}
		SubjectEntity subject = subjectEntity.get();
		ProfessorEntity professor = professorEntity.get();
		professor.removeSubject(subject);
	}

}
