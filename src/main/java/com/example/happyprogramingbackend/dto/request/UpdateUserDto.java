package com.example.happyprogramingbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
  private String password;

  private String rePassword;

  private String firstName;

  private String lastName;

  private String middleName;

  private Date birthday;

  private String gender;

  private Boolean isActive;

  private String description;

  private String skills;

  private String avatar;
}
