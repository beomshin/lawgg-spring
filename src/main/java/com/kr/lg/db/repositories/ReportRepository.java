package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.ReportTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportTb, Long> {
}
