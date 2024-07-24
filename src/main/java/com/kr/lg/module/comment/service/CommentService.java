package com.kr.lg.module.comment.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentException;

public interface CommentService {

    void enrollBoardCommentNotWithLogin(EnrollBoardCommentNotWithLoginRequest request, String ip) throws CommentException;

    void enrollBoardCommentWithLogin(EnrollBoardCommentWithLoginRequest request, UserTb userTb, String ip) throws CommentException;
}
