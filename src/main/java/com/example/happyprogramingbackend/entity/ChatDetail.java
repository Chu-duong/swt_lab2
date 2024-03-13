package com.example.happyprogramingbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_detail")
public class ChatDetail extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "group_chat_id")
  private GroupChat groupChat;

  @Column(name = "type", nullable = true)
  private String type;
}
