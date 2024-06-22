package com.kr.lg.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AlertRepository extends RootAlertRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE AlertTb at SET at.readFlag = 1 WHERE at.alertId = :alertId")
    void readAlert(Long alertId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE AlertTb at SET at.readFlag = 1 WHERE at.alertId in (:alertId)")
    void readAlert(List<Long> alertId);
}
