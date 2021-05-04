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

import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.service.ExamPeriodService;

@RestController
@RequestMapping(path = "/api/examPeriod")
public class ExamPeriodRestController {
	private ExamPeriodService examPeriodService;

	@Autowired
	public ExamPeriodRestController(ExamPeriodService examPeriodService) {
		this.examPeriodService = examPeriodService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExamPeriodDto> examPeriodDto = examPeriodService.findById(id);
		if (examPeriodDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam period with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<ExamPeriodDto>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.getAll());

	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamPeriodDto examPeriodDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.save(examPeriodDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody ExamPeriodDto examPeriodDto) {
		try {
			Optional<ExamPeriodDto> examPeriod = examPeriodService.update(examPeriodDto);
			if (examPeriod.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(examPeriodDto);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examPeriodDto);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExamPeriodDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.getAll(pageable));

	}

}
