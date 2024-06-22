package com.kr.lg.repositories;


import com.kr.lg.entities.MessageTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootMessageRepository extends JpaRepository<MessageTb, Long> {



}
