package com.kr.lg.db.repositories;

import com.kr.lg.common.enums.StatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardCommentRepository extends RootBoardCommentRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET status = :status WHERE boardCommentId = :boardCommentId")
    int updateBoardCommentStatus(@Param("boardCommentId") Long boardCommentId, @Param("status") StatusEnum status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET content = :content WHERE boardCommentId = :boardCommentId")
    int updateBoardComment(@Param("boardCommentId") Long boardCommentId, @Param("content") String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET report = report + 1  WHERE boardCommentId = :boardCommentId")
    int reportBoardComment(@Param("boardCommentId") Long boardCommentId);



}
