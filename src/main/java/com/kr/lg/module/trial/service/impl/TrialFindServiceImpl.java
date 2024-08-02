package com.kr.lg.module.trial.service.impl;

import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.mapper.TrialMapper;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import com.kr.lg.module.trial.model.entry.TrialVoteEntry;
import com.kr.lg.model.mapper.MapperParam;
import com.kr.lg.model.mapper.TrialParam;
import com.kr.lg.module.trial.service.TrialFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialFindServiceImpl implements TrialFindService {

    private final TrialMapper trialMapper;

    @Override
    public Page<TrialEntry> findTrials(TrialParam<?> param) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 게시판 조회");
            List<TrialEntry> content = trialMapper.findTrials(param); // trials 조회
            long count = trialMapper.findTrialsCnt(param.getData()); // trials 개수 조회
            return new PageImpl<>(content, param.getPageable(), count); // pageable 생성
        } catch (RuntimeException e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_FIND_TRIAL);
        }
    }

    @Override
    public Optional<TrialEntry> findTrial(MapperParam param) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 게시판 상세 조회");
            return Optional.ofNullable(trialMapper.findTrial(param));
        } catch (RuntimeException e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_FIND_TRIAL);
        }
    }

    @Override
    public TrialVoteEntry findVotePercent(MapperParam param) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 게시판 투표 정보 조회");
            return trialMapper.findVotePercent(param);
        } catch (RuntimeException e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_FIND_TRAIL_VOTE);
        }
    }

}
