package com.kr.lg.db.mapper;

import com.kr.lg.web.querydsl.TrialQ;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialVoteTbMapper {

    TrialQ findVotePercent(Long trialId);
}
