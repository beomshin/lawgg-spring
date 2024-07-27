package com.kr.lg.module.comment.mapper;

import com.kr.lg.module.comment.model.entry.BoardCommentEntry;
import com.kr.lg.module.comment.model.entry.TrialCommentEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<BoardCommentEntry> findBoardCommentsWithNotLogin(long rootId);
    List<BoardCommentEntry> findBoardCommentsWithLogin(long rootId, long userId);
    List<TrialCommentEntry> findTrialCommentsWithNotLogin(long rootId);
    List<TrialCommentEntry> findTrialCommentsWithLogin(long rootId, long userId);
}
