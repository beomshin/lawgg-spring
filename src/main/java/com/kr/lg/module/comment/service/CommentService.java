package com.kr.lg.module.comment.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.comment.model.req.DeleteTrialCommentRequest;
import com.kr.lg.module.comment.model.req.EnrollTrialCommentRequest;
import com.kr.lg.module.comment.model.req.DeletePositionCommentRequest;
import com.kr.lg.module.comment.model.req.EnrollPositionCommentRequest;
import com.kr.lg.module.comment.exception.CommentException;

public interface CommentService {

    void enrollBoardCommentNotWithLogin(EnrollPositionCommentRequest request, String ip) throws CommentException;
    void enrollBoardCommentWithLogin(EnrollPositionCommentRequest request, UserTb userTb, String ip) throws CommentException;
    void deleteBoardCommentNotWithLogin(DeletePositionCommentRequest request) throws CommentException;
    void deleteBoardCommentWithLogin(DeletePositionCommentRequest request, UserTb userTb) throws CommentException;
    void enrollTrialCommentWithLogin(EnrollTrialCommentRequest request, UserTb userTb, String ip) throws CommentException;
    void deleteTrialCommentWithLogin(DeleteTrialCommentRequest request, UserTb userTb) throws CommentException;

}
