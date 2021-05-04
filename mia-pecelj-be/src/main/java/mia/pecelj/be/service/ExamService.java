package mia.pecelj.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mia.pecelj.be.dto.ExamDto;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.exception.MyValidationException;

public interface ExamService {
	Optional<ExamDto> findById(Long id);
	List<ExamDto> getAll();
	ExamDto save(ExamDto dto) throws MyEntityExistException, MyEntityNotPresentedException, MyValidationException;
	Optional<ExamDto> update(ExamDto dto) throws MyEntityNotPresentedException, MyEntityExistException, MyValidationException;
	ExamDto delete(Long id) throws MyEntityNotPresentedException;
	public Page<ExamDto> getAll(Pageable pageable);

}
