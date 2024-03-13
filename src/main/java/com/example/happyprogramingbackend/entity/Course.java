package com.example.happyprogramingbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "course_key")
  private String courseKey;

  @Column(name = "name")
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "avatar")
  private String avatar;

  @ManyToMany()
  @JoinTable(
      name = "mentor_course",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "mentor_id"))
  Set<User> mentors;

  //    @ManyToMany(mappedBy = "menteeCourse")
  //    Set<User> students;

  @OneToMany(mappedBy = "course")
  private Set<MenteeCourse> students;

  @OneToMany(mappedBy = "course")
  private List<Lesson> lessons = new ArrayList<>();

  @Column(name = "group_chat_id")
  private Long groupChatId;

  @OneToOne(mappedBy = "course")
  private GroupChat groupChat;
}
