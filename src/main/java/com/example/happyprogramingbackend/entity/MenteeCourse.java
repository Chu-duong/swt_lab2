package com.example.happyprogramingbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mentee_course")
public class MenteeCourse extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "student_id")
  private User student;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @Column(name = "status", nullable = false)
  private String status = "waiting";
}
