package com.example.happyprogramingbackend.Dto.Response;

import com.example.happyprogramingbackend.Entity.Role;
import com.example.happyprogramingbackend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListUserResponse {
    private String firstName;

    private String lastName;

    private String middleName;

    private String username;

    private Long id;

    private Role role;

    private Date birthday;

    public ListUserResponse(User  entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.firstName = entity.getFirstName();
        this.middleName = entity.getMiddleName();
        this.lastName = entity.getLastName();
        this.birthday = entity.getBirthday();
        this.role = entity.getRole();
    }


}
