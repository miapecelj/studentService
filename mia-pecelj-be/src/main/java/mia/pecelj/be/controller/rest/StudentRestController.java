package mia.pecelj.be.controller.rest;

import java.time.LocalDate;
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

import mia.pecelj.be.dto.ExamDto;
import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.dto.SubjectDto;
import mia.pecelj.be.service.StudentService;

@RestController
@RequestMapping(path = "/api/student")
public class StudentRestController {
	private final StudentService studentService;

	@Autowired
	public StudentRestController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<StudentDto> studentDto = studentService.findById(id);
		if (studentDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(studentDto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " does not exist!");
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<StudentDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody StudentDto studentDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
		    bindingResult.getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving student "+errors);
		} else {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(studentService.save(studentDto));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod cuvanja entiteta: " + studentDto);
			}
		}
	}

	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@Valid @RequestBody StudentDto studentDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
		    bindingResult.getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		} else {
			try {
				Optional<StudentDto> student = studentService.update(studentDto);
				if (student.isPresent()) {
					return ResponseEntity.status(HttpStatus.OK).body(studentDto);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(studentDto);
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}

	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(studentService.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<StudentDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll(pageable));
	}
	@PostMapping("/addExam")
	public @ResponseBody ResponseEntity<Object> addExam(@RequestParam Long examId, @RequestParam Long studentId) {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(studentService.addExam(examId, studentId));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		
	}

	@DeleteMapping("/removeExam")
	public @ResponseBody ResponseEntity<Object> removeExam(@RequestParam Long studentId,@RequestParam Long examId) {
		try {
			studentService.removeExam(studentId, examId);
			return ResponseEntity.status(HttpStatus.OK).body(studentService.removeExam(studentId, examId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
