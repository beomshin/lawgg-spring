package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.LawFirmApplyTb;
import com.kr.lg.common.enums.entity.status.ApplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LawFirmApplyRepository extends JpaRepository<LawFirmApplyTb, Long> {

    int countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(Long lawFirmId, Long userId, ApplyStatus status);

    @Modifying
    @Query("UPDATE LawFirmApplyTb SET status = :status WHERE lawFirmTb.lawFirmId = :lawFirmId AND userTb.userId = :userId")
    void cancelApplyLawFirm(@Param("lawFirmId") long lawFirmId, @Param("userId") long userId, @Param("status") ApplyStatus status);

}
