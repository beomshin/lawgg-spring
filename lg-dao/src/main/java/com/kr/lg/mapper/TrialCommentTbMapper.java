package com.kr.lg.mapper;

import com.kr.lg.model.web.querydsl.TrialQ;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrialCommentTbMapper {

    List<TrialQ> findAnonymousAllCommentTrial(Long rootId);
    List<TrialQ> findUserAllCommentTrial(Long rootId, Long userId);
}
