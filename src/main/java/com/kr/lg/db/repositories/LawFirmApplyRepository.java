package com.kr.lg.db.repositories;

import com.kr.lg.enums.ApplyStatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface LawFirmApplyRepository extends RootLawFirmApplyRepository {

    int countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(Long lawFirmId, Long userId, ApplyStatusEnum status);

    @Modifying
    @Query("UPDATE LawFirmApplyTb SET status = :status WHERE lawFirmTb.lawFirmId = :lawFirmId AND userTb.userId = :userId")
    void cancelApplyLawFirm(@Param("lawFirmId") long lawFirmId, @Param("userId") long userId, @Param("status") ApplyStatusEnum status);

}
