package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.MailTb;
import com.kr.lg.enums.VerificationEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface MailRepository extends RootMailRepository {
    Optional<MailTb> findByCodeAndTxIdAndExpiredAfter(String code, String txId, Date now);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MailTb SET verification = :verification WHERE mailId = :mailId")
    int finishVerification(@Param("mailId") Long mailId, @Param("verification") VerificationEnum verification);
}
