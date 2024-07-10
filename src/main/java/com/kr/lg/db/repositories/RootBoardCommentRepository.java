package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardCommentTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootBoardCommentRepository extends JpaRepository<BoardCommentTb, Long> {

}
