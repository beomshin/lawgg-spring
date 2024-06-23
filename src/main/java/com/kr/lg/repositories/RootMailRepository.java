package com.kr.lg.repositories;

import com.kr.lg.entities.MailTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootMailRepository extends JpaRepository<MailTb, Long> {
}
