package mia.pecelj.be.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.entity.ProfessorEntity;
import mia.pecelj.be.service.ProfessorService;

@RestController
@RequestMapping(path = "/api/professor")
public class ProfessorRestController {
	private ProfessorService professorService;
	@Autowired
	public ProfessorRestController(ProfessorService professorService) {
		this.professorService=professorService;
	}
	@GetMapping
	public @ResponseBody ResponseEntity<List<ProfessorDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
	}
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ProfessorDto> professorDto=professorService.findById(id);
		if(professorDto.isPresent()) {
			System.out.println(professorDto.get());
			return ResponseEntity.status(HttpStatus.OK).body(professorDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Professor with id " + id+" does not exist!");
		}
	}

}
