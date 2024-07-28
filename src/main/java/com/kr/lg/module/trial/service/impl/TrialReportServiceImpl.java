package com.kr.lg.module.trial.service.impl;

import com.kr.lg.db.entities.ReportTb;
import com.kr.lg.db.repositories.ReportRepository;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.model.dto.TrialReportDto;
import com.kr.lg.module.trial.service.TrialReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialReportServiceImpl implements TrialReportService {

    private final TrialRepository trialRepository;
    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public synchronized void reportTrial(TrialReportDto reportDto) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 신고");
            ReportTb reportTb = ReportTb.builder()
                    .ip(reportDto.getIp())
                    .content(reportDto.getContent())
                    .trialTb(reportDto.getTrialTb())
                    .build();
            reportRepository.save(reportTb);
            trialRepository.reportTrial(reportDto.getTrialTb().getTrialId());
        } catch (Exception e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_REPORT_TRIAL);
        }
    }
}
