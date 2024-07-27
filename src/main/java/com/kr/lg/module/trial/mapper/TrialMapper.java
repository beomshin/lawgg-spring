package com.kr.lg.module.trial.mapper;

import com.kr.lg.module.trial.model.entry.TrialEntry;
import com.kr.lg.module.trial.model.entry.TrialVoteEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.TrialParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrialMapper {

    List<TrialEntry> findTrials(TrialParam<?> param);
    long findTrialsCnt(MapperParam param);
    TrialEntry findTrial(MapperParam param);
    TrialVoteEntry findVotePercent(MapperParam param);
}
