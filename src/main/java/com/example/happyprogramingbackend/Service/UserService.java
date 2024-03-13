package com.example.happyprogramingbackend.Service;

import com.example.happyprogramingbackend.common.Ultil;
import com.example.happyprogramingbackend.dto.Request.CreateUserDto;
import com.example.happyprogramingbackend.dto.Request.RejectStudent;
import com.example.happyprogramingbackend.dto.Request.UpdateUserDto;
import com.example.happyprogramingbackend.dto.Response.UserResponse;
import com.example.happyprogramingbackend.entity.*;
import com.example.happyprogramingbackend.Exception.BadRequestException;
import com.example.happyprogramingbackend.Exception.NotFoundException;
import com.example.happyprogramingbackend.Repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;
    private final RateRepository rateRepository;
    private final MenteeCourseRepository menteeCourseRepository;
    private final NotificationRepository notificationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private final ChatDetailRepository chatDetailRepository;

    public UserResponse add(CreateUserDto request) {
        Boolean flag = false;
        String password = Ultil.generateRandomPassword();
        if (request.getPassword() == null) {
            flag = true;
            request.setPassword(password);
        }
        if (!userRepository.findByEmail(request.getEmail()).isEmpty()) {
            throw new BadRequestException("Email has been used");
        }
        System.out.println(request.getPassword());
        User user = User.builder()
                .username(request.getEmail())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .isVerified(false)
                .password(passwordEncoder.encode(request.getPassword()))
                .description(request.getDescription())
                .skills(request.getSkills())
                .birthday(request.getBirthday())
                .role(roleRepository.findById(request.getRoleId()).orElseThrow(() -> {
                    throw new NotFoundException("Not found Role");
                }))
                .build();

        if (flag == true) {
            user.setIsVerified(true);
        }
        User userResponse = userRepository.save(user);
        String fullName = request.getFirstName() + " " + request.getMiddleName() + " " + request.getLastName();
        try {
            if (flag == true
            ) {
                emailService.sendResetPassword(request.getEmail(), fullName, password);
            } else {
                emailService.sendHtmlEmail(request.getEmail(), fullName, userResponse.getId());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new UserResponse(user);
    }

    public UserResponse myProfile(long id) {
        Optional<User> user = userRepository.findById(id);

        return new UserResponse(user.get());
    }

    public UserResponse update(Long id, UpdateUserDto request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found User");
        });
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        user.setIsActive(request.getIsActive());
        user.setDescription(request.getDescription());
        user.setSkills(request.getSkills());
        user.setAvatar(request.getAvatar());
        userRepository.save(user);

        return new UserResponse(user);
    }


    public List<UserResponse> search(String keyword, Long roleId, int page, Boolean isActive) {
        List<User> users;
        if (isActive != null) {
            users = userRepository.searchAllWithStatus(keyword, roleId, isActive);
        } else {
            users = userRepository.searchAllBy(keyword, roleId);
        }

        ArrayList<UserResponse> usersResponse = new ArrayList<>();
        for (User user : users) {
            usersResponse.add(new UserResponse(user));
        }

        return usersResponse;
    }

    public UserResponse verifyUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found User");
        });
        user.setIsVerified(true);
        userRepository.save(user);

        return new UserResponse(user);
    }

    public UserResponse getDetail(Long id) {
        return new UserResponse(userRepository.getById(id));
    }

    public User getRawDetail(Long id) {
        return userRepository.findById(id).get();
    }

    public Boolean getDetailByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new NotFoundException("Email not found in system");
        } else {
            return true;
        }

    }

    public Boolean resetPassword(String email) {
        User user = userRepository.findByEmail(email).get();
        String password = Ultil.generateRandomPassword();
        user.setPassword(passwordEncoder.encode(password));
        String fullName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
        try {
            emailService.sendResetPassword(email, fullName, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        userRepository.save(user);
        return true;
    }

    public Boolean changePassword(Long id, String oldPassword, String newPassword, String rePassword) {
        User user = userRepository.findById(id).get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadRequestException("Incorrect old password");
        }
        if (!newPassword.equals(rePassword)) {
            throw new BadRequestException("Password is not matches");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    public Boolean enrollCourse(Long id, Long courseId) {
        User user = userRepository.findById(id).get();
        if (user.getRole().getId() != 3) {
            throw new BadRequestException("You are not students");
        }
        Course course = courseRepository.findById(courseId).get();
        Optional<MenteeCourse> menteeCourse = menteeCourseRepository.getMenteeCourseBy(courseId, id);
        if (menteeCourse.isEmpty()) {
            menteeCourseRepository.save(MenteeCourse.builder()
                    .student(user)
                    .course(course)
                    .status("waiting")
                    .build());
        } else {
            menteeCourse.get().setStatus("waiting");
            menteeCourseRepository.save(menteeCourse.get());
        }

        return true;
    }

    public Boolean unEnrollCourse(Long id, Long courseId) {
        User user = userRepository.findById(id).get();
        if (user.getRole().getId() != 3) {
            throw new BadRequestException("You are not students");
        }
        Course course = courseRepository.findById(courseId).get();

        Set<MenteeCourse> menteeCourses = user.getMenteeCourse();

        for (MenteeCourse menteeCourse : menteeCourses) {
            if (menteeCourse.getCourse().getId() == courseId) {
                chatDetailRepository.deleteChatDetailByStudent(
                        user.getId(),
                        menteeCourse.getCourse().getGroupChat().getId()
                );
                menteeCourseRepository.deleteById(menteeCourse.getId());
            }
        }

        return true;
    }

    public Boolean rateUser(Long studentId, Long teacherId, Float rateValue) {
        User teacher = userRepository.findById(teacherId).get();
        User student = userRepository.findById(studentId).get();
        Rate rate = rateRepository.getByStudentId(studentId, teacherId);
        if (rate == null) {
            rate = Rate.builder()
                    .ratedBy(student)
                    .rated(teacher)
                    .rateValue(rateValue)
                    .build();
        } else {
            rate.setRateValue(rateValue);
        }

        rateRepository.save(rate);
        return true;
    }

    public Float getRateUser(Long studentId, Long teacherId) {
        User teacher = userRepository.findById(teacherId).get();
        User student = userRepository.findById(studentId).get();
        Rate rate = rateRepository.getByStudentId(studentId, teacherId);
        if (rate == null) {
            return (float) 0;
        }
        return rate.getRateValue();
    }

    public Boolean approveStudent(Long menteeCourseId, String status, User user, RejectStudent body) {
        MenteeCourse menteeCourse = menteeCourseRepository.findById(menteeCourseId).get();
        menteeCourse.setStatus(status);
        menteeCourseRepository.save(menteeCourse);
        Notification notification = new Notification();
        notification.setCreatedBy(user);
        notification.setSendTo(menteeCourse.getStudent());
        if (status.equals("approved")) {
            notification.setDescription("You have been ACCEPTED to join course: " + menteeCourse.getCourse().getName());
            ChatDetail chatDetail = ChatDetail.builder()
                    .user(menteeCourse.getStudent())
                    .groupChat(menteeCourse.getCourse().getGroupChat())
                    .type("ST")
                    .build();

            chatDetailRepository.save(chatDetail);
        }
        if (status.equals("rejected")) {
            notification.setDescription("You have been REJECTED to join course: "
                    + menteeCourse.getCourse().getName()
                    + " with reason: " + body.getReason());

        }

        if (status.equals("ban")) {
            notification.setDescription("You have been BAN to join course: "
                    + menteeCourse.getCourse().getName());

        }

        if (status.equals("unban")) {
            notification.setDescription("You have been UNBAN to join course: "
                    + menteeCourse.getCourse().getName());
        }
        notificationRepository.save(notification);

        return true;
    }
}
