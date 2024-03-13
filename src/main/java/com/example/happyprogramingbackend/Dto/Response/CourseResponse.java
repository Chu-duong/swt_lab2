package com.example.happyprogramingbackend.dto.Response;

import com.example.happyprogramingbackend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;

    private String courseKey;

    private String name;

    private String description;

    private Set<UserResponse> mentors;

    private Date createdAt;

    private Boolean isActive;

    private List<Lesson> lessons;

    private Set<UserResponseImp> students;

    private String avatar;

    public Set<UserResponse> deleteMentorCourse(Set<User> users) {
        Set<UserResponse> usersResponse = new HashSet<>();
        for (User user : users) {
            usersResponse.add(new UserResponse(user));
        }
        return usersResponse;
    }

    public Set<UserResponseImp> getMentee(Set<MenteeCourse> menteeCourses) {
        Set<UserResponseImp> usersResponse = new HashSet<>();
        for (MenteeCourse menteeCourse : menteeCourses) {
            usersResponse.add(new UserResponseImp(menteeCourse.getStudent(), menteeCourse.getStatus(), menteeCourse.getId()));
        }

        return usersResponse;
    }


    public CourseResponse(Course entity) {
        this.avatar = entity.getAvatar();
        this.id = entity.getId();
        this.courseKey = entity.getCourseKey();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.mentors = deleteMentorCourse(entity.getMentors());
        this.createdAt = entity.getCreatedAt();
        this.isActive = entity.getIsActive();
        if (entity.getLessons() != null) {
            for (Lesson lesson : entity.getLessons()) {
                lesson.setCourse(null);
                if (lesson.getCreatedBy() != null) {
                    lesson.setCreatedByName(
                            lesson.getCreatedBy().getFirstName() + " " +
                                    lesson.getCreatedBy().getMiddleName() + " " +
                                    lesson.getCreatedBy().getLastName());
                    lesson.setAvatar(lesson.getCreatedBy().getAvatar());
                    lesson.setCreatedBy(null);
                }

                if (lesson.getComment() != null) {
                    for(Comment comment : lesson.getComment()) {
                        comment.setLesson(null);
                        comment.setCreatedBy(null);
                    }

                }


            }
            this.lessons = entity.getLessons();
        }
        if (entity.getStudents() != null)
            this.students = getMentee(entity.getStudents());
    }
}
