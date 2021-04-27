package mia.pecelj.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mia.pecelj.be.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long>{

}
