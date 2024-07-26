package com.kr.lg.module.lawfirm.service.impl;

import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.exception.LawFirmResultCode;
import com.kr.lg.module.lawfirm.mapper.LawFirmMapper;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.web.dto.mapper.LawFirmParam;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.module.lawfirm.service.LawFirmFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmFindServiceImpl implements LawFirmFindService {

    private final LawFirmMapper lawFirmMapper;

    @Override
    public Page<LawFirmEntry> findLawFirms(LawFirmParam<?> param) throws LawFirmException {
        try {
            log.info("▶ [로펌] 로펌 리스트 조회");
            List<LawFirmEntry> content = lawFirmMapper.findLawFirms(param); // board 조회
            long count = lawFirmMapper.findLawFirmsCnt(param.getData()); // board 개수 조회
            return new PageImpl<>(content, param.getPageable(), count); // pageable 생성
        } catch (RuntimeException e) {
            log.error("", e);
            throw new LawFirmException(LawFirmResultCode.FAIL_FIND_LAW_FIRMS);
        }
    }

    @Override
    public LawFirmEntry findLawFirm(MapperParam param) throws LawFirmException {
        try {
            log.info("▶ [로펌] 로펌 상세 조회");
            return lawFirmMapper.findLawFirm(param);
        } catch (RuntimeException e) {
            log.error("", e);
            throw new LawFirmException(LawFirmResultCode.FAIL_FIND_LAW_FIRM);
        }
    }

}
