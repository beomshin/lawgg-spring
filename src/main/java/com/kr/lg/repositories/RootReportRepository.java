package com.kr.lg.repositories;

import com.kr.lg.entities.ReportTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootReportRepository extends JpaRepository<ReportTb, Long> {
}
