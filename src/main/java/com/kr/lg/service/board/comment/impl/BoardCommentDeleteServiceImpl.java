package com.kr.lg.service.board.comment.impl;

import com.kr.lg.db.entities.BoardCommentTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.model.common.listener.BoardCEvent;
import com.kr.lg.model.common.listener.CommnetCNTEvent;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.common.utils.BoardUtils;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.service.board.comment.BoardCommentDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardCommentDeleteServiceImpl implements BoardCommentDeleteService {

    private final BoardCommentRepository boardCommentRepository;
    private final BoardUtils boardUtils;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public int deleteAnonymousCommentBoard(BoardLayer boardLayer) throws LgException {
        BoardCommentTb boardCommentTb = boardCommentRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD_COMMENT)); // 댓글 조회
        if (boardCommentTb.getStatus().equals(StatusEnum.DELETE_STATUS)) throw new LgException(GlobalCode.ALREADY_DELETE_BOARD_COMMENT);
        boardUtils.isRightCommentAnonymousPassword(boardLayer.getPassword(), boardCommentTb.getPassword()); // 비회원 비밀번호 확인
        applicationEventPublisher.publishEvent(new BoardCEvent(boardCommentTb.getBoardTb().getBoardId(), -1));
        return boardCommentRepository.updateBoardCommentStatus(boardLayer.getId(), StatusEnum.DELETE_STATUS); // 댓글 삭제
    }

    @Override
    public int deleteUserCommentBoard(BoardLayer boardLayer) throws LgException {
        BoardCommentTb boardCommentTb = boardCommentRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD_COMMENT)); // 댓글 조회
        if (boardCommentTb.getStatus().equals(StatusEnum.DELETE_STATUS)) throw new LgException(GlobalCode.ALREADY_DELETE_BOARD_COMMENT);
        boardUtils.isRightCommentUserNonePassword(boardLayer.getUserTb(), boardCommentTb.getUserTb());
        applicationEventPublisher.publishEvent(new BoardCEvent(boardCommentTb.getBoardTb().getBoardId(), -1));
        applicationEventPublisher.publishEvent(new CommnetCNTEvent(boardLayer.getUserTb(), -1)); // 댓글 개수 감소
        return boardCommentRepository.updateBoardCommentStatus(boardLayer.getId(), StatusEnum.DELETE_STATUS); // 댓글 삭제
    }


}
