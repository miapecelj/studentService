package mia.pecelj.be.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mia.pecelj.be.dto.TitleDto;
import mia.pecelj.be.service.impl.TitleServiceImpl;

@RestController
@RequestMapping(path = "/api/title")
public class TitleRestController {

	TitleServiceImpl titleService;

	@Autowired
	public TitleRestController(TitleServiceImpl titleService) {
		this.titleService = titleService;
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<TitleDto>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(titleService.getAll());
	}
}
