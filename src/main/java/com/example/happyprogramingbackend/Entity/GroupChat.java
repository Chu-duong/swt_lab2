package com.example.happyprogramingbackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_chat")
public class GroupChat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;


    @Column(name = "type", nullable = true)
    private String type;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User sendTo;


    @Column(name = "description", columnDefinition = "TEXT")
    private String description;


    @JsonBackReference
    @OneToMany(mappedBy = "groupChat")
    private List<ChatDetail> chatDetails = new ArrayList<>();;
}
