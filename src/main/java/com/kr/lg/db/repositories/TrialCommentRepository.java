package com.kr.lg.db.repositories;

import com.kr.lg.enums.StatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrialCommentRepository extends RootTrialCommentRepository {

    @Modifying
    @Query(value = "UPDATE TrialCommentTb SET status = :status  WHERE trialCommentId = :trialCommentId")
    void updateTrialCommentStatus(@Param("trialCommentId") long trialCommentId, StatusEnum status);
}
