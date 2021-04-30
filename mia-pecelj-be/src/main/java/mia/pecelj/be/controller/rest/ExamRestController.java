package mia.pecelj.be.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mia.pecelj.be.dto.ExamDto;
import mia.pecelj.be.dto.ExamPeriodDto;
import mia.pecelj.be.dto.StudentDto;
import mia.pecelj.be.service.ExamService;

@RestController
@RequestMapping(path = "/api/exam")
public class ExamRestController {
	ExamService examService;
	@Autowired
	public ExamRestController(ExamService examService) {
		this.examService=examService;
	}
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamDto examDto) {
		
			try {
				return ResponseEntity.status(HttpStatus.OK).body(examService.save(examDto));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Greska kod cuvanja entiteta: " +e.getMessage());
			}
		}
	@GetMapping
	public @ResponseBody ResponseEntity<List<ExamDto>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(examService.getAll());

	}
	

}
