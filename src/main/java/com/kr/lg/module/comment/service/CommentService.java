package com.kr.lg.module.comment.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.comment.model.req.DeleteCommentTrialRequest;
import com.kr.lg.module.comment.model.req.EnrollCommentTrialRequest;
import com.kr.lg.module.board.model.req.DeleteBoardCommentNotWithLoginRequest;
import com.kr.lg.module.board.model.req.DeleteBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.model.req.ReportBoardCommentRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentException;

public interface CommentService {

    void enrollBoardCommentNotWithLogin(EnrollBoardCommentNotWithLoginRequest request, String ip) throws CommentException;
    void enrollBoardCommentWithLogin(EnrollBoardCommentWithLoginRequest request, UserTb userTb, String ip) throws CommentException;
    void updateBoardCommentNotWithLogin(UpdateBoardCommentNotWithLoginRequest request) throws CommentException;
    void updateBoardCommentWithLogin(UpdateBoardCommentWithLoginRequest request, UserTb userTb) throws CommentException;
    void reportBoardComment(ReportBoardCommentRequest request) throws CommentException;
    void deleteBoardCommentNotWithLogin(DeleteBoardCommentNotWithLoginRequest request) throws CommentException;
    void deleteBoardCommentWithLogin(DeleteBoardCommentWithLoginRequest request, UserTb userTb) throws CommentException;
    void enrollTrialCommentWithLogin(EnrollCommentTrialRequest request, UserTb userTb, String ip) throws CommentException;
    void deleteTrialCommentWithLogin(DeleteCommentTrialRequest request, UserTb userTb) throws CommentException;

}
