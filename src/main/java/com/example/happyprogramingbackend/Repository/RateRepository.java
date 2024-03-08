package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.Entity.Course;
import com.example.happyprogramingbackend.Entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT m FROM Rate m where m.rated.id = ?2 and m.ratedBy.id= ?1")
    Rate getByStudentId(Long studentId,Long teacherId);

}
