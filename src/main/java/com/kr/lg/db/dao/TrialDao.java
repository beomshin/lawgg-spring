package com.kr.lg.db.dao;

import com.kr.lg.web.querydsl.TrialQ;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.exception.LgException;
import com.kr.lg.db.entities.TrialTb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface TrialDao {

    Page<TrialQ> findAllListTrial(TrialLayer requestDto, Pageable pageable);
    Page<TrialQ> findUserListTrial(TrialLayer requestDto, Pageable pageable);
    Page<TrialQ> findLawFirmListTrial(TrialLayer trialLayer, Pageable pageable);
    TrialQ findDetailTrial(TrialLayer requestDto) throws LgException;
    @Transactional
    TrialTb saveTrial(TrialLayer requestDto);


}
