package com.kr.lg.module.comment.service;

import com.kr.lg.module.comment.exception.CommentException;

public interface CommentDeleteService {

    void deleteBoardComment(long boardCommentId) throws CommentException;
    void deleteTrialComment(long trialCommentId) throws CommentException;
}
