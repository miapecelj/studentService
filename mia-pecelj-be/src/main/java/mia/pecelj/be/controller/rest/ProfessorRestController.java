package mia.pecelj.be.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mia.pecelj.be.dto.ProfessorDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.service.ProfessorService;

@RestController
@RequestMapping(path = "/api/professor")
public class ProfessorRestController {
	private ProfessorService professorService;

	@Autowired
	public ProfessorRestController(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<ProfessorDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ProfessorDto> professorDto = professorService.findById(id);
		if (professorDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(professorDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor with id " + id + " does not exist!");
		}
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ProfessorDto professorDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
		    bindingResult.getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving professor "+errors);
			
		} else {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professorDto));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Greska kod cuvanja entiteta: " + professorDto);
			}
		}
	}

	@PostMapping("/addSubject")
	public @ResponseBody ResponseEntity<Object> save(@RequestParam Long id, @Valid @RequestBody SubjectDto subject,
			BindingResult bindingResult) {
		System.out.println(id);
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod dodavanja predmete " + subject);
		} else {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(professorService.addSubject(subject, id));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod dodavanja predmete " + subject);
			}
		}
	}

	@PutMapping("/removeSubject")
	public @ResponseBody ResponseEntity<Object> removeSubject(@RequestParam Long professorId,
			@RequestParam Long subjectId) {

		try {
			professorService.removeSubject(professorId, subjectId);
			return ResponseEntity.status(HttpStatus.OK).body("Uspesno uklonjen predmet");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Greska kod uklanjanja predmeta sa id " + subjectId);
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<ProfessorDto> update(@Valid @RequestBody ProfessorDto professorDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
		    bindingResult.getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(professorDto);
		} else {
			try {
				Optional<ProfessorDto> professor = professorService.update(professorDto);
				if (professor.isPresent()) {
					return ResponseEntity.status(HttpStatus.OK).body(professorDto);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(professorDto);
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(professorDto);
			}
		}

	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			professorService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted professor with id:" + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ProfessorDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll(pageable));
	}

}
