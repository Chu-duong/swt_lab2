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
public class UserResponse {
    private String firstName;

    private String lastName;

    private String middleName;

    private String username;

    private Long id;

    private Role role;

    private Date birthday;

    private Boolean isActive;

    private String gender;

    private String email;

    private Set<CourseResponse> course;

    private Set<CourseResponseImp> menteeCourse;

    private Float rated;

    private List<Rate> ratedTo;

    private String description;

    private String[] skills;

    private String avatar;

    private ArrayList<NotificationResponseImp> notification;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.firstName = entity.getFirstName();
        this.middleName = entity.getMiddleName();
        this.lastName = entity.getLastName();
        this.birthday = entity.getBirthday();
        this.role = entity.getRole();
        this.isActive = entity.getIsActive();
        this.email = entity.getEmail();
        this.gender = entity.getGender();
        if (entity.getAvatar() != null) {
            this.avatar = entity.getAvatar();
        } else {
            this.avatar = "https://vdostavka.ru/wp-content/uploads/2019/05/no-avatar.png";
        }
        ;
        if (entity.getMentorCourse() != null) {
            Set<CourseResponse> set = new HashSet<>();
            for (Course course : entity.getMentorCourse()) {
                course.setMentors(new HashSet<>());
                //course.setStudents(new HashSet<>());
                set.add(new CourseResponse(course));
            }

            this.course = set;
        }

        if (entity.getMenteeCourse() != null) {
            Set<CourseResponseImp> set = new HashSet<>();
            for (MenteeCourse course : entity.getMenteeCourse()) {
                course.getCourse().setMentors(new HashSet<>());
                //course.getCourse().setStudents(new HashSet<>());
                set.add(new CourseResponseImp(course.getCourse(), course.getStatus()));
            }

            this.menteeCourse = set;
        }

        if (entity.getRated() != null) {
            Float sum = (float) 0;
            for (int i = 0; i < entity.getRated().size(); i++) {
                sum += entity.getRated().get(i).getRateValue();
            }
            this.rated = sum / entity.getRated().size();
        }

        this.description = entity.getDescription();
        if (entity.getSkills() != null) {
            this.skills = entity.getSkills().split(",");
        }

        if (entity.getNotificationTo() != null) {
            ArrayList<NotificationResponseImp> arrayList = new ArrayList<>();
            for (Notification notification : entity.getNotificationTo()) {
                arrayList.add(new NotificationResponseImp(notification));
            }
            this.notification = arrayList;
        }
    }
}
