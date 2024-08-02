package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardAttachTb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardAttachRepository extends JpaRepository<BoardAttachTb, Long> {

    List<BoardAttachTb> findByBoardTb_BoardId(long boardId);
}
