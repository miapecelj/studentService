package mia.pecelj.be.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.service.StudentService;

@RestController
@RequestMapping(path = "/api/student")
public class StudentRestController {
	private final StudentService studentService;
	@Autowired
	public StudentRestController(StudentService studentService) {
		this.studentService=studentService;
	}
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<StudentDto> studentDto=studentService.findById(id);
		if(studentDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(studentDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Student with id " + id+" does not exist!");
		}
	}
	@GetMapping
	public @ResponseBody ResponseEntity<List<StudentDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
	}
	

}
