package com.kr.lg.module.lawfirm.mapper;

import com.kr.lg.module.lawfirm.model.entry.LawFirmBoardEntry;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.model.mapper.LawFirmParam;
import com.kr.lg.model.mapper.MapperParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LawFirmMapper {

    List<LawFirmEntry> findLawFirms(LawFirmParam<?> lawFirmParam);

    long findLawFirmsCnt(MapperParam param);

    LawFirmEntry findLawFirm(MapperParam param);

    List<LawFirmBoardEntry> findLawFirmBoard(LawFirmParam<?> lawFirmParam);

    long findLawFirmBoardCnt(MapperParam param);
}
