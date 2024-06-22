package com.kr.lg.service.trial.comment.impl;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.repositories.TrialCommentRepository;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.comment.TrialCommentReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialCommentReportServiceImpl implements TrialCommentReportService {

    private final TrialCommentRepository trialCommentRepository;

    @Value("${spring.profiles.active}")
    private String active;


    @Override
    public int reportCommentTrial(TrialLayer requestDto) throws LgException {
        return trialCommentRepository.reportTrialComment(requestDto.getId()); // 신고
    }
}
