package com.kr.lg.module.trial.service.impl;

import com.kr.lg.common.enums.entity.status.TrialStatus;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.module.trial.service.TrialDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialDeleteServiceImpl implements TrialDeleteService {

    private final TrialRepository trialRepository;

    @Override
    @Transactional
    public void deleteTrial(long trialId) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 삭제");
            trialRepository.updateStatus(trialId, TrialStatus.DELETE_STATUS);
        } catch (Exception e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_DELETE_TRIAL);
        }
    }
}
