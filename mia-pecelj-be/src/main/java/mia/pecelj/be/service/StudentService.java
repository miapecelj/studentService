package mia.pecelj.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mia.pecelj.be.dto.StudentDto;

import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.exception.MyValidationException;

public interface StudentService {

	Optional<StudentDto> findById(Long id);

	List<StudentDto> getAll();

	StudentDto save(StudentDto dto) throws MyEntityExistException, MyEntityNotPresentedException;

	Optional<StudentDto> update(StudentDto dto) throws MyEntityNotPresentedException;

	StudentDto delete(Long id) throws MyEntityNotPresentedException;

	public Page<StudentDto> getAll(Pageable pageable);

	StudentDto addExam(Long examId, Long studentId) throws MyEntityNotPresentedException, MyValidationException;

	StudentDto removeExam(Long studentId, Long examId) throws MyEntityNotPresentedException;

}