package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootBoardRepository extends JpaRepository<BoardTb, Long> {
}
