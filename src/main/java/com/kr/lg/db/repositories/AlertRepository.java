package com.kr.lg.db.repositories;

import com.kr.lg.db.entities.AlertTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<AlertTb, Long> {

    Optional<AlertTb> findByAlertIdAndUserTb_UserId(@Param("alertId") long alertId, @Param("userId") long userId);

    @Modifying
    @Query(value = "UPDATE AlertTb at SET at.readFlag = 1 WHERE at.alertId = :alertId")
    void readAlert(Long alertId);

    @Modifying
    @Query(value = "UPDATE AlertTb at SET at.readFlag = 1 WHERE at.alertId in (:alertId)")
    void readAlertAll(List<Long> alertId);
}
