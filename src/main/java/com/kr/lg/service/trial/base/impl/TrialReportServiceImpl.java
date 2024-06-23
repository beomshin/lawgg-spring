package com.kr.lg.service.trial.base.impl;

import com.kr.lg.db.entities.ReportTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.repositories.ReportRepository;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.base.TrialReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialReportServiceImpl implements TrialReportService {

    private final TrialRepository trialRepository;
    private final ReportRepository reportRepository;

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public int reportTrial(TrialLayer requestDto)
    {
        if (active.equals("local")) {
            reportRepository.save(ReportTb.builder()
                    .ip(requestDto.getIp())
                    .content(requestDto.getContent())
                    .trialTb(TrialTb.builder().trialId(requestDto.getId()).build())
                    .build());
            return 1;
        }
        reportRepository.save(ReportTb.builder()
                .ip(requestDto.getIp())
                .content(requestDto.getContent())
                .trialTb(TrialTb.builder().trialId(requestDto.getId()).build())
                .build());
        return trialRepository.reportTrial(requestDto.getId()); // 신고
    }
}
