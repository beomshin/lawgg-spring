package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.MessageTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageTb, Long> {

}
