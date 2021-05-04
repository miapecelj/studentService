package mia.pecelj.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.exception.MyValidationException;

public interface ExamPeriodService {

	Optional<ExamPeriodDto> findById(Long id);
	List<ExamPeriodDto> getAll();
	ExamPeriodDto save(ExamPeriodDto dto) throws MyEntityExistException, MyValidationException;
	Optional<ExamPeriodDto> update(ExamPeriodDto dto) throws MyEntityExistException, MyValidationException;
	ExamPeriodDto delete(Long id) throws MyEntityNotPresentedException;
	public Page<ExamPeriodDto> getAll(Pageable pageable);
}
