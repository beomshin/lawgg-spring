package com.kr.lg.db.repositories;

import com.kr.lg.enums.Status2Enum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LawFirmRepository extends RootLawFirmRepository {

    int countByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE LawFirmTb SET status = :status  WHERE lawFirmId = :lawFirmId")
    int updateStatus(Status2Enum status,  @Param("lawFirmId") Long lawFirmId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE LawFirmTb SET profile = :profile, background = :background  WHERE lawFirmId = :lawFirmId")
    int updateLawFirm(String profile, String background,  @Param("lawFirmId") Long lawFirmId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE LawFirmTb SET profile = :profile, background = :background, introduction = :introduction  WHERE lawFirmId = :lawFirmId")
    int updateLawFirm(String profile, String background, String introduction,  @Param("lawFirmId") Long lawFirmId);
}
