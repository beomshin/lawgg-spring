package com.kr.lg.db.repositories;

import com.kr.lg.enums.AcceptEnum;
import com.kr.lg.enums.ApplyStatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface LawFirmApplyRepository extends RootLawFirmApplyRepository {

    int countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(Long lawFirmId, Long userId, ApplyStatusEnum status);

    @Modifying
    @Transactional
    @Query("UPDATE LawFirmApplyTb SET status = :status WHERE lawFirmTb.lawFirmId = :lawFirmId AND userTb.userId = :userId")
    int updateApplyStatus(ApplyStatusEnum status, Long lawFirmId, Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE LawFirmApplyTb SET accept = :accept, status = :status, confirmDt = :confirmDt WHERE lawFirmAppyId = :lawFirmAppyId")
    int confirm(AcceptEnum accept, ApplyStatusEnum status, Timestamp confirmDt, Long lawFirmAppyId);

    @Modifying
    @Transactional
    @Query("UPDATE LawFirmApplyTb SET accept = :accept WHERE lawFirmAppyId = :lawFirmAppyId")
    int accept(AcceptEnum accept, Long lawFirmAppyId);

    @Modifying
    @Transactional
    @Query("UPDATE LawFirmApplyTb SET accept = :accept WHERE lawFirmAppyId = :lawFirmAppyId")
    int refuse(AcceptEnum accept, Long lawFirmAppyId);

}
