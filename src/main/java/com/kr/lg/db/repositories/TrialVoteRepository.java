package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.TrialVoteTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.entity.element.PrecedentEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrialVoteRepository extends RootTrialVoteRepository {

    Optional<TrialVoteTb> findByTrialTbAndUserTb(TrialTb trialTb, UserTb userTb);

    Optional<TrialVoteTb> findByTrialTb_TrialIdAndUserTb(Long trialId, UserTb userTb);

    @Modifying
    @Query(value = "UPDATE TrialVoteTb SET precedent = :precedent WHERE trialVoteId = :trialVoteId")
    int updatePrecedent(@Param("trialVoteId") Long trialVoteId, PrecedentEnum precedent);
}
