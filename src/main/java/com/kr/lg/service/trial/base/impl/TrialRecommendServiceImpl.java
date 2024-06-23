package com.kr.lg.service.trial.base.impl;

import com.kr.lg.db.entities.TrialRecommendTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.listener.TrialREvent;
import com.kr.lg.db.repositories.TrialRecommendRepository;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.service.trial.base.TrialRecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialRecommendServiceImpl implements TrialRecommendService {

    private final TrialRecommendRepository trialRecommendRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void recommendTrial(TrialLayer requestDto) throws LgException {
        long count = trialRecommendRepository.countByTrialTb_TrialIdAndUserTb(requestDto.getId(), requestDto.getUserTb());
        if (count > 0) throw new LgException(GlobalCode.ALREADY_RECOMMEND_TRIAL); // 이미 추천 처리
        applicationEventPublisher.publishEvent(new TrialREvent(requestDto.getId(), 1));
        trialRecommendRepository.save(TrialRecommendTb.builder()
                .trialTb(TrialTb.builder().trialId(requestDto.getId()).build())
                .userTb(requestDto.getUserTb())
                .build());
    }

    @Override
    public int deleteRecommendTrial(TrialLayer requestDto) throws LgException {
        long count = trialRecommendRepository.countByTrialTb_TrialIdAndUserTb(requestDto.getId(), requestDto.getUserTb());
        if (count == 0) throw new LgException(GlobalCode.ALREADY_DELETE_TRIAL); // 이미 추천 처리
        return trialRecommendRepository.deleteRecommendTrial(TrialTb.builder().trialId(requestDto.getId()).build(), requestDto.getUserTb());
    }
}
