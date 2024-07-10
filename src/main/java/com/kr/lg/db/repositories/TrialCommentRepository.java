package com.kr.lg.db.repositories;

import com.kr.lg.enums.StatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TrialCommentRepository extends RootTrialCommentRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialCommentTb SET report = report + 1  WHERE trialCommentId = :trialCommentId")
    int reportTrialComment(@Param("trialCommentId") Long trialCommentId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialCommentTb SET content = :content WHERE trialCommentId = :trialCommentId")
    int updateTrialComment(@Param("trialCommentId") Long trialCommentId, @Param("content") String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialCommentTb SET status = :status  WHERE trialCommentId = :trialCommentId")
    int updateStatus(@Param("trialCommentId") Long trialCommentId, StatusEnum status);
}
