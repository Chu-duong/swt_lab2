package com.example.happyprogramingbackend.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "isVerified")
    private Boolean isVerified = false;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "skills", nullable = true)
    private String skills;


    @Column(name = "avatar", nullable = true)
    private String avatar;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getIsVerified();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getIsActive();
    }

    @ManyToMany(mappedBy = "mentors")
    Set<Course> mentorCourse;

//    @ManyToMany()
//    @JoinTable(
//            name = "mentee_course",
//            joinColumns = @JoinColumn(name = "mentee_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id"))
//    Set<Course> menteeCourse;

    @OneToMany(mappedBy = "student")
    private Set<MenteeCourse> menteeCourse;

    @OneToMany(mappedBy = "ratedBy")
    private List<Rate> rateTo = new ArrayList<>();


    @OneToMany(mappedBy = "rated")
    private List<Rate> rated = new ArrayList<>();

    @OneToMany(mappedBy = "sendTo")
    private List<Notification> notificationTo = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @Transient
    private List<Lesson> createdTo = new ArrayList<>();
}

