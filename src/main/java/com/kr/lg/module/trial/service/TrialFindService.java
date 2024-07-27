package com.kr.lg.module.trial.service;

import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import com.kr.lg.module.trial.model.entry.TrialVoteEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.TrialParam;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TrialFindService {

    Page<TrialEntry> findTrials(TrialParam<?> param) throws TrialException;
    Optional<TrialEntry> findTrial(MapperParam param) throws TrialException;
    TrialVoteEntry findVotePercent(MapperParam param) throws TrialException;
}
