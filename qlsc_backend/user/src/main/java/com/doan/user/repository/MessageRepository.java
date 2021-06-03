package com.doan.user.repository;

import com.doan.user.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.status = 1 and m.user.id = ?1")
    Page<Message> getMessagesByUserId(Pageable pageable, Long userId);

}
