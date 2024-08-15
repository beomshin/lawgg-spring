package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.ReportTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportTb, Long> {

    Optional<ReportTb> findByBoardTbAndIp(BoardTb boardTb, String ip);
}
