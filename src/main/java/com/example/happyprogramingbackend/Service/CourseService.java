package com.example.happyprogramingbackend.Service;

import com.example.happyprogramingbackend.Dto.Request.*;
import com.example.happyprogramingbackend.Dto.Response.ChatDetailResponseImp;
import com.example.happyprogramingbackend.Dto.Response.CommentResponseImp;
import com.example.happyprogramingbackend.Dto.Response.CourseResponse;
import com.example.happyprogramingbackend.Dto.Response.LessonResponse;
import com.example.happyprogramingbackend.Entity.*;
import com.example.happyprogramingbackend.Exception.BadRequestException;
import com.example.happyprogramingbackend.Exception.NotFoundException;
import com.example.happyprogramingbackend.Repository.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final LessonRepository lessonRepository;
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final GroupChatRepository groupChatRepository;
    @Autowired
    private final ChatDetailRepository chatDetailRepository;

    public CourseResponse createCourse(CreateCourseDto request) {
        ArrayList<User> users = new ArrayList<>();
        if (request.getMentors() != null) {
            for (Long userId : request.getMentors()) {
                User user = userService.getRawDetail(userId);
                if (user == null || user.getRole().getId() != 2) {
                    throw new BadRequestException("Teacher not found");
                }
                users.add(user);
            }
        }
        Set<User> mentors = new HashSet<>();
        if (users.size() > 0) {
            for (User e : users) {
                mentors.add(e);
            }
        }
        Course course = Course.builder()
                .courseKey(request.getCourseKey())
                .name(request.getName())
                .description(request.getDescription())
                .mentors(mentors)
                .avatar(request.getAvatar())
                .build();

        Course courseReturn = courseRepository.save(course);

        GroupChat groupChat = GroupChat.builder()
                .course(courseReturn)
                .type("GROUP_CHAT")
                .build();
        groupChatRepository.save(groupChat);
        groupChat.setCourse(courseReturn);
        groupChatRepository.save(groupChat);

        courseReturn.setGroupChatId(groupChat.getId());
        courseRepository.save(courseReturn);

        if (users.size() > 0) {
            for (User e : users) {
                ChatDetail chatDetail = ChatDetail.builder()
                        .user(e)
                        .groupChat(groupChat)
                        .type("TC")
                        .build();

                chatDetailRepository.save(chatDetail);
            }
        }


        return new CourseResponse(courseReturn);
    }

    public CourseResponse updateCourse(Long id, UpdateCourseDto request) {
        Course course = courseRepository.findById(id).get();
        Set<User> mentorsOut = new HashSet<>();
        if (course.getGroupChat() != null)
            chatDetailRepository.deleteChatDetailBy(course.getGroupChat().getId());
        Set<User> mentors = new HashSet<>();
        if (request.getMentors() != null) {
            for (Long userId : request.getMentors()) {
                User user = userService.getRawDetail(userId);
                if (user == null || user.getRole().getId() != 2) {
                    throw new BadRequestException("Teacher not found");
                }

                mentors.add(user);
            }
        }

        if (mentors.size() > 0) {
            for (User e : mentors) {
                ChatDetail chatDetail = ChatDetail.builder()
                        .user(e)
                        .groupChat(course.getGroupChat())
                        .type("TC")
                        .build();

                chatDetailRepository.save(chatDetail);
            }
        }


        course.setCourseKey(request.getCourseKey());
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setMentors(mentors);
        course.setIsActive(request.getIsActive());
        course.setAvatar(request.getAvatar());
        Course courseReturn = courseRepository.save(course);

        return new CourseResponse(courseReturn);
    }

    public CourseResponse getCourseDetail(Long id) {
        Course course = courseRepository.findById(id).get();
        return new CourseResponse(course);
    }

    public ArrayList<CourseResponse> getListCourse(String keyword, Boolean isActive) {
        if (keyword == null) {
            keyword = "";
        }

        ArrayList<CourseResponse> courseResponse = new ArrayList<>();
        List<Course> courses;
        if (isActive != null) {
            courses = courseRepository.searchAllWithStatus(keyword, isActive);
        } else {
            courses = courseRepository.searchAllBy(keyword);
        }

        for (Course course : courses) {
            courseResponse.add(new CourseResponse(course));
        }

        return courseResponse;
    }

    public Set<CourseResponse> getListCourseByTeacher(Long id, String keyword) {
        if (keyword == null) {
            keyword = "";
        }
        Set<CourseResponse> courses = userService.getDetail(id).getCourse();


        return courses;
    }

    public CourseResponse createLesson(CreateLesson request, User user) {
        Course course = courseRepository.findById(request.getCourseId()).get();
        Lesson lesson = Lesson.builder()
                .subject(request.getSubject())
                .description(request.getDescription())
                .files(request.getFiles())
                .createdBy(user)
                .course(course)
                .build();
        lessonRepository.save(lesson);
        return new CourseResponse(course);
    }

    public CourseResponse updateLesson(Long id, UpdateLesson request) {
        Lesson lesson = lessonRepository.findById(id).get();
        lesson.setSubject(request.getSubject());
        lesson.setFiles(request.getFiles());
        lesson.setDescription(request.getDescription());
        lessonRepository.save(lesson);
        Course course = courseRepository.findById(lesson.getCourse().getId()).get();

        return new CourseResponse(course);
    }

    public LessonResponse getDetailLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();

        return new LessonResponse(lesson);
    }

    public CommentResponseImp createComment(User user, CreateCommentDto body) {
        Lesson lesson = lessonRepository.findById(body.getLessonId()).get();
        Comment comment = new Comment();
        comment.setCreatedBy(user);
        comment.setLesson(lesson);
        comment.setDescription(body.getDescription());
        commentRepository.save(comment);
        return new CommentResponseImp(comment);
    }

    public CommentResponseImp updateComment(Long id, UpdateCommentDto body) {
        Comment comment = commentRepository.findById(id).get();
        comment.setDescription(body.getDescription());
        commentRepository.save(comment);
        return new CommentResponseImp(comment);
    }

    public Boolean deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        commentRepository.delete(comment);
        return true;
    }

    public List<ChatDetailResponseImp> getListGroupChat(User user) {
        List<ChatDetail> chatDetails = new ArrayList<>();
        if (user.getRole().getRoleKey().equals("AD")) {
            chatDetails = chatDetailRepository.findAll();
        } else {
            chatDetails = chatDetailRepository.getGroupChatByUser(user.getId());
        }
        List<ChatDetailResponseImp> chatDetailsRespone = new ArrayList<>();
        for (ChatDetail chatDetail : chatDetails) {
            chatDetailsRespone.add(new ChatDetailResponseImp(chatDetail,user.getRole().getRoleKey()));
        }
        return chatDetailsRespone;
    }

}