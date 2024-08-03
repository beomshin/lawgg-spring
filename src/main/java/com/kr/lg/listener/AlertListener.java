package com.kr.lg.listener;

import com.kr.lg.db.entities.*;
import com.kr.lg.common.enums.entity.type.AlertType;
import com.kr.lg.common.enums.entity.flag.ReadFlag;
import com.kr.lg.db.repositories.*;
import com.kr.lg.module.comment.model.event.BoardCommentCreateAlertToWriterEvent;
import com.kr.lg.module.comment.model.event.BoardCommentCreateAlertToBoardWriterEvent;
import com.kr.lg.module.comment.model.event.TrialCommentCreateAlertToWriterEvent;
import com.kr.lg.module.comment.model.event.TrialCommentCreateAlertToTrialWriterEvent;
import com.kr.lg.module.trial.model.event.AlertTLEvent;
import com.kr.lg.module.trial.model.event.AlertVideoEvent;
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
    public void enrollBoardAlert(BoardCommentCreateAlertToBoardWriterEvent BoardCommentCreateAlertToBoardWriterEvent) {
        Optional<BoardTb> boardTb = boardRepository.findById(BoardCommentCreateAlertToBoardWriterEvent.getBoardId());
        if (boardTb.isPresent()) {
            UserTb writer = boardTb.get().getUserTb();
            if (writer != null && BoardCommentCreateAlertToBoardWriterEvent.isPost(writer)) {
                alertRepository.save(
                        AlertTb.builder()
                                .boardTb(boardTb.get())
                                .userTb(writer)
                                .title(BoardCommentCreateAlertToBoardWriterEvent.getTitle())
                                .content(BoardCommentCreateAlertToBoardWriterEvent.getContent())
                                .type(AlertType.BOARD_ALERT_TYPE)
                                .readFlag(ReadFlag.NON_READ_FLAG)
                                .build()
                );
            }
        }
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollBoardCommentAlert(BoardCommentCreateAlertToWriterEvent BoardCommentCreateAlertToWriterEvent) {
        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findById(BoardCommentCreateAlertToWriterEvent.getParentId());
        if (boardCommentTb.isPresent() && boardCommentTb.get().getUserTb() != null) {
            UserTb writer = boardCommentTb.get().getUserTb();
            if (BoardCommentCreateAlertToWriterEvent.isPost(writer)){
                alertRepository.save(
                        AlertTb.builder()
                                .boardTb(boardCommentTb.get().getBoardTb())
                                .userTb(writer)
                                .title(BoardCommentCreateAlertToWriterEvent.getTitle())
                                .content(BoardCommentCreateAlertToWriterEvent.getContent())
                                .type(AlertType.BOARD_ALERT_TYPE)
                                .readFlag(ReadFlag.NON_READ_FLAG)
                                .build()
                );
            }
        }
    }


    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollTrialAlert(TrialCommentCreateAlertToTrialWriterEvent TrialCommentCreateAlertToTrialWriterEvent) {
        Optional<TrialTb> trialTb = trialRepository.findById(TrialCommentCreateAlertToTrialWriterEvent.getTrialId());
        if (trialTb.isPresent()) {
            UserTb writer = trialTb.get().getUserTb();
            if (TrialCommentCreateAlertToTrialWriterEvent.isPost(writer)) {
                alertRepository.save(
                        AlertTb.builder()
                                .trialTb(trialTb.get())
                                .userTb(writer)
                                .title(TrialCommentCreateAlertToTrialWriterEvent.getTitle())
                                .content(TrialCommentCreateAlertToTrialWriterEvent.getContent())
                                .type(AlertType.TRIAL_ALERT_TYPE)
                                .readFlag(ReadFlag.NON_READ_FLAG)
                                .build()
                );
            }
        }
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void enrollTrialCommentAlert(TrialCommentCreateAlertToWriterEvent TrialCommentCreateAlertToWriterEvent) {
        Optional<TrialCommentTb> trialCommentTb = trialCommentRepository.findById(TrialCommentCreateAlertToWriterEvent.getParentId());
        if (trialCommentTb.isPresent() && trialCommentTb.get().getUserTb() != null) {
            UserTb writer = trialCommentTb.get().getUserTb();
            if (TrialCommentCreateAlertToWriterEvent.isPost(writer)){
                alertRepository.save(
                        AlertTb.builder()
                                .trialTb(trialCommentTb.get().getTrialTb())
                                .userTb(writer)
                                .title(TrialCommentCreateAlertToWriterEvent.getTitle())
                                .content(TrialCommentCreateAlertToWriterEvent.getContent())
                                .type(AlertType.TRIAL_ALERT_TYPE)
                                .readFlag(ReadFlag.NON_READ_FLAG)
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
                        .type(AlertType.TRIAL_ALERT_TYPE)
                        .readFlag(ReadFlag.NON_READ_FLAG)
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
                        .type(AlertType.TRIAL_ALERT_TYPE)
                        .readFlag(ReadFlag.NON_READ_FLAG)
                        .build()
        );
    }
}
