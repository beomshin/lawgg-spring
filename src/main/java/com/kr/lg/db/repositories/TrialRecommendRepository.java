package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TrialRecommendRepository extends RootTrialRecommendRepository {

    long countByTrialTb_TrialIdAndUserTb(Long trialId, UserTb userTb);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM TrialRecommendTb where trialTb = :trialTb and userTb = :userTb")
    int deleteRecommendTrial(@Param("trialTb") TrialTb trialTb, @Param("userTb") UserTb userTb);
}
