package com.kr.lg.module.trial.service.impl;

import com.kr.lg.enums.EndingEnum;
import com.kr.lg.enums.LiveEnum;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.model.dto.TrialUpdateDto;
import com.kr.lg.module.trial.service.TrialUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialUpdateServiceImpl implements TrialUpdateService {

    private final TrialRepository trialRepository;

    @Override
    public void updateLiveStartTrial(TrialUpdateDto updateDto) throws TrialException {
        try {
            trialRepository.updateLive(updateDto.getTrialId(), updateDto.getUserTb().getLawFirmId(), updateDto.getUserTb(), updateDto.getUrl(), LiveEnum.LIVE_TYPE, new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_UPDATE_LIVE_START_TRIAL);
        }
    }

    @Override
    public void updateEndTrial(TrialUpdateDto updateDto) throws TrialException {
       try {
           trialRepository.updateEnd(updateDto.getTrialId(), updateDto.getPrecedent(), EndingEnum.ENDING_TYPE, new Timestamp(System.currentTimeMillis()));
       } catch (Exception e) {
           log.error("", e);
           throw new TrialException(TrialResultCode.FAIL_UPDATE_LIVE_END_TRIAL);
       }
    }


}
