package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.TrialAttachTb;
import com.kr.lg.enums.StatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrialAttachRepository extends RootTrialAttachRepository {

    List<TrialAttachTb> findByTrialTb_TrialId(long trialId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialAttachTb SET status = :status  WHERE trialAttachId in (:deleteFiles)")
    int deleteFiles(@Param(value = "status") StatusEnum status, List<Long> deleteFiles);
}
