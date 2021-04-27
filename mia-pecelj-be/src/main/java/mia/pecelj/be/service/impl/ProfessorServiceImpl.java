package mia.pecelj.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.exception.MyEntityExistException;
import mia.pecelj.be.exception.MyEntityNotPresentedException;
import mia.pecelj.be.mapper.ProfessorEntityDtoMyMapper;
import mia.pecelj.be.repository.ProfessorRepository;
import mia.pecelj.be.service.ProfessorService;





@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService{
	ProfessorRepository professorRepository;
	ProfessorEntityDtoMyMapper professorMapper;
	@Autowired
	public ProfessorServiceImpl(ProfessorEntityDtoMyMapper professorMapper,ProfessorRepository professorRepository) {
		this.professorMapper=professorMapper;
		this.professorRepository=professorRepository;
	}
	@Override
	public Optional<ProfessorDto> findById(Long id) {
		Optional<ProfessorEntity> professor = professorRepository.findById(id);
		if (professor.isPresent()) {
			return Optional.of(professorMapper.toDto(professor.get()));
		}
		return Optional.empty();
	}
	@Override
	public List<ProfessorDto> getAll() {
		List<ProfessorEntity> entities = professorRepository.findAll();
		 return entities.stream().map(entity->{
			 return professorMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}
	@Override
	public ProfessorDto save(ProfessorDto dto) throws MyEntityExistException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<ProfessorDto> update(ProfessorDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Page<ProfessorDto> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
