package mia.pecelj.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.entity.CityEntity;
import mia.pecelj.be.entity.StudentEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.mapper.CityEntityDtoMapper;
import mia.pecelj.be.mapper.StudentEntityDtoMapper;
import mia.pecelj.be.repository.CityRepository;
import mia.pecelj.be.repository.StudentRepository;
import mia.pecelj.be.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	private StudentRepository studentRepository;
	private StudentEntityDtoMapper studentMapper;
	private CityRepository cityRepository;
	private CityEntityDtoMapper cityMapper;
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository,StudentEntityDtoMapper studentMapper,CityRepository cityRepository, CityEntityDtoMapper cityMapper) {
		this.studentRepository=studentRepository;
		this.studentMapper=studentMapper;
		this.cityRepository=cityRepository;
		this.cityMapper=cityMapper;
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
		 return entities.stream().map(entity->{
			 return studentMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}
	@Override
	public StudentDto save(StudentDto dto) throws MyEntityExistException, MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity = cityRepository.findById(dto.getCity().getPostalCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("city does not exist");
		}
		Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getId());
		if(studentEntity.isPresent()) {
			throw new MyEntityExistException("student already exist", studentMapper.toDto(studentEntity.get()));
		}
		StudentEntity student = studentRepository.save(studentMapper.toEntity(dto));
		return studentMapper.toDto(student);
	}
	@Override
	public Optional<StudentDto> update(StudentDto dto) throws MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity= cityRepository.findById(dto.getCity().getPostalCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ dto.getCity().getPostalCode()+" does not exist!");
		}
		Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getId());
		if(!studentEntity.isPresent()) {
			return Optional.empty();
		}
		StudentEntity student = studentRepository.save(studentMapper.toEntity(dto));
		return Optional.of(studentMapper.toDto(student));
	}
	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<StudentEntity> studentEntity = studentRepository.findById(id);
		if(studentEntity.isPresent()) {
			studentRepository.delete(studentEntity.get());
		}else {
			throw new MyEntityNotPresentedException("Student with id "+id+" does not exist");
		}
		
	}
	@Override
	public Page<StudentDto> getAll(Pageable pageable) {
		Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toDto);
		return entites;
	}
	
	

}
