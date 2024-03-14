package com.example.happyprogramingbackend.dto.response;

import com.example.happyprogramingbackend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
