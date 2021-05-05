package mia.pecelj.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mia.pecelj.be.entity.ExamEntity;


@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long>{

}
