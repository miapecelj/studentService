package mia.pecelj.be.service;

import java.util.List;
import java.util.Optional;


import mia.pecelj.be.dto.MyDto;
import mia.pecelj.be.dto.SubjectDto;

public interface SubjectService {

	Optional<SubjectDto> findById(Long id);
	List<SubjectDto> getAll();
	SubjectDto save(SubjectDto dto) throws Exception;
	Optional<SubjectDto> update(SubjectDto dto);
	void delete(Long id) throws Exception;
	List<SubjectDto> getAll(int pageNo, int pageSize, String sortBy);
}
