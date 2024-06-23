package com.kr.lg.service.trial.comment.impl;

import com.kr.lg.db.entities.TrialCommentTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.web.common.listener.AlertTCEvent;
import com.kr.lg.web.common.listener.AlertTEvent;
import com.kr.lg.web.common.listener.CommnetCNTEvent;
import com.kr.lg.web.common.listener.TrialCEvent;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.comment.TrialCommentEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialCommentEnrollServiceImpl implements TrialCommentEnrollService {

    private final TrialCommentRepository trialCommentRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void enrollCommentTrial(TrialLayer requestDto) {
        trialCommentRepository.save(TrialCommentTb.builder()
                .userTb(requestDto.getUserTb())
                .trialTb(TrialTb.builder().trialId(requestDto.getId()).build())
                .parentId(requestDto.getParentId())
                .order(requestDto.getOrder())
                .depth(requestDto.getDepth())
                .id(requestDto.getLoginId())
                .password(requestDto.getPassword())
                .writer(requestDto.getWriter())
                .content(requestDto.getContent())
                .emoticon(requestDto.getEmoticon())
                .build());
        applicationEventPublisher.publishEvent(new TrialCEvent(requestDto.getId(), 1)); // 트라이얼 답글 개수 증가
        applicationEventPublisher.publishEvent(new CommnetCNTEvent(requestDto.getUserTb(), 1)); // 댓글 개수 증가
        switch (requestDto.getDepth()) {
            case PARENT_COMMENT: applicationEventPublisher.publishEvent(new AlertTEvent(requestDto)); break;
            case CHILDREN_COMMENT: applicationEventPublisher.publishEvent(new AlertTCEvent(requestDto)); break;
        }
    }
}
