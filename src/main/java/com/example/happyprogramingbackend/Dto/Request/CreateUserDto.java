package com.example.happyprogramingbackend.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    private Long roleId;

    private Date birthday;

    private String email;

    private String gender;

    private String description;

    private String skills;

    private String avatar;
}
