package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TrialRecommendTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrialRecommendRepository extends JpaRepository<TrialRecommendTb, Long> {
    Optional<TrialRecommendTb> findByTrialTb_TrialIdAndUserTb_UserId(@Param("trialId") long trialId, @Param("userId") long userId);
    @Modifying
    @Query(value = "DELETE FROM TrialRecommendTb where trialTb.trialId = :trialId and userTb.userId = :userId")
    void deleteRecommendTrial(@Param("trialId") long trialId, @Param("userId") long userId);
}
