package com.kr.lg.db.repositories;

import com.kr.lg.common.enums.entity.status.CommentStatus;
import com.kr.lg.db.entities.TrialCommentTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrialCommentRepository extends JpaRepository<TrialCommentTb, Long> {
    @Modifying
    @Query(value = "UPDATE TrialCommentTb SET status = :status  WHERE trialCommentId = :trialCommentId")
    void updateTrialCommentStatus(@Param("trialCommentId") long trialCommentId, CommentStatus status);
}
