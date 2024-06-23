package com.kr.lg.repositories;

import com.kr.lg.entities.AlertTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootAlertRepository extends JpaRepository<AlertTb, Long> {
}
