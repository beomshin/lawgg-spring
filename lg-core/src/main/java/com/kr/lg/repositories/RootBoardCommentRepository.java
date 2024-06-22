package com.kr.lg.repositories;

import com.kr.lg.entities.BoardCommentTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootBoardCommentRepository extends JpaRepository<BoardCommentTb, Long> {

}
