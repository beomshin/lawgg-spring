package com.kr.lg.db.repositories;


import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.enums.WriterEnum;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface BoardRepository extends RootBoardRepository {

    Optional<BoardTb> findByBoardIdAndWriterType(long boardId, WriterEnum writerEnum);

    @Modifying
    @Query(value = "UPDATE BoardTb SET view = view + 1  WHERE boardId = :boardId")
    void increaseCount(@Param("boardId") Long boardId); // 조회수 증가 ToDo 레디스 처리 (동시성)

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET status = :status WHERE boardId = :boardId")
    int updateBoardStatus(@Param("boardId") Long boardId, @Param("status") StatusEnum status);

    @Modifying
    @Query(value = "UPDATE BoardTb SET title = :title, content = :content  WHERE boardId = :boardId")
    void updateBoard(@Param("boardId") Long boardId, @Param("title") String title, @Param("content") String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardTb SET report = report + 1  WHERE boardId = :boardId")
    int reportBoard(@Param("boardId") Long boardId);

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
