package com.kr.lg.db.repositories;

import com.kr.lg.common.enums.entity.element.StatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrialAttachRepository extends RootTrialAttachRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE TrialAttachTb SET status = :status  WHERE trialAttachId in (:deleteFiles)")
    int deleteFiles(@Param(value = "status") StatusEnum status, List<Long> deleteFiles);
}
