package com.example.happyprogramingbackend.controller;

import com.example.happyprogramingbackend.dto.Request.*;
import com.example.happyprogramingbackend.entity.User;
import com.example.happyprogramingbackend.Service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseDto request) {
        return new ResponseEntity<>(courseService.createCourse(request), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourseDetail(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getCourse(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "isActive", required = false) Boolean isActive) {
        return new ResponseEntity<>(courseService.getListCourse(keyword, isActive), HttpStatus.OK);
    }

    @GetMapping("/by-teacher/{id}")
    public ResponseEntity<?> getCourseByTeacher(
            @PathVariable Long id,
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        return new ResponseEntity<>(courseService.getListCourseByTeacher(id, keyword), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody UpdateCourseDto request) {
        return new ResponseEntity<>(courseService.updateCourse(id, request), HttpStatus.OK);
    }

    @PostMapping("/create-lesson")
    public ResponseEntity<?> updateCourse(@RequestBody CreateLesson request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(courseService.createLesson(request, user), HttpStatus.OK);
    }

    @PutMapping("/update-lesson/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody UpdateLesson request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(courseService.updateLesson(id, request), HttpStatus.OK);
    }

    @GetMapping("/get-lesson/{id}")
    public ResponseEntity<?> getLesson(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getDetailLesson(id), HttpStatus.OK);
    }

    @PostMapping("lesson/create-comment")
    public ResponseEntity<?> createComment(
            @RequestBody CreateCommentDto body
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(courseService.createComment(user,body), HttpStatus.OK);
    }

    @PutMapping("lesson/update-comment/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentDto body
    ) {
        return new ResponseEntity<>(courseService.updateComment(commentId,body), HttpStatus.OK);
    }

    @DeleteMapping("lesson/delete-comment/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId
    ) {
        return new ResponseEntity<>(courseService.deleteComment(commentId), HttpStatus.OK);
    }


    @GetMapping("chat/get-list-group")
    public ResponseEntity<?> getList(

    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(courseService.getListGroupChat(user), HttpStatus.OK);
    }


}
