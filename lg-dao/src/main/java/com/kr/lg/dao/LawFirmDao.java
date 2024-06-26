package com.kr.lg.dao;

import com.kr.lg.model.web.querydsl.LawFirmQ;
import com.kr.lg.model.web.common.layer.LawFLayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LawFirmDao {

    Page<LawFirmQ> findAllLawFirmList(LawFLayer requestDto, Pageable pageable);
    Optional<LawFirmQ> findLawFirmDetail(LawFLayer requestDto);
    Long findApply(LawFLayer requestDto);
    Optional<LawFirmQ> findMyLawFirm(Long id);
    Page<LawFirmQ> findApplyListMyLawFirm(LawFLayer requestDto, Pageable pageable);
    Page<LawFirmQ> findUserListMyLawFirm(LawFLayer requestDto, Pageable pageable);


}
