package com.kr.lg.service.trial.comment.impl;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.dao.TrialCommentDao;
import com.kr.lg.mapper.TrialCommentTbMapper;
import com.kr.lg.model.web.net.response.trial.comment.*;
import com.kr.lg.model.web.querydsl.TrialQ;
import com.kr.lg.model.web.common.layer.TrialLayer;
import com.kr.lg.service.trial.comment.TrialCommentFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialCommentFindServiceImpl implements TrialCommentFindService {

    private final TrialCommentDao trialCommentDao;
    private final TrialCommentTbMapper trialCommentTbMapper;

    @Override
    public DefaultResponse findAnonymousAllCommentTrial(TrialLayer requestDto) {
        Long rootId = trialCommentDao.findRootComment(requestDto.getId()); // 루트 댓글 조회
        List<TrialQ> comments = trialCommentTbMapper.findAnonymousAllCommentTrial(rootId); // 댓글 리스트 조회
        return new FindACTResponse(rootId, comments);
    }

    @Override
    public DefaultResponse findUserAllCommentTrial(TrialLayer requestDto) {
        Long rootId = trialCommentDao.findRootComment(requestDto.getId()); // 루트 댓글 조회
        List<TrialQ> comments = trialCommentTbMapper.findUserAllCommentTrial(rootId, requestDto.getUserTb().getUserId()); // 댓글 리스트 조회
        return new FindUCTResponse(rootId, comments);
    }

    @Override
    public DefaultResponse findAnonymousParentCommentTrial(TrialLayer requestDto) {
        Page<TrialQ> comments = trialCommentDao.findAnonymousCommentTrial(requestDto, PageRequest.of(requestDto.getPage(), requestDto.getPageNum())); // 댓글 리스트 조회
        return new FindAPCTResponse(comments);
    }

    @Override
    public DefaultResponse findUserParentCommentTrial(TrialLayer requestDto) {
        Page<TrialQ> comments = trialCommentDao.findUserCommentTrial(requestDto, PageRequest.of(requestDto.getPage(), requestDto.getPageNum())); // 댓글 리스트 조회
        return new FindUPCTResponse(comments);
    }

    @Override
    public DefaultResponse findAnonymousChildrenCommentTrial(TrialLayer requestDto)  {
        Page<TrialQ> comments = trialCommentDao.findAnonymousCommentTrial(requestDto, PageRequest.of(requestDto.getPage(), requestDto.getPageNum())); // 댓글 리스트 조회
        return new FindACCTResponse(comments);
    }

    @Override
    public DefaultResponse findUserChildrenCommentTrial(TrialLayer requestDto) {
        Page<TrialQ> comments = trialCommentDao.findUserCommentTrial(requestDto, PageRequest.of(requestDto.getPage(), requestDto.getPageNum())); // 댓글 리스트 조회
        return new FindUCCTResponse(comments);
    }
}
