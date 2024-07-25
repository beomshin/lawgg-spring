package com.kr.lg.module.comment.service;

import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.model.dto.CommentUpdateDto;

public interface CommentUpdateService {

    void updateBoardComment(CommentUpdateDto commentUpdateDto) throws CommentException;

    void reportBoardComment(long boardCommentId) throws CommentException;
}
