package com.example.happyprogramingbackend.Dto.Response;

import com.example.happyprogramingbackend.Entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {

    private Long id;


    private String subject;


    private String description;


    private String files;

    private String createdByName;

    private String avatar;

    public LessonResponse(Lesson entity) {
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.description = entity.getDescription();
        this.files = entity.getFiles();
        this.createdByName = entity.getCreatedByName();
        this.avatar = entity.getAvatar();

    }
}
