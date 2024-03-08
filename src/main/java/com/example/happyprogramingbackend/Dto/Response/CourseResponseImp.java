package com.example.happyprogramingbackend.Dto.Response;

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
public class CourseResponseImp {
    private Long id;

    private String courseKey;

    private String name;

    private String description;

    private Set<UserResponse> mentors;

    private Date createdAt;

    private Boolean isActive;

    private List<Lesson> lessons;

    private String enrolled;

    private String avatar;

    public Set<UserResponse> deleteMentorCourse(Set<User> users) {
        Set<UserResponse> usersResponse = new HashSet<>();
        for (User user : users) {
            usersResponse.add(new UserResponse(user));
        }
        return usersResponse;
    }

    public CourseResponseImp(Course entity, String status) {
        this.id = entity.getId();
        this.courseKey = entity.getCourseKey();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.mentors = deleteMentorCourse(entity.getMentors());
        this.createdAt = entity.getCreatedAt();
        this.isActive = entity.getIsActive();
        this.avatar = entity.getAvatar();
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
            }
            this.lessons = entity.getLessons();
        }
        this.enrolled = status;
    }
}
