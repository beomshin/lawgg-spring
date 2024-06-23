package com.kr.lg.service.trial.comment.impl;

import com.kr.lg.db.entities.TrialCommentTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.utils.TrialUtils;
import com.kr.lg.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.comment.TrialCommentUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialCommentUpdateServiceImpl implements TrialCommentUpdateService {

    private final TrialCommentRepository trialCommentRepository;
    private final TrialUtils trialUtils;

    @Override
    public int updateCommentTrial(TrialLayer requestDto) throws LgException {
        TrialCommentTb trialCommentTb = trialCommentRepository.findById(requestDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_USER)); // 게시판 조회
        trialUtils.isWriterUser(trialCommentTb.getUserTb(), requestDto.getUserTb());
        return trialCommentRepository.updateTrialComment(requestDto.getId(), requestDto.getContent()); // 댓글 업데이트
    }

}
