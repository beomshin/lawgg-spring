package com.kr.lg.db.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AlertRepository extends RootAlertRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE AlertTb at SET at.readFlag = 1 WHERE at.alertId = :alertId")
    void readAlert(Long alertId);

    @Modifying
    @Query(value = "UPDATE AlertTb at SET at.readFlag = 1 WHERE at.alertId in (:alertId)")
    void readAlertAll(List<Long> alertId);
}
