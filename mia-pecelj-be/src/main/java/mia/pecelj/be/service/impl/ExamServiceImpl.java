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
import mia.pecelj.be.mapper.ExamEntityDtoMapper;
import mia.pecelj.be.repository.ExamPeriodRepository;
import mia.pecelj.be.repository.ExamRepository;
import mia.pecelj.be.repository.ProfessorRepository;
import mia.pecelj.be.repository.SubjectRepository;
import mia.pecelj.be.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService{
	ExamRepository examRepository;
	ExamPeriodRepository examPeriodRepository;
	ProfessorRepository professorRepository;
	SubjectRepository subjectRepository;
	ExamEntityDtoMapper examMapper;
	@Autowired
	public ExamServiceImpl(ExamRepository examRepository,ExamPeriodRepository examPeriodRepository,ProfessorRepository professorRepository,
			SubjectRepository subjectRepository,ExamEntityDtoMapper examMapper) {
		this.examPeriodRepository=examPeriodRepository;
		this.examRepository=examRepository;
		this.professorRepository=professorRepository;
		this.subjectRepository=subjectRepository;
		this.examMapper=examMapper;
	}
	

	@Override
	public Optional<ExamDto> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamDto> getAll() {
		List<ExamEntity> entities = examRepository.findAll();
		return entities.stream().map(entity -> {
			return examMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamDto save(ExamDto dto) throws MyEntityExistException, MyEntityNotPresentedException {
		System.out.println(dto);
		if(dto.getExamPeriod()!=null) {
			Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(dto.getExamPeriod().getId());
			if (!examPeriodEntity.isPresent()) {
				throw new MyEntityNotPresentedException("examPeriod does not exist");
			}
			}
			Optional<SubjectEntity>  subjectEntity= subjectRepository.findById(dto.getSubject().getId());
			if (!subjectEntity.isPresent()) {
				throw new MyEntityNotPresentedException("subject does not exist");
			}
			Optional<ProfessorEntity>  professorEntity= professorRepository.findById(dto.getProfessor().getId());
			if (!professorEntity.isPresent()) {
				throw new MyEntityNotPresentedException("professor  does not exist");
			}
//			if(!dto.getProfessor().getSubjects().stream().map(professorSubject->
//				professorSubject.getSubject()).collect(Collectors.toList())
//					.contains(dto.getSubject())) {
//				throw new MyEntityNotPresentedException("professor not assigned on that subject");
//			}
			Optional<ExamEntity>  examEntity= examRepository.findById(dto.getId());
			if (examEntity.isPresent()) {
				throw new MyEntityExistException("exam already exist",dto);
			}
			ExamEntity exam = examRepository.save(examMapper.toEntity(dto));
			return examMapper.toDto(exam);
	}

	@Override
	public Optional<ExamDto> update(ExamDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamDto delete(Long id) throws MyEntityNotPresentedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ExamDto> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	

}




