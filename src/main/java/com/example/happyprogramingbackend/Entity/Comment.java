package com.example.happyprogramingbackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private String createdByName;
    private String createdByEmail;
    private String createdByAvatar;
    private Long createdByUserId;

    @PostLoad
    public void logUserUpdate() {
        this.createdByUserId = this.createdBy.getId();
        this.createdByName = this.createdBy.getFirstName() + " " + this.createdBy.getMiddleName() + " " + this.createdBy.getLastName();
        this.createdByEmail = this.createdBy.getEmail();
        this.createdByAvatar = this.createdBy.getAvatar();
    }
}
