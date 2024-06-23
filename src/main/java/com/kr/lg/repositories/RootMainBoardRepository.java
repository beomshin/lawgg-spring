package com.kr.lg.repositories;

import com.kr.lg.entities.MainBoardTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootMainBoardRepository extends JpaRepository<MainBoardTb, Long> {
}
