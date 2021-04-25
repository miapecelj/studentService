package mia.pecelj.be.service;

import java.util.List;
import java.util.Optional;


import mia.pecelj.be.dto.MyDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;

public interface SubjectService {

	Optional<SubjectDto> findById(Long id);
	List<SubjectDto> getAll();
	SubjectDto save(SubjectDto dto) throws MyEntityExistException;
	Optional<SubjectDto> update(SubjectDto dto);
	void delete(Long id) throws MyEntityNotPresentedException;
	List<SubjectDto> getAll(int pageNo, int pageSize, String sortBy);
}
