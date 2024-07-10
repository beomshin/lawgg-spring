package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardAttachTb;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RootBoardAttachRepository extends JpaRepository<BoardAttachTb, Long> {

}
