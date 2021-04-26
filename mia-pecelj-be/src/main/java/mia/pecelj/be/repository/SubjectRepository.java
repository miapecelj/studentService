package mia.pecelj.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mia.pecelj.be.entity.SubjectEntity;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long>{

}
