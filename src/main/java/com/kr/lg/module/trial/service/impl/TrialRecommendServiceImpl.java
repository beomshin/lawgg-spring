package com.kr.lg.module.trial.service.impl;

import com.kr.lg.db.entities.TrialRecommendTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.TrialRecommendRepository;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.service.TrialRecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialRecommendServiceImpl implements TrialRecommendService {

    private final TrialRecommendRepository trialRecommendRepository;

    @Override
    @Transactional
    public void recommendTrial(TrialTb trialTb, UserTb userTb) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 추천");
            TrialRecommendTb boardRecommendTb = TrialRecommendTb.builder()
                    .trialTb(trialTb)
                    .userTb(userTb)
                    .build();
            trialRecommendRepository.save(boardRecommendTb); // 추천
        } catch (Exception e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_RECOMMEND_TRIAL);
        }
    }

}
