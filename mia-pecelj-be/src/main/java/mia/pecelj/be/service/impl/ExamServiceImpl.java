package mia.pecelj.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.ExamDto;
import mia.pecelj.be.entity.ExamEntity;
import mia.pecelj.be.entity.ExamPeriodEntity;
import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.entity.SubjectEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.exception.MyValidationException;
import mia.pecelj.be.mapper.ExamEntityDtoMapper;
import mia.pecelj.be.repository.ExamPeriodRepository;
import mia.pecelj.be.repository.ExamRepository;
import mia.pecelj.be.repository.ProfessorRepository;
import mia.pecelj.be.repository.SubjectRepository;
import mia.pecelj.be.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
	ExamRepository examRepository;
	ExamPeriodRepository examPeriodRepository;
	ProfessorRepository professorRepository;
	SubjectRepository subjectRepository;
	ExamEntityDtoMapper examMapper;

	@Autowired
	public ExamServiceImpl(ExamRepository examRepository, ExamPeriodRepository examPeriodRepository,
			ProfessorRepository professorRepository, SubjectRepository subjectRepository,
			ExamEntityDtoMapper examMapper) {
		this.examPeriodRepository = examPeriodRepository;
		this.examRepository = examRepository;
		this.professorRepository = professorRepository;
		this.subjectRepository = subjectRepository;
		this.examMapper = examMapper;

	}

	@Override
	public Optional<ExamDto> findById(Long id) {
		Optional<ExamEntity> exam = examRepository.findById(id);
		if (exam.isPresent()) {
			return Optional.of(examMapper.toDto(exam.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExamDto> getAll() {
		List<ExamEntity> entities = examRepository.findAll();
		return entities.stream().map(entity -> {
			return examMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamDto save(ExamDto dto)
			throws MyEntityExistException, MyEntityNotPresentedException, MyValidationException {
		if (dto.getExamPeriod() != null) {
			Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(dto.getExamPeriod().getId());
			if (!examPeriodEntity.isPresent()) {
				throw new MyEntityNotPresentedException("examPeriod does not exist");
			} else {
				if (examPeriodEntity.get().getExams().stream().map(exam -> exam.getSubject().getId())
						.collect(Collectors.toList()).contains(dto.getSubject().getId())) {
					throw new MyValidationException("Exam for this subject already defined in this examPeriod");
				}
				if (dto.getDateOfExam().isBefore(examPeriodEntity.get().getStartDate())
						|| dto.getDateOfExam().isAfter(examPeriodEntity.get().getEndDate())) {
					throw new MyValidationException("Date of exam must be between "
							+ examPeriodEntity.get().getStartDate() + " and " + examPeriodEntity.get().getEndDate());
				}

			}
		}
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(dto.getSubject().getId());
		if (!subjectEntity.isPresent()) {
			throw new MyEntityNotPresentedException("subject does not exist");
		}
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(dto.getProfessor().getId());
		if (!professorEntity.isPresent()) {
			throw new MyEntityNotPresentedException("professor  does not exist");
		}
		Optional<ExamEntity> examEntity = examRepository.findById(dto.getId());
		if (examEntity.isPresent()) {
			throw new MyEntityExistException("exam already exist", dto);
		}
		ExamEntity exam = examRepository.save(examMapper.toEntity(dto));
		return examMapper.toDto(exam);
	}

	@Override
	public Optional<ExamDto> update(ExamDto dto)
			throws MyEntityNotPresentedException, MyEntityExistException, MyValidationException {
		if (dto.getExamPeriod() != null) {
			Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(dto.getExamPeriod().getId());
			if (!examPeriodEntity.isPresent()) {
				throw new MyEntityNotPresentedException("examPeriod does not exist");
			} else {
				if (examPeriodEntity.get().getExams().stream().map(exam -> exam.getSubject().getId())
						.collect(Collectors.toList()).contains(dto.getSubject().getId())) {
					throw new MyValidationException("Exam for this subject already defined in this examPeriod");
				}
				if (dto.getDateOfExam().isBefore(examPeriodEntity.get().getStartDate())
						|| dto.getDateOfExam().isAfter(examPeriodEntity.get().getEndDate())) {
					throw new MyValidationException("Date of exam must be between "
							+ examPeriodEntity.get().getStartDate() + " and " + examPeriodEntity.get().getEndDate());
				}

			}
		}
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(dto.getSubject().getId());
		if (!subjectEntity.isPresent()) {
			throw new MyEntityNotPresentedException("subject does not exist");
		}
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(dto.getProfessor().getId());
		if (!professorEntity.isPresent()) {
			throw new MyEntityNotPresentedException("professor  does not exist");
		}
		Optional<ExamEntity> examEntity = examRepository.findById(dto.getId());
		if (!examEntity.isPresent()) {
			throw new MyEntityNotPresentedException("exam already exist");
		}
		ExamEntity exam = examRepository.save(examMapper.toEntity(dto));
		return Optional.of(examMapper.toDto(exam));
	}

	@Override
	public ExamDto delete(Long id) throws MyEntityNotPresentedException {
		Optional<ExamEntity> examEntity = examRepository.findById(id);
		if (examEntity.isPresent()) {
			examRepository.delete(examEntity.get());
			return examMapper.toDto(examEntity.get());
		} else {
			throw new MyEntityNotPresentedException("Exam with id " + id + " does not exist");
		}
	}

	@Override
	public Page<ExamDto> getAll(Pageable pageable) {
		Page<ExamDto> entites = examRepository.findAll(pageable).map(examMapper::toDto);
		return entites;
	}

}
