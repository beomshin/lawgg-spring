package com.kr.lg.module.comment.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.comment.model.req.DeleteCommentTrialRequest;
import com.kr.lg.module.comment.model.req.EnrollCommentTrialRequest;
import com.kr.lg.module.comment.model.req.DeleteBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.ReportBoardCommentRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.model.req.EnrollPositionCommentRequest;
import com.kr.lg.module.comment.exception.CommentException;

public interface CommentService {

    void enrollBoardCommentNotWithLogin(EnrollPositionCommentRequest request, String ip) throws CommentException;
    void enrollBoardCommentWithLogin(EnrollPositionCommentRequest request, UserTb userTb, String ip) throws CommentException;
    void updateBoardCommentNotWithLogin(UpdateBoardCommentNotWithLoginRequest request) throws CommentException;
    void updateBoardCommentWithLogin(UpdateBoardCommentWithLoginRequest request, UserTb userTb) throws CommentException;
    void reportBoardComment(ReportBoardCommentRequest request) throws CommentException;
    void deleteBoardCommentNotWithLogin(DeleteBoardCommentNotWithLoginRequest request) throws CommentException;
    void deleteBoardCommentWithLogin(DeleteBoardCommentNotWithLoginRequest request, UserTb userTb) throws CommentException;
    void enrollTrialCommentWithLogin(EnrollCommentTrialRequest request, UserTb userTb, String ip) throws CommentException;
    void deleteTrialCommentWithLogin(DeleteCommentTrialRequest request, UserTb userTb) throws CommentException;

}
