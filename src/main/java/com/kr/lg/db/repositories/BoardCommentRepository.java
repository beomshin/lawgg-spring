package com.kr.lg.db.repositories;

import com.kr.lg.enums.StatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardCommentRepository extends RootBoardCommentRepository {

    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET status = :status WHERE boardCommentId = :boardCommentId")
    void updateBoardCommentStatus(@Param("boardCommentId") long boardCommentId, @Param("status") StatusEnum status);

    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET content = :content WHERE boardCommentId = :boardCommentId")
    void updateBoardComment(@Param("boardCommentId") long boardCommentId, @Param("content") String content);

    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET report = report + 1  WHERE boardCommentId = :boardCommentId")
    void reportBoardComment(@Param("boardCommentId") long boardCommentId);



}
