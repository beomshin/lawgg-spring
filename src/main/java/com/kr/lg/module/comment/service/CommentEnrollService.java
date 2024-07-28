package com.kr.lg.module.comment.service;

import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.model.dto.CommentEnrollDto;

public interface CommentEnrollService {

    void enrollBoardComment(CommentEnrollDto commentEnrollDto) throws CommentException;
    void enrollTrialComment(CommentEnrollDto commentEnrollDto) throws CommentException;
}
