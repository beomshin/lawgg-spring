package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.MainBoardTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainBoardRepository extends JpaRepository<MainBoardTb, Long> {
}
