package com.example.happyprogramingbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseDto {
    private String courseKey;

    private String name;

    private String description;

    private List<Long> mentors;

    private String avatar;
}
