package com.kr.lg.service.board.comment.impl;

import com.kr.lg.db.entities.BoardCommentTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.common.utils.BoardUtils;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.service.board.comment.BoardCommentUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardCommentUpdateServiceImpl implements BoardCommentUpdateService {

    private final BoardCommentRepository boardCommentRepository;;
    private final BoardUtils boardUtils;

    @Override
    public int updateAnonymousCommentBoard(BoardLayer boardLayer) throws LgException {
        BoardCommentTb boardCommentTb = boardCommentRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD_COMMENT)); // 댓글 조회
        boardUtils.isRightCommentAnonymousPassword(boardLayer.getPassword(), boardCommentTb.getPassword());
        return boardCommentRepository.updateBoardComment(boardLayer.getId(), boardLayer.getContent()); // 댓글 업데이트
    }

    @Override
    public int updateUserCommentBoard(BoardLayer boardLayer) throws LgException {
        BoardCommentTb boardCommentTb = boardCommentRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD_COMMENT)); // 댓글 조회
        boardUtils.isRightCommentUserPassword(boardLayer.getUserTb(), boardCommentTb.getUserTb(), boardLayer.getPassword());
        return boardCommentRepository.updateBoardComment(boardLayer.getId(), boardLayer.getContent()); // 댓글 업데이트
    }
}
