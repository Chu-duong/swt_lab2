package com.example.happyprogramingbackend.Dto.Response;

import com.example.happyprogramingbackend.Entity.Notification;
import com.example.happyprogramingbackend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseImp {
    private String sendBy;

    private String sendByAvatar;

    private String description;


    public NotificationResponseImp(Notification entity) {

        this.sendByAvatar = entity.getCreatedBy().getAvatar();
        this.sendBy = entity.getCreatedBy().getEmail();
        this.description = entity.getDescription();

    }
}
