package com.kr.lg.service.trial.base.impl;

import com.kr.lg.entities.TrialTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.repositories.TrialRepository;
import com.kr.lg.utils.TrialUtils;
import com.kr.lg.model.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.base.TrialAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialAuthServiceImpl implements TrialAuthService {

    private final TrialRepository trialRepository;
    private final TrialUtils trialUtils;

    @Override
    public void loginUserTrial(TrialLayer requestDto) throws LgException {
        TrialTb trialTb = trialRepository.findByTrialIdAndUserTb(requestDto.getId(), requestDto.getUserTb());
        trialUtils.isWriterUser(trialTb.getUserTb(), requestDto.getUserTb());
    }
}
