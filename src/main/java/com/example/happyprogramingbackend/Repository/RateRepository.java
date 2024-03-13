package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

  @Query("SELECT m FROM Rate m where m.rated.id = ?2 and m.ratedBy.id= ?1")
  Rate getByStudentId(Long studentId, Long teacherId);
}
