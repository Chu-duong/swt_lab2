package com.example.happyprogramingbackend.Dto.Response;

import com.example.happyprogramingbackend.Entity.Comment;
import com.example.happyprogramingbackend.Entity.Course;
import com.example.happyprogramingbackend.Entity.Lesson;
import com.example.happyprogramingbackend.Entity.User;
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
public class CommentResponseImp {
    private Long id;

    private String createdByName;
    private String createdByEmail;
    private String createdByAvatar;
    private Long createdByUserId;

    private String description;

    private Date createdAt;

    public CommentResponseImp(Comment entity) {
        this.id = entity.getId();
        this.createdByUserId = entity.getCreatedBy().getId();
        this.createdByName= entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getMiddleName() + " " + entity.getCreatedBy().getLastName();;
        this.createdByEmail = entity.getCreatedBy().getEmail();
        this.createdByAvatar = entity.getCreatedBy().getAvatar();
        this.description = entity.getDescription();
        this.createdAt = entity.getCreatedAt();
    }
}
