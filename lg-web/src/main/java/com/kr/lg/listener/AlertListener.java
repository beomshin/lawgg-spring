package com.kr.lg.listener;

import com.kr.lg.entities.*;
import com.kr.lg.enums.entity.element.AlertEnum;
import com.kr.lg.enums.entity.element.ReadEnum;
import com.kr.lg.model.web.common.listener.*;
import com.kr.lg.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AlertListener {

    private final BoardRepository boardRepository;
    private final TrialRepository trialRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final TrialCommentRepository trialCommentRepository;
    private final AlertRepository alertRepository;


    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollBoardAlert(AlertBEvent AlertBEvent) {
        Optional<BoardTb> boardTb = boardRepository.findById(AlertBEvent.getBoardId());
        if (boardTb.isPresent()) {
            UserTb writer = boardTb.get().getUserTb();
            if (AlertBEvent.isPost(writer)) {
                alertRepository.save(
                        AlertTb.builder()
                                .boardTb(boardTb.get())
                                .userTb(writer)
                                .title(AlertBEvent.getTitle())
                                .content(AlertBEvent.getContent())
                                .type(AlertEnum.BOARD_ALERT_TYPE)
                                .readFlag(ReadEnum.NON_READ_FLAG)
                                .build()
                );
            }
        }
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollBoardCommentAlert(AlertBCEvent AlertBCEvent) {
        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findById(AlertBCEvent.getParentId());
        if (boardCommentTb.isPresent() && boardCommentTb.get().getUserTb() != null) {
            UserTb writer = boardCommentTb.get().getUserTb();
            if (AlertBCEvent.isPost(writer)){
                alertRepository.save(
                        AlertTb.builder()
                                .boardTb(boardCommentTb.get().getBoardTb())
                                .userTb(writer)
                                .title(AlertBCEvent.getTitle())
                                .content(AlertBCEvent.getContent())
                                .type(AlertEnum.BOARD_ALERT_TYPE)
                                .readFlag(ReadEnum.NON_READ_FLAG)
                                .build()
                );
            }
        }
    }


    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollTrialAlert(AlertTEvent AlertTEvent) {
        Optional<TrialTb> trialTb = trialRepository.findById(AlertTEvent.getTrialId());
        if (trialTb.isPresent()) {
            UserTb writer = trialTb.get().getUserTb();
            if (AlertTEvent.isPost(writer)) {
                alertRepository.save(
                        AlertTb.builder()
                                .trialTb(trialTb.get())
                                .userTb(writer)
                                .title(AlertTEvent.getTitle())
                                .content(AlertTEvent.getContent())
                                .type(AlertEnum.TRIAL_ALERT_TYPE)
                                .readFlag(ReadEnum.NON_READ_FLAG)
                                .build()
                );
            }
        }
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollTrialCommentAlert(AlertTCEvent AlertTCEvent) {
        Optional<TrialCommentTb> trialCommentTb = trialCommentRepository.findById(AlertTCEvent.getParentId());
        if (trialCommentTb.isPresent() && trialCommentTb.get().getUserTb() != null) {
            UserTb writer = trialCommentTb.get().getUserTb();
            if (AlertTCEvent.isPost(writer)){
                alertRepository.save(
                        AlertTb.builder()
                                .trialTb(trialCommentTb.get().getTrialTb())
                                .userTb(writer)
                                .title(AlertTCEvent.getTitle())
                                .content(AlertTCEvent.getContent())
                                .type(AlertEnum.TRIAL_ALERT_TYPE)
                                .readFlag(ReadEnum.NON_READ_FLAG)
                                .build()
                );
            }
        }
    }


    @TransactionalEventListener
    @Async
    @Transactional
    public void enorllAlert(AlertTLEvent AlertTLEvent) {
        alertRepository.save(
                AlertTb.builder()
                        .trialTb(AlertTLEvent.getTrialTb())
                        .userTb(AlertTLEvent.getTrialTb().getUserTb())
                        .title("나의 트라이얼 재판이 시작되었습니다")
                        .content(new StringBuffer().append("제목 : ").append(AlertTLEvent.getTrialTb().getTitle()).toString())
                        .type(AlertEnum.TRIAL_ALERT_TYPE)
                        .readFlag(ReadEnum.NON_READ_FLAG)
                        .build()
        );
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollTrialVideo(AlertVideoEvent alertVideoEvent) {
        alertRepository.save(
                AlertTb.builder()
                        .trialTb(alertVideoEvent.getTrialTb())
                        .userTb(alertVideoEvent.getTrialTb().getUserTb())
                        .title("등록하신 트라이얼 영상이 업로드 완료되었습니다.")
                        .content(new StringBuffer().append("제목 : ").append(alertVideoEvent.getTrialTb().getTitle()).toString())
                        .type(AlertEnum.TRIAL_ALERT_TYPE)
                        .readFlag(ReadEnum.NON_READ_FLAG)
                        .build()
        );
    }
}
