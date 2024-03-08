package com.example.happyprogramingbackend.Dto.Response;

import com.example.happyprogramingbackend.Entity.ChatDetail;
import com.example.happyprogramingbackend.Entity.Comment;
import com.example.happyprogramingbackend.Entity.GroupChat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDetailResponseImp {
    private Long id;
    private Long groupChatId;

    private String avatar;

    private String key;
    private String status;
    private Boolean statusCourse;
    public ChatDetailResponseImp(ChatDetail entity,String role) {
        this.id = entity.getId();
        this.avatar = entity.getGroupChat().getCourse().getAvatar();
        this.key = entity.getGroupChat().getCourse().getName();
        this.groupChatId = entity.getGroupChat().getId();
        if(role == "TC") {
            this.status = (entity.getGroupChat().getCourse()
                    .getStudents().stream().filter(menteeCourse -> menteeCourse.getStudent().getId() == entity.getUser().getId()).findFirst()).get().getStatus();
        }
       this.statusCourse=entity.getGroupChat().getCourse().getIsActive();

    }
}
