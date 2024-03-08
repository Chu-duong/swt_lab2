package com.example.happyprogramingbackend.Repository;

import com.example.happyprogramingbackend.Entity.ChatDetail;
import com.example.happyprogramingbackend.Entity.Course;
import com.example.happyprogramingbackend.Entity.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatDetailRepository extends JpaRepository<ChatDetail, Long> {

    @Query("SELECT m FROM ChatDetail m WHERE (m.user.id = ?1)")
    List<ChatDetail> getGroupChatByUser(Long userId);

    @Modifying
    @Transactional
    @Query("delete from ChatDetail b where b.groupChat.id = ?1 and b.type = 'TC'")
    void deleteChatDetailBy(Long courseId);

    @Modifying
    @Transactional
    @Query("delete from ChatDetail b where b.user.id = ?1 and b.groupChat.id = ?2")
    void deleteChatDetailByStudent(Long studentId,Long groupChatId);
}

