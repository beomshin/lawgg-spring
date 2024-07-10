package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.MailTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootMailRepository extends JpaRepository<MailTb, Long> {
}
