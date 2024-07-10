package com.kr.lg.service.trial.base.impl;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.common.enums.StatusEnum;
import com.kr.lg.model.common.listener.TrialCNTEvent;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.common.utils.TrialUtils;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.service.trial.base.TrialDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialDeleteServiceImpl implements TrialDeleteService {

    private final TrialRepository trialRepository;
    private final TrialUtils trialUtils;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void deleteUserTrial(TrialLayer requestDto) throws LgException {
        TrialTb trialTb = trialRepository.findByTrialIdAndUserTb(requestDto.getId(), requestDto.getUserTb());
        trialUtils.isRightUserPassword(requestDto.getUserTb(), trialTb.getUserTb(), requestDto.getPassword());
        trialRepository.updateStatus(trialTb.getTrialId(), StatusEnum.DELETE_STATUS);
        applicationEventPublisher.publishEvent(new TrialCNTEvent(requestDto.getUserTb(), -1));
    }
}
