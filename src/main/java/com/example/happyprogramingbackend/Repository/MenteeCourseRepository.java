package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.entity.MenteeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenteeCourseRepository extends JpaRepository<MenteeCourse, Long> {

    @Query("SELECT m FROM MenteeCourse m WHERE m.course.id = ?1  and m.student.id  = ?2")
    Optional<MenteeCourse> getMenteeCourseBy(Long courseId, Long studentId);
}