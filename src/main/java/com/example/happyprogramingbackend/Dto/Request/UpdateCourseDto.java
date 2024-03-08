package com.example.happyprogramingbackend.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseDto {
    private String courseKey;

    private String name;

    private String description;

    private List<Long> mentors;

    private Boolean isActive;

    private String avatar;
}
