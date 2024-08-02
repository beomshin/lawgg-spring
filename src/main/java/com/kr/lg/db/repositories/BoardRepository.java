package com.kr.lg.db.repositories;


import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.enums.WriterEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardTb, Long> {

    Optional<BoardTb> findByBoardIdAndWriterType(long boardId, WriterEnum writerEnum); // 게시판 조회

    Optional<BoardTb> findByBoardIdAndWriterTypeAndStatus(long boardId, WriterEnum writerEnum, StatusEnum statusEnum); // 정상 게시판 조회

    @Modifying
    @Query(value = "UPDATE BoardTb SET view = view + 1  WHERE boardId = :boardId")
    void increaseCount(@Param("boardId") Long boardId); // 조회수 증가 ToDo 레디스 처리 (동시성)

    @Modifying
    @Query(value = "UPDATE BoardTb SET title = :title, content = :content  WHERE boardId = :boardId")
    void updateBoard(@Param("boardId") Long boardId, @Param("title") String title, @Param("content") String content); // 포지션 게시판 업데이트

    @Modifying
    @Query(value = "UPDATE BoardTb SET status = 2 WHERE boardId = :boardId")
    void deleteBoard(@Param("boardId") Long boardId); // 포지션 게시판 삭제

    @Modifying
    @Query(value = "UPDATE BoardTb SET report = report + 1  WHERE boardId = :boardId")
    void reportBoard(@Param("boardId") Long boardId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT b FROM BoardTb b where b.boardId = :boardId")
    BoardTb findLockBoard(@Param("boardId") Long boardId);

    @Modifying
    @Query(value = "UPDATE BoardTb SET commentCount = commentCount + :count  WHERE boardId = :boardId")
    void updateCommentCount(@Param("boardId") long boardId, long count);

    @Modifying
    @Query(value = "UPDATE BoardTb SET recommendCount = recommendCount + :count WHERE boardId = :boardId")
    void updateRecommendCount(@Param("boardId") Long boardId, Long count);
}
