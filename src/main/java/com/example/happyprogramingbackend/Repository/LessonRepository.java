package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.Entity.Lesson;
import com.example.happyprogramingbackend.Entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {


}
