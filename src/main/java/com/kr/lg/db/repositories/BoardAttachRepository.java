package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.BoardAttachTb;

import java.util.List;

public interface BoardAttachRepository extends RootBoardAttachRepository {

    List<BoardAttachTb> findByBoardTb_BoardId(long boardId);
}
