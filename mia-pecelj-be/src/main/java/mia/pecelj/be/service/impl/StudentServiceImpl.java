package mia.pecelj.be.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.entity.CityEntity;
import mia.pecelj.be.entity.ExamEntity;
import mia.pecelj.be.entity.ExamRegistrationEntity;
import mia.pecelj.be.entity.StudentEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.exception.MyValidationException;
import mia.pecelj.be.mapper.ExamEntitySimpleDtoMapper;
import mia.pecelj.be.mapper.MyStudentEntityDtoMapper;
import mia.pecelj.be.repository.CityRepository;
import mia.pecelj.be.repository.ExamRepository;
import mia.pecelj.be.repository.StudentRepository;
import mia.pecelj.be.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	private StudentRepository studentRepository;
	private MyStudentEntityDtoMapper studentMapper;
	private CityRepository cityRepository;
	private ExamRepository examRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, MyStudentEntityDtoMapper studentMapper,
			CityRepository cityRepository, ExamEntitySimpleDtoMapper examMapper,ExamRepository examRepository) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
		this.cityRepository = cityRepository;
		this.examRepository =examRepository;

	}

	@Override
	public Optional<StudentDto> findById(Long id) {
		Optional<StudentEntity> student = studentRepository.findById(id);
		if (student.isPresent()) {
			return Optional.of(studentMapper.toDto(student.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<StudentDto> getAll() {
		List<StudentEntity> entities = studentRepository.findAll();
		return entities.stream().map(entity -> {
			return studentMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public StudentDto save(StudentDto dto) throws MyEntityExistException, MyEntityNotPresentedException {
		if (dto.getCity() != null) {
			Optional<CityEntity> cityEntity = cityRepository.findById(dto.getCity().getPostalCode());
			if (!cityEntity.isPresent()) {
				throw new MyEntityNotPresentedException("city does not exist");
			}
		}
		Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getId());
		if (studentEntity.isPresent()) {
			throw new MyEntityExistException("student already exist", studentMapper.toDto(studentEntity.get()));
		}
		StudentEntity student = studentRepository.save(studentMapper.toEntity(dto));
		return studentMapper.toDto(student);
	}

	@Override
	public Optional<StudentDto> update(StudentDto dto) throws MyEntityNotPresentedException {
		if (dto.getCity() != null) {
			Optional<CityEntity> cityEntity = cityRepository.findById(dto.getCity().getPostalCode());
			if (!cityEntity.isPresent()) {
				throw new MyEntityNotPresentedException(
						"City with code " + dto.getCity().getPostalCode() + " does not exist!");
			}
		}

		Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getId());
		if (!studentEntity.isPresent()) {
			return Optional.empty();
		}
		StudentEntity student = studentRepository.save(studentMapper.toEntity(dto));
		return Optional.of(studentMapper.toDto(student));
	}

	@Override
	public StudentDto delete(Long id) throws MyEntityNotPresentedException {
		Optional<StudentEntity> studentEntity = studentRepository.findById(id);
		if (studentEntity.isPresent()) {
			studentRepository.delete(studentEntity.get());
			return studentMapper.toDto(studentEntity.get());
		} else {
			throw new MyEntityNotPresentedException("Student with id " + id + " does not exist");
		}

	}

	@Override
	public Page<StudentDto> getAll(Pageable pageable) {
		Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toDto);
		return entites;
	}

	@Override
	public StudentDto addExam(Long examId, Long studentId) throws MyEntityNotPresentedException, MyValidationException {
		Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
		if (!studentEntity.isPresent()) {
			throw new MyEntityNotPresentedException("student does not exist");
		}
		Optional<ExamEntity> examEntity = examRepository.findById(examId);
		if (!examEntity.isPresent()) {
			throw new MyEntityNotPresentedException("exam does not exist");
		}
		try {
			isValid(examEntity.get(), studentEntity.get());
		} catch (MyValidationException e) {
			throw e;
		}
		StudentEntity student = studentEntity.get();
		ExamEntity exam = examEntity.get();
		student.getExams().add(new ExamRegistrationEntity(student, exam));
		studentRepository.save(student);
		return studentMapper.toDto(student);
	}

	private void isValid(ExamEntity examEntity, StudentEntity studentEntity) throws MyValidationException {
		if (examEntity.getSubject().getYearOfStudy() > studentEntity.getCurrentYearOfStudy()) {
			throw new MyValidationException("Student can't register subject that is from higher year of study");
		}
		if ((LocalDate.now().plusWeeks(1).isBefore(examEntity.getExamPeriod().getStartDate())
				|| LocalDate.now().isAfter(examEntity.getExamPeriod().getStartDate()))) {
			throw new MyValidationException("Exam can be registered one week before start of exam period");
		}

	}

	@Override
	public StudentDto removeExam(Long studentId, Long examId) throws MyEntityNotPresentedException {
		Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
		if (!studentEntity.isPresent()) {
			throw new MyEntityNotPresentedException("student does not exist");
		}
		Optional<ExamEntity> examEntity = examRepository.findById(examId);
		if (!examEntity.isPresent()) {
			throw new MyEntityNotPresentedException("exam does not exist");
		}
		ExamEntity exam = examEntity.get();
		StudentEntity student = studentEntity.get();
		student.getExams().remove(new ExamRegistrationEntity(student, exam));
		student = studentRepository.save(student);
		return studentMapper.toDto(student);

	}

}
