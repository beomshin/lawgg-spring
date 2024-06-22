package com.kr.lg.repositories;

import com.kr.lg.enums.entity.element.StatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardAttachRepository extends RootBoardAttachRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE BoardAttachTb SET status = :status  WHERE boardAttachId in (:deleteFiles)")
    int deleteFiles(@Param(value = "status") StatusEnum status, List<Long> deleteFiles);
}
