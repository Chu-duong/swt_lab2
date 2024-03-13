package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {}
