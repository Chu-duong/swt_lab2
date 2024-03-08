package com.example.happyprogramingbackend.Dto.Response;

import com.example.happyprogramingbackend.Entity.*;
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
public class UserResponseImp {
    private String firstName;

    private String lastName;

    private String middleName;

    private String username;

    private String email;

    private Long id;

    private String enrolled;

    private Long menteeCourseId;

    private String avatar;

    public UserResponseImp(User entity, String enrolled, Long menteeCourseId) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.firstName = entity.getFirstName();
        this.middleName = entity.getMiddleName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.enrolled = enrolled;
        this.menteeCourseId = menteeCourseId;
        if (entity.getAvatar() != null) {
            this.avatar = entity.getAvatar();
        } else {
            this.avatar = "https://vdostavka.ru/wp-content/uploads/2019/05/no-avatar.png";
        }
    }
}
