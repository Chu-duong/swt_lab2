package com.example.happyprogramingbackend.dto.response;

import com.example.happyprogramingbackend.entity.Notification;
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
