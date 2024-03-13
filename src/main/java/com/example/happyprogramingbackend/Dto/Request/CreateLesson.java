package com.example.happyprogramingbackend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLesson {
    private Long courseId;

    private String subject;

    private String description;

    private String files;

}
