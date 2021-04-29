package mia.pecelj.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mia.pecelj.be.entity.ExamPeriodEntity;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriodEntity, Long>{

}
