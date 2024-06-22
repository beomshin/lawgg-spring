package com.kr.lg.mapper;

import com.kr.lg.model.web.querydsl.TrialQ;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialVoteTbMapper {

    TrialQ findVotePercent(Long trialId);
}
