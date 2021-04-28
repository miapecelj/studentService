package mia.pecelj.be.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mia.pecelj.be.dto.CityDto;
import mia.pecelj.be.dto.MyDto;
import mia.pecelj.be.service.CityService;

@RestController
@RequestMapping(path = "/api/city")
public class CityRestController implements MyDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CityService cityService;

	@Autowired
	public CityRestController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping
	public @ResponseBody ResponseEntity<List<CityDto>> getAll() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(cityService.getAll());
	}

}
