package com.kr.lg.service.trial.comment.impl;

import com.kr.lg.entities.TrialCommentTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.StatusEnum;
import com.kr.lg.web.common.listener.CommnetCNTEvent;
import com.kr.lg.web.common.listener.TrialCEvent;
import com.kr.lg.repositories.TrialCommentRepository;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.utils.TrialUtils;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.comment.TrialCommentDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialCommentDeleteServiceImpl implements TrialCommentDeleteService {

    private final TrialCommentRepository trialCommentRepository;
    private final TrialUtils trialUtils;
    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void deleteCommentTrial(TrialLayer requestDto) throws LgException {
        TrialCommentTb trialCommentTb = trialCommentRepository.findById(requestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_USER)); // 게시판 조회
        if (trialCommentTb.getStatus().equals(StatusEnum.DELETE_STATUS)) throw new LgException(GlobalCode.ALREADY_DELETE_TRIAL_COMMENT);
        trialUtils.isWriterUser(trialCommentTb.getUserTb(), requestDto.getUserTb());
        applicationEventPublisher.publishEvent(new TrialCEvent(trialCommentTb.getTrialTb().getTrialId(), -1)); // 트라이얼 답글 개수 감수
        applicationEventPublisher.publishEvent(new CommnetCNTEvent(requestDto.getUserTb(), -1)); // 댓글 개수 감소
        trialCommentRepository.updateStatus(trialCommentTb.getTrialCommentId(), StatusEnum.DELETE_STATUS);
    }

}
