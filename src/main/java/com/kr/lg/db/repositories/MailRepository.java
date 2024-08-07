package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.MailTb;
import com.kr.lg.common.enums.entity.status.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface MailRepository extends JpaRepository<MailTb, Long> {

    // 인증 메일 존재 여부 조회
    Optional<MailTb> findByCodeAndTxIdAndExpiredAfterAndVerification(String code, String txId, Date now, VerificationStatus verification);

    // 메일 인증 완료 업데이트
    @Modifying
    @Query(value = "UPDATE MailTb SET verification = :verification WHERE mailId = :mailId")
    void updateCompleteVerifyEmail(@Param("mailId") Long mailId, @Param("verification") VerificationStatus verification);
}
