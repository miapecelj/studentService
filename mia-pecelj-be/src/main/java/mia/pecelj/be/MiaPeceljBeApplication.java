package mia.pecelj.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mia.pecelj.be.entity.Semester;
import mia.pecelj.be.entity.SubjectEntity;
import mia.pecelj.be.mapper.SubjectEntityDtoMapper;
import mia.pecelj.be.mapper.SubjectEntityDtoMapperImpl;

@SpringBootApplication
public class MiaPeceljBeApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MiaPeceljBeApplication.class, args);
		SubjectEntityDtoMapper mapper=new SubjectEntityDtoMapperImpl();
		SubjectEntity subject = new SubjectEntity(1,"Matematika1","Math introducition",6,1,Semester.SUMMER);
		System.out.println(mapper.toDto(subject));
		System.out.println();
	}

}
