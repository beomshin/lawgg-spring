package com.kr.lg.db.mapper;

import com.kr.lg.model.querydsl.TrialQ;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialVoteTbMapper {

    TrialQ findVotePercent(Long trialId);
}
