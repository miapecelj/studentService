package mia.pecelj.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;


public interface ProfessorService {
	Optional<ProfessorDto> findById(Long id);
	List<ProfessorDto> getAll();
	ProfessorDto save(ProfessorDto dto) throws MyEntityExistException, MyEntityNotPresentedException;
	Optional<ProfessorDto> update(ProfessorDto dto) throws MyEntityNotPresentedException;
	ProfessorDto delete(Long id) throws MyEntityNotPresentedException;
	public Page<ProfessorDto> getAll(Pageable pageable);
	public ProfessorDto addSubject(SubjectDto subject,Long id) throws MyEntityNotPresentedException;
	void removeSubject(Long professorId, Long subjectId) throws MyEntityNotPresentedException;

}
