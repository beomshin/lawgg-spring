package com.kr.lg.module.lawfirm.mapper;

import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.web.dto.mapper.LawFirmParam;
import com.kr.lg.web.dto.mapper.MapperParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LawFirmMapper {

    List<LawFirmEntry> findLawFirms(LawFirmParam<?> lawFirmParam);

    long findLawFirmsCnt(MapperParam param);

    LawFirmEntry findLawFirm(MapperParam param);
}
