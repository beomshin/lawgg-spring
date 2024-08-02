package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TrialVoteTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.PrecedentEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrialVoteRepository extends JpaRepository<TrialVoteTb, Long> {

    Optional<TrialVoteTb> findByTrialTb_TrialIdAndUserTb_UserId(long trialId, long userId);
    @Modifying
    @Query(value = "UPDATE TrialVoteTb SET precedent = :precedent WHERE trialVoteId = :trialVoteId")
    void updatePrecedent(@Param("trialVoteId") Long trialVoteId, PrecedentEnum precedent);
}
