package mia.pecelj.be.controller.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.service.SubjectService;

@RestController
@RequestMapping(path = "/api/subject")
public class SubjectRestController {
	private final SubjectService subjectService;

	@Autowired
	public SubjectRestController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<SubjectDto> subjectDto = subjectService.findById(id);
		if (subjectDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(subjectDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<SubjectDto>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll());
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody SubjectDto subjectDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod cuvanja entiteta: " + subjectDto);
		} else {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectDto));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod cuvanja entiteta: " + subjectDto);
			}
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<SubjectDto> update(@Valid @RequestBody SubjectDto subjectDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subjectDto);
		} else {
			Optional<SubjectDto> subject = subjectService.update(subjectDto);
			if (subject.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(subject.get());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subjectDto);
			}
		}

	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			subjectService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted subject with id:" + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<SubjectDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll(pageable));
	}

}
