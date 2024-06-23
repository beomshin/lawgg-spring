package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.AlertTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootAlertRepository extends JpaRepository<AlertTb, Long> {
}
