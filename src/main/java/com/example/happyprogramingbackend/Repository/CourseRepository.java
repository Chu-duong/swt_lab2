package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  @Query("SELECT m FROM Course m WHERE (m.courseKey LIKE %?1% or m.name LIKE %?1% )")
  List<Course> searchAllBy(String keyword);

  @Query(
      "SELECT m FROM Course m WHERE (m.courseKey LIKE %?1% or m.name LIKE %?1% ) and m.isActive = ?2")
  List<Course> searchAllWithStatus(String keyword, Boolean isActive);
}
