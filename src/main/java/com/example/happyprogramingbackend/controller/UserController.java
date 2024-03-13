package com.example.happyprogramingbackend.controller;

import com.example.happyprogramingbackend.Service.UserService;
import com.example.happyprogramingbackend.dto.request.*;
import com.example.happyprogramingbackend.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("my-profile")
    public ResponseEntity<?> myProfile() {
        User authen = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(userService.myProfile(authen.getId()), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> searchUser(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "roleId", required = true) Long roleId,
            @RequestParam(name = "isActive", required = false) Boolean isActive,
            @RequestParam(name = "page", required = true) int page
    ) {

        return new ResponseEntity<>(userService.search(keyword, roleId, page, isActive), HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto request) {
        return new ResponseEntity<>(userService.add(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto request) {
        return new ResponseEntity<>(userService.update(id, request), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> detailUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getDetail(id), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.changePassword(
                user.getId(),
                request.getOldPassword(),
                request.getNewPassword(),
                request.getRePassword()
        ), HttpStatus.OK);
    }

    @PutMapping("/enroll-course")
    public ResponseEntity<?> enrollCourse(
            @RequestParam(name = "courseId", required = true) Long courseId
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.enrollCourse(
                user.getId(),
                courseId
        ), HttpStatus.OK);
    }

    @PutMapping("/unenroll-course")
    public ResponseEntity<?> unEnrollCourse(
            @RequestParam(name = "courseId", required = true) Long courseId
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.unEnrollCourse(
                user.getId(),
                courseId
        ), HttpStatus.OK);
    }

    @PutMapping("/approve-student")
    public ResponseEntity<?> approveStudent(
            @RequestParam(name = "menteeCourseId", required = true) String menteeCourseId,
            @RequestParam(name = "status", required = true) String status,
            @RequestBody() RejectStudent body
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(
                userService.approveStudent(
                        Long.parseLong(menteeCourseId),
                        status,
                        user,
                        body
                ), HttpStatus.OK);
    }

    @PutMapping("/rate-teacher")
    public ResponseEntity<?> rateTeacher(
            @RequestBody() RateRequest body
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.rateUser(
                user.getId(),
                body.getTeacherId(),
                body.getRateValue()
        ), HttpStatus.OK);
    }

    @GetMapping("/get-rate-teacher")
    public ResponseEntity<?> getRateTeacher(
            @RequestParam(name = "teacherId", required = true) String teacherId
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.getRateUser(
                user.getId(),
                Long.parseLong(teacherId)
        ), HttpStatus.OK);
    }
}
