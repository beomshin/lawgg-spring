package com.kr.lg.db.repositories;


import com.kr.lg.db.entities.MessageTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootMessageRepository extends JpaRepository<MessageTb, Long> {



}
