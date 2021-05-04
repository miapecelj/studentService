package mia.pecelj.be.controller.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mia.pecelj.be.dto.ExamDto;
import mia.pecelj.be.service.ExamService;

@RestController
@RequestMapping(path = "/api/exam")
public class ExamRestController {
	ExamService examService;

	@Autowired
	public ExamRestController(ExamService examService) {
		this.examService = examService;
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamDto examDto) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(examService.save(examDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<ExamDto>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(examService.getAll());

	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody ExamDto examDto) {
		try {
			Optional<ExamDto> exam = examService.update(examDto);
			if (exam.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(examDto);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examDto);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examService.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExamDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examService.getAll(pageable));

	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExamDto> examDto = examService.findById(id);
		if (examDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam with id " + id + " does not exist!");
		}
	}

}
