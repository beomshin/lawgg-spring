package com.kr.lg.db.repositories;

import com.kr.lg.common.enums.entity.level.CommentDepthLevel;
import com.kr.lg.common.enums.entity.status.CommentStatus;
import com.kr.lg.db.entities.BoardCommentTb;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardCommentRepository extends JpaRepository<BoardCommentTb, Long> {

    @EntityGraph(attributePaths = "boardTb")
    Optional<BoardCommentTb> findByBoardTb_BoardIdAndDepth(long boardId, CommentDepthLevel depthLevel);

    @EntityGraph(attributePaths = "boardTb")
    Optional<BoardCommentTb> findByBoardCommentId(long commentId);

    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET status = :status WHERE boardCommentId = :boardCommentId")
    void updateBoardCommentStatus(@Param("boardCommentId") long boardCommentId, @Param("status") CommentStatus status);

    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET content = :content WHERE boardCommentId = :boardCommentId")
    void updateBoardComment(@Param("boardCommentId") long boardCommentId, @Param("content") String content);

    @Modifying
    @Query(value = "UPDATE BoardCommentTb SET report = report + 1  WHERE boardCommentId = :boardCommentId")
    void reportBoardComment(@Param("boardCommentId") long boardCommentId);



}
