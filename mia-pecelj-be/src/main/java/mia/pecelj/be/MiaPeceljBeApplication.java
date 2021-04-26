package mia.pecelj.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mia.pecelj.be.entity.CityEntity;
import mia.pecelj.be.entity.Semester;
import mia.pecelj.be.entity.StudentEntity;
import mia.pecelj.be.entity.SubjectEntity;
import mia.pecelj.be.mapper.CityEntityDtoMapper;
import mia.pecelj.be.mapper.StudentEntityDtoMapper;

@SpringBootApplication
public class MiaPeceljBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiaPeceljBeApplication.class, args);

	}

}
