package com.kr.lg.repositories;


import com.kr.lg.entities.BoardTb;
import com.kr.lg.entities.UserTb;
import com.kr.lg.enums.entity.element.StatusEnum;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

public interface BoardRepository extends RootBoardRepository {
    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET status = :status WHERE boardId = :boardId")
    int updateBoardStatus(@Param("boardId") Long boardId, @Param("status") StatusEnum status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET title = :title, content = :content  WHERE boardId = :boardId")
    int updateBoard(@Param("boardId") Long boardId, @Param("title") String title, @Param("content") String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET report = report + 1  WHERE boardId = :boardId")
    int reportBoard(@Param("boardId") Long boardId);

    @Modifying
    @Query(value = "UPDATE BoardTb SET view = view + 1  WHERE boardId = :boardId")
    int viewBoard(@Param("boardId") Long boardId);

    long countByBoardIdAndUserTb(Long boardId, UserTb userTb);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT b FROM BoardTb b where b.boardId = :boardId")
    BoardTb findLockBoard(@Param("boardId") Long boardId);

    @Modifying
    @Query(value = "UPDATE BoardTb SET commentCount = commentCount + :count  WHERE boardId = :boardId")
    int updateCommentCount(@Param("boardId") Long boardId, Long count);

    @Modifying
    @Query(value = "UPDATE BoardTb SET recommendCount = recommendCount + :count WHERE boardId = :boardId")
    int updateRecommendCount(@Param("boardId") Long boardId, Long count);
}
