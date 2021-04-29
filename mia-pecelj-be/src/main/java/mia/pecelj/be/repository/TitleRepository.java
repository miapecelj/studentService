package mia.pecelj.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mia.pecelj.be.entity.TitleEntity;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long> {

}
