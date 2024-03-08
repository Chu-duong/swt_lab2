package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.Entity.Comment;
import com.example.happyprogramingbackend.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}