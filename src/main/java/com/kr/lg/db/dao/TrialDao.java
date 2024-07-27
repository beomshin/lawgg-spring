package com.kr.lg.db.dao;

import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.db.entities.TrialTb;
import org.springframework.transaction.annotation.Transactional;

public interface TrialDao {

    @Transactional
    TrialTb saveTrial(TrialLayer requestDto);


}
