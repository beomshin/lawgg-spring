package com.kr.lg.repositories;

import com.kr.lg.entities.BoardTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootBoardRepository extends JpaRepository<BoardTb, Long> {
}
