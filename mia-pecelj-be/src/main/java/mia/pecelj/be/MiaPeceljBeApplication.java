package mia.pecelj.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mia.pecelj.be.entity.CityEntity;
import mia.pecelj.be.entity.Semester;
import mia.pecelj.be.entity.StudentEntity;
import mia.pecelj.be.entity.SubjectEntity;
import mia.pecelj.be.mapper.CityEntityDtoMapper;
import mia.pecelj.be.mapper.StudentEntityDtoMapper;
import mia.pecelj.be.mapper.StudentEntityDtoMapperImpl;
import mia.pecelj.be.mapper.SubjectEntityDtoMapper;
import mia.pecelj.be.mapper.SubjectEntityDtoMapperImpl;

@SpringBootApplication
public class MiaPeceljBeApplication {

	public static void main(String[] args) {
		//int indexNumber, int indexYear, String firstname, String lastname, String email, String address,CityEntity city
		SpringApplication.run(MiaPeceljBeApplication.class, args);

	}

}
